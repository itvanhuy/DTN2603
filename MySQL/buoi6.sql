use dtn2603;
-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó.
DELIMITER $$
CREATE PROCEDURE GetAccountsByDepartment(IN dept_name VARCHAR(100))
BEGIN
    SELECT a.account_id, a.email, a.username, a.fullname, a.create_date
    FROM `account` a
    JOIN department d ON a.department_id = d.department_id
    WHERE d.department_name = dept_name;
END $$
DELIMITER ;

-- Gọi store
CALL GetAccountsByDepartment('Sales');
-- Question 2: Tạo store để in ra số lượng account trong mỗi group.
DELIMITER $$
CREATE PROCEDURE GetAccountCountPerGroup()
BEGIN
    SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS account_count
    FROM `group` g
    LEFT JOIN group_account ga ON g.group_id = ga.group_id
    GROUP BY g.group_id, g.group_name
    ORDER BY g.group_id;
END $$
DELIMITER ;

-- Gọi store
CALL GetAccountCountPerGroup();
-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại.
DELIMITER $$
CREATE PROCEDURE CountQuestionsByTypeInCurrentMonth()
BEGIN
    SELECT t.type_name, COUNT(q.question_id) AS question_count
    FROM type_question t
    LEFT JOIN question q ON t.type_id = q.type_id 
        AND MONTH(q.create_date) = MONTH(CURRENT_DATE())
        AND YEAR(q.create_date) = YEAR(CURRENT_DATE())
    GROUP BY  t.type_id;
END $$
DELIMITER ;

-- Gọi store
CALL CountQuestionsByTypeInCurrentMonth();
-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất.
DELIMITER $$
CREATE PROCEDURE GetMostPopularTypeID(OUT most_popular_type_id INT)
BEGIN
    SELECT type_id INTO most_popular_type_id
    FROM question
    GROUP BY type_id
    ORDER BY COUNT(question_id) DESC
    LIMIT 1;
END $$
DELIMITER ;

-- Gọi store
CALL GetMostPopularTypeID(@type_id);
SELECT @type_id AS most_popular_type_id;
-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question.
DELIMITER //
CREATE PROCEDURE GetMostPopularTypeName()
BEGIN
    DECLARE type_id INT;
    
    -- Gọi store ở câu 4
    CALL GetMostPopularTypeID(type_id);
    
    -- Lấy tên của type question
    SELECT type_name 
    FROM type_question 
    WHERE type_id = type_id;
END //
DELIMITER ;

-- Gọi store
CALL GetMostPopularTypeName();
-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào.
DELIMITER //
CREATE PROCEDURE SearchGroupsOrUsers(IN search_string VARCHAR(100))
BEGIN
    -- Tìm group có tên chứa chuỗi
    SELECT 'GROUP' AS type, group_id AS id, group_name AS name, NULL AS username
    FROM `group`
    WHERE group_name LIKE CONCAT('%', search_string, '%')
    
    UNION
    
    -- Tìm user có username chứa chuỗi
    SELECT 'ACCOUNT' AS type, account_id AS id, fullname AS name, username
    FROM `account`
    WHERE username LIKE CONCAT('%', search_string, '%');
END //
DELIMITER ;

-- Gọi store
CALL SearchGroupsOrUsers('A');
-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:
-- username sẽ giống email nhưng bỏ phần @..mail đi
-- positionID: sẽ có default là developer
-- departmentID: sẽ được cho vào 1 phòng chờ
-- Sau đó in ra kết quả tạo thành công
DELIMITER //
CREATE PROCEDURE CreateAccount(
    IN p_fullname VARCHAR(100),
    IN p_email VARCHAR(100)
)
BEGIN
    DECLARE v_username VARCHAR(100);
    DECLARE v_position_id INT;
    DECLARE v_department_id INT;
    DECLARE v_new_account_id INT;
    
    -- Tạo username từ email (bỏ phần @...mail)
    SET v_username = SUBSTRING_INDEX(p_email, '@', 1);
    
    -- Lấy position_id của DEV
    SELECT position_id INTO v_position_id 
    FROM `position` 
    WHERE position_name = 'DEV' 
    LIMIT 1;
    
    -- Lấy hoặc tạo phòng ban chờ
    INSERT IGNORE INTO department(department_name) VALUES ('Phòng chờ');
    SELECT department_id INTO v_department_id 
    FROM department 
    WHERE department_name = 'Phòng chờ';
    
    -- Tạo account mới
    INSERT INTO `account`(email, username, fullname, department_id, position_id, create_date)
    VALUES (p_email, v_username, p_fullname, v_department_id, v_position_id, CURRENT_DATE());
    
    SET v_new_account_id = LAST_INSERT_ID();
    
    -- In kết quả
    SELECT 'Tạo thành công' AS message, 
           v_new_account_id AS account_id, 
           p_email AS email, 
           v_username AS username, 
           p_fullname AS fullname,
           'DEV' AS position,
           'Phòng chờ' AS department;
END //
DELIMITER ;

-- Gọi store
CALL CreateAccount('Nguyen Van B', 'b@mail.com');
-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất
DELIMITER $$

CREATE PROCEDURE GetLongestQuestionByType(IN type_name VARCHAR(50))
BEGIN                               
    SELECT                           
        q.content,                    
        t.type_name,                
        LENGTH(q.content) AS content_length 
    FROM question q                   
    JOIN type_question t ON q.type_id = t.type_id  
    WHERE t.type_name = type_name   
    ORDER BY LENGTH(q.content) DESC   
    LIMIT 1;                          

END $$x                              

DELIMITER ;                         
-- Gọi store
CALL GetLongestQuestionByType('ESSAY');
CALL GetLongestQuestionByType('MULTIPLE_CHOICE');
-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID
DELIMITER //
CREATE PROCEDURE DeleteExamByID(IN p_exam_id INT)
BEGIN
    DECLARE v_exam_id INT;
    
    -- Kiểm tra exam có tồn tại không
    SELECT exam_id INTO v_exam_id FROM exam WHERE exam_id = p_exam_id;
    
    IF v_exam_id IS NOT NULL THEN
        -- Xóa các bản ghi liên quan trong exam_question
        DELETE FROM exam_question WHERE exam_id = p_exam_id;
        
        -- Xóa exam
        DELETE FROM exam WHERE exam_id = p_exam_id;
        
        SELECT CONCAT('Đã xóa exam có ID: ', p_exam_id) AS message;
    ELSE
        SELECT 'Không tìm thấy exam với ID này' AS message;
    END IF;
END //
DELIMITER ;

-- Gọi store
CALL DeleteExamByID(1);
-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử dụng store ở câu 9 để xóa)
-- Sau đó in số lượng record đã remove từ các table liên quan trong khi removing
DELIMITER //

CREATE PROCEDURE DeleteOldExams()
BEGIN
    -- Hiển thị danh sách exam sẽ bị xóa
    SELECT '=== DANH SÁCH EXAM CŨ ===' AS info;
    SELECT exam_id, code, title, create_date
    FROM exam
    WHERE create_date <= DATE_SUB(CURDATE(), INTERVAL 3 YEAR);
    
    -- Xóa exam_question trước (do khóa ngoại)
    DELETE FROM exam_question
    WHERE exam_id IN (
        SELECT exam_id FROM exam
        WHERE create_date <= DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
    );
    
    -- Xóa exam
    DELETE FROM exam
    WHERE create_date <= DATE_SUB(CURDATE(), INTERVAL 3 YEAR);
    
    -- In số lượng đã xóa
    SELECT CONCAT('Đã xóa ', ROW_COUNT(), ' exam cũ') AS message;
END //

DELIMITER ;

-- Gọi store
CALL DeleteOldExams();
-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được chuyển về phòng ban default là phòng ban chờ việc
DELIMITER //

CREATE PROCEDURE DeleteDepartmentByName(IN dept_name VARCHAR(100))
BEGIN
    DECLARE v_dept_id INT;
    DECLARE v_default_id INT;
    
    -- Lấy ID của phòng ban cần xóa
    SELECT department_id INTO v_dept_id
    FROM department
    WHERE department_name = dept_name;
    
    -- Lấy ID của phòng ban mặc định (phòng chờ việc)
    SELECT department_id INTO v_default_id
    FROM department
    WHERE department_name = 'Phòng chờ việc';
    
    -- Nếu chưa có phòng chờ việc thì tạo mới
    IF v_default_id IS NULL THEN
        INSERT INTO department(department_name) VALUES ('Phòng chờ việc');
        SET v_default_id = LAST_INSERT_ID();
    END IF;
    
    -- Chuyển account sang phòng chờ việc
    UPDATE `account`
    SET department_id = v_default_id
    WHERE department_id = v_dept_id;
    
    -- Xóa phòng ban
    DELETE FROM department
    WHERE department_id = v_dept_id;
    
    -- Thông báo kết quả
    SELECT CONCAT('Đã xóa phòng ban "', dept_name, '" và chuyển account sang phòng chờ việc') AS message;
END //

DELIMITER ;
-- Gọi store
CALL DeleteDepartmentAndMoveAccounts('Sales');
-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay
DELIMITER //
CREATE PROCEDURE CountQuestionsByMonthInCurrentYear()
BEGIN
    SELECT 
        MONTH(create_date) AS month,
        COUNT(question_id) AS question_count
    FROM question
    WHERE YEAR(create_date) = YEAR(CURRENT_DATE())
    GROUP BY MONTH(create_date)
    ORDER BY month;
END //
DELIMITER ;

-- Gọi store
CALL CountQuestionsByMonthInCurrentYear();
-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong tháng")
DELIMITER //
CREATE PROCEDURE CountQuestionsInLast6Months()
BEGIN
    -- Tạo bảng tạm chứa 6 tháng gần nhất
    WITH RECURSIVE last_6_months AS (
        SELECT 
            DATE_FORMAT(CURRENT_DATE() - INTERVAL 0 MONTH, '%Y-%m-01') AS month_date,
            DATE_FORMAT(CURRENT_DATE() - INTERVAL 0 MONTH, '%m') AS month
        UNION ALL
        SELECT 
            DATE_FORMAT(month_date - INTERVAL 1 MONTH, '%Y-%m-01'),
            DATE_FORMAT(month_date - INTERVAL 1 MONTH, '%m')
        FROM last_6_months
        WHERE month_date > CURRENT_DATE() - INTERVAL 5 MONTH
    )
    SELECT 
        lm.month AS thang,
        COALESCE(COUNT(q.question_id), 0) AS so_luong_cau_hoi,
        CASE 
            WHEN COUNT(q.question_id) = 0 THEN 'không có câu hỏi nào trong tháng'
            ELSE CONCAT('Có ', COUNT(q.question_id), ' câu hỏi')
        END AS ghi_chu
    FROM last_6_months lm
    LEFT JOIN question q ON MONTH(q.create_date) = lm.month 
        AND YEAR(q.create_date) = YEAR(CURRENT_DATE())
    GROUP BY lm.month, lm.month_date
    ORDER BY lm.month_date DESC;
END //
DELIMITER ;

-- Gọi store
CALL CountQuestionsInLast6Months();