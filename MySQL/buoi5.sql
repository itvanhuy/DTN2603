use dtn2603;
-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
CREATE VIEW sale_employees AS
SELECT a.account_id, a.email, a.username, a.fullname, a.department_id, a.position_id, a.create_date
FROM `account` a
JOIN department d ON a.department_id = d.department_id
WHERE d.department_name = 'Sales';

SELECT * FROM sale_employees;
-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất
-- cte
CREATE VIEW most_active_accounts AS
WITH 
-- Bước 1: Tính số lượng group của mỗi account
account_group_count AS (
    SELECT account_id, COUNT(group_id) AS group_count
    FROM group_account
    GROUP BY account_id
),
-- Bước 2: Tìm số lượng group nhiều nhất
max_group_count AS (
    SELECT MAX(group_count) AS max_count
    FROM account_group_count
)
-- Lấy thông tin các account có số lượng group = max
SELECT a.account_id, a.email, a.username, a.fullname, agc.group_count
FROM `account` a
JOIN account_group_count agc ON a.account_id = agc.account_id
WHERE agc.group_count = (SELECT max_count FROM max_group_count);

SELECT * FROM most_active_accounts;
-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ được coi là quá dài) và xóa nó đi
-- Bước 1: Tạo view chứa các câu hỏi có content dài hơn 300 từ
CREATE VIEW long_questions AS
SELECT question_id, content, category_id, type_id, creator_id, create_date
FROM question
WHERE LENGTH(content) > 300;

-- Bước 2: Xóa các câu hỏi trong view long_questions
DELETE FROM question
WHERE question_id IN (SELECT question_id FROM long_questions);

SELECT * FROM long_questions;
-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
-- subquery
CREATE VIEW largest_departments AS
-- Bước 3: Lấy các phòng ban có số nhân viên = max
SELECT d.department_id, d.department_name, 
       -- Bước 1: Tính số lượng nhân viên của mỗi phòng ban
       (SELECT COUNT(a.account_id) FROM `account` a WHERE a.department_id = d.department_id) AS employee_count
FROM department d
WHERE (SELECT COUNT(a.account_id) FROM `account` a WHERE a.department_id = d.department_id) = (
    -- Bước 2: Tìm số lượng nhân viên nhiều nhất
    SELECT MAX(employee_count)
    FROM (
        SELECT COUNT(account_id) AS employee_count
        FROM `account`
        GROUP BY department_id
    ) AS max_dept
);

SELECT * FROM largest_departments;
-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo.
CREATE VIEW nguyen_questions AS
SELECT q.question_id, q.content, q.category_id, q.type_id, q.creator_id, q.create_date,
       a.fullname AS creator_name
FROM question q
JOIN `account` a ON q.creator_id = a.account_id
WHERE a.fullname LIKE 'Nguyễn%';

SELECT * FROM nguyen_questions;