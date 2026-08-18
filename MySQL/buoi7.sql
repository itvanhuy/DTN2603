use dtn2603;

--  Question 1: Tạo trigger không cho phép người dùng nhập vào Group có ngày tạo trước 1 năm trước
DELIMITER $$
CREATE TRIGGER before_insert_group_check_date
BEFORE INSERT ON `group`
FOR EACH ROW
BEGIN
    IF NEW.create_date < DATE_SUB(NOW(), INTERVAL 1 YEAR) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ngày tạo group không được trước 1 năm so với hiện tại';
    END IF;
END$$
DELIMITER ;

-- Question 2: Tạo trigger Không cho phép người dùng thêm bất kỳ user nào vào department "Sale" nữa, khi thêm thì hiện ra thông báo "Department "Sale" cannot add more user"
DELIMITER $$
CREATE TRIGGER before_insert_account_check_sale
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
    DECLARE dept_name VARCHAR(100);
    SELECT department_name INTO dept_name FROM department WHERE department_id = NEW.department_id;
    IF dept_name = 'Sale' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Department "Sale" cannot add more user';
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_update_account_check_sale
BEFORE UPDATE ON `account`
FOR EACH ROW
BEGIN
    DECLARE dept_name VARCHAR(100);
    SELECT department_name INTO dept_name FROM department WHERE department_id = NEW.department_id;
    IF dept_name = 'Sale' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Department "Sale" cannot add more user';
    END IF;
END$$
DELIMITER ;

-- Question 3: Cấu hình 1 group có nhiều nhất là 5 user
DELIMITER $$
CREATE TRIGGER before_insert_group_account_check_max
BEFORE INSERT ON group_account
FOR EACH ROW
BEGIN
    DECLARE user_count INT;
    SELECT COUNT(*) INTO user_count FROM group_account WHERE group_id = NEW.group_id;
    IF user_count >= 5 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Group đã đạt tối đa 5 user';
    END IF;
END$$
DELIMITER ;

-- Question 4: Cấu hình 1 bài thi có nhiều nhất là 10 Question
DELIMITER $$
CREATE TRIGGER before_insert_exam_question_check_max
BEFORE INSERT ON exam_question
FOR EACH ROW
BEGIN
    DECLARE q_count INT;
    SELECT COUNT(*) INTO q_count FROM exam_question WHERE exam_id = NEW.exam_id;
    IF q_count >= 10 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Bài thi đã có tối đa 10 câu hỏi';
    END IF;
END$$
DELIMITER ;

-- Question 5: Tạo trigger không cho phép người dùng xóa tài khoản có email là admin@gmail.com (đây là tài khoản admin, không cho phép user xóa), còn lại các tài khoản khác thì sẽ cho phép xóa và sẽ xóa tất cả các thông tin liên quan tới user đó
DELIMITER $$
CREATE TRIGGER before_delete_account_check_admin
BEFORE DELETE ON `account`
FOR EACH ROW
BEGIN
    IF OLD.email = 'admin@gmail.com' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa tài khoản admin';
    END IF;
END$$
DELIMITER ;

-- Question 6: Không sử dụng cấu hình default cho field DepartmentID của table Account, hãy tạo trigger cho phép người dùng khi tạo account không điền vào departmentID thì sẽ được phân vào phòng ban "waiting Department"
DELIMITER $$
CREATE TRIGGER before_insert_account_set_default_dept
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
    DECLARE waiting_dept_id INT;
    IF NEW.department_id IS NULL THEN
        SELECT department_id INTO waiting_dept_id FROM department WHERE department_name = 'waiting Department';
        SET NEW.department_id = waiting_dept_id;
    END IF;
END$$
DELIMITER ;

-- Question 7: Cấu hình 1 bài thi chỉ cho phép user tạo tối đa 4 answers cho mỗi question, trong đó có tối đa 2 đáp án đúng.
DELIMITER $$
CREATE TRIGGER before_insert_answer_check
BEFORE INSERT ON answer
FOR EACH ROW
BEGIN
    DECLARE total_answers INT;
    DECLARE correct_answers INT;
    
    SELECT COUNT(*) INTO total_answers FROM answer WHERE question_id = NEW.question_id;
    SELECT COUNT(*) INTO correct_answers FROM answer WHERE question_id = NEW.question_id AND is_correct = 1;
    
    IF total_answers >= 4 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mỗi câu hỏi chỉ có tối đa 4 answers';
    END IF;
    
    IF NEW.is_correct = 1 AND correct_answers >= 2 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mỗi câu hỏi chỉ có tối đa 2 đáp án đúng';
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_update_answer_check
BEFORE UPDATE ON answer
FOR EACH ROW
BEGIN
    DECLARE total_answers INT;
    DECLARE correct_answers INT;
    
    SELECT COUNT(*) INTO total_answers FROM answer WHERE question_id = NEW.question_id AND answer_id != OLD.answer_id;
    SELECT COUNT(*) INTO correct_answers FROM answer WHERE question_id = NEW.question_id AND is_correct = 1 AND answer_id != OLD.answer_id;
    
    IF total_answers >= 4 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mỗi câu hỏi chỉ có tối đa 4 answers';
    END IF;
    
    IF NEW.is_correct = 1 AND (correct_answers + 1) > 2 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mỗi câu hỏi chỉ có tối đa 2 đáp án đúng';
    END IF;
END$$
DELIMITER ;

-- Question 8: Viết trigger sửa lại dữ liệu cho đúng:Nếu người dùng nhập vào gender của account là nam, nữ, chưa xác định Thì sẽ đổi lại thành M, F, U cho giống với cấu hình ở database
DELIMITER $$
CREATE TRIGGER before_insert_account_normalize_gender
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
    IF NEW.gender = 'nam' THEN SET NEW.gender = 'M';
    ELSEIF NEW.gender = 'nữ' THEN SET NEW.gender = 'F';
    ELSEIF NEW.gender = 'chưa xác định' THEN SET NEW.gender = 'U';
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_update_account_normalize_gender
BEFORE UPDATE ON `account`
FOR EACH ROW
BEGIN
    IF NEW.gender = 'nam' THEN SET NEW.gender = 'M';
    ELSEIF NEW.gender = 'nữ' THEN SET NEW.gender = 'F';
    ELSEIF NEW.gender = 'chưa xác định' THEN SET NEW.gender = 'U';
    END IF;
END$$
DELIMITER ;

-- Question 9: Viết trigger không cho phép người dùng xóa bài thi mới tạo được 2 ngày
DELIMITER $$
CREATE TRIGGER before_delete_exam_check_date
BEFORE DELETE ON exam
FOR EACH ROW
BEGIN
    IF OLD.create_date >= DATE_SUB(NOW(), INTERVAL 2 DAY) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa bài thi mới tạo trong vòng 2 ngày';
    END IF;
END$$
DELIMITER ;

-- Question 10: Viết trigger chỉ cho phép người dùng chỉ được update, delete các question khi question đó chưa nằm trong exam nào
DELIMITER $$
CREATE TRIGGER before_update_question_check_in_exam
BEFORE UPDATE ON question
FOR EACH ROW
BEGIN
    DECLARE exam_count INT;
    SELECT COUNT(*) INTO exam_count FROM exam_question WHERE question_id = OLD.question_id;
    IF exam_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể sửa câu hỏi đã nằm trong exam';
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_delete_question_check_in_exam
BEFORE DELETE ON question
FOR EACH ROW
BEGIN
    DECLARE exam_count INT;
    SELECT COUNT(*) INTO exam_count FROM exam_question WHERE question_id = OLD.question_id;
    IF exam_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa câu hỏi đã nằm trong exam';
    END IF;
END$$
DELIMITER ;

-- Question 12: Lấy ra thông tin exam trong đó: Duration <= 30 thì sẽ đổi thành giá trị "Short time" 30 < Duration <= 60 thì sẽ đổi thành giá trị "Medium time" Duration > 60 thì sẽ đổi thành giá trị "Long time"
SELECT 
    exam_id,
    code,
    title,
    duration,
    CASE 
        WHEN TIME_TO_SEC(duration) / 60 <= 30 THEN 'Short time'
        WHEN TIME_TO_SEC(duration) / 60 <= 60 THEN 'Medium time'
        ELSE 'Long time'
    END AS time_category
FROM exam;

-- Question 13: Thống kê số account trong mỗi group và in ra thêm 1 column nữa có tên là the_number_user_amount và mang giá trị được quy định như sau:
-- Nếu số lượng user trong group =< 5 thì sẽ có giá trị là few
-- Nếu số lượng user trong group <= 20 và > 5 thì sẽ có giá trị là normal
-- Nếu số lượng user trong group > 20 thì sẽ có giá trị là higher
SELECT 
    g.group_id,
    g.group_name,
    COUNT(ga.account_id) AS user_count,
    CASE 
        WHEN COUNT(ga.account_id) <= 5 THEN 'few'
        WHEN COUNT(ga.account_id) <= 20 THEN 'normal'
        ELSE 'higher'
    END AS the_number_user_amount
FROM `group` g
LEFT JOIN group_account ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name;

-- Question 14: Thống kê số mỗi phòng ban có bao nhiêu user, nếu phòng ban nào không có user thì sẽ thay đổi giá trị 0 thành "Không có User"
SELECT 
    d.department_id,
    d.department_name,
    COUNT(a.account_id) AS user_count,
    CASE 
        WHEN COUNT(a.account_id) = 0 THEN 'Không có User'
        ELSE CAST(COUNT(a.account_id) AS CHAR)
    END AS display_user_count
FROM department d
LEFT JOIN `account` a ON d.department_id = a.department_id
GROUP BY d.department_id, d.department_name;