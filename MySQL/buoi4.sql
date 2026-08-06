use dtn2603;
-- Question 1: Viết lệnh để lấy racc daccnh sách nhân viên và thông tin phòng baccn củacc họ
SELECT acc.*, d.department_name
FROM account acc
JOIN department d
ON acc.department_id = d.department_id;
-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010
SELECT acc.*, d.department_name
FROM account acc
INNER JOIN department d
ON acc.department_id = d.department_id
WHERE acc.create_date > '2010-12-20';
-- Question 3: Viết lệnh để lấy ra tất cả các developer
SELECT acc.*
FROM account acc
JOIN position p
ON acc.position_id = p.position_id
WHERE p.position_name = 'DEV';
-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên
SELECT d.department_name, COUNT(acc.account_id) AS so_luong
FROM department d
JOIN account acc
ON d.department_id = acc.department_id
GROUP BY d.department_id, d.department_name
HAVING COUNT(acc.account_id) > 3;
-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều nhất
SELECT q.question_id,
       q.content,
       COUNT(eq.exam_id) AS so_lan
FROM question q
JOIN exam_question eq
ON q.question_id = eq.question_id
GROUP BY q.question_id, q.content
ORDER BY so_lan DESC
LIMIT 1;
-- Question 6: Thông kê mỗi category Question được sử dụng trong bao nhiêu Question
SELECT c.category_name,
       COUNT(q.question_id) AS so_luong
FROM category_question c
LEFT JOIN question q
ON c.category_id = q.category_id
GROUP BY c.category_id, c.category_name;
-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam
SELECT q.question_id,
       q.content,
       COUNT(eq.exam_id) AS so_exam
FROM question q
LEFT JOIN exam_question eq
ON q.question_id = eq.question_id
GROUP BY q.question_id, q.content;
-- Question 8: Lấy ra Question có nhiều câu trả lời nhất
SELECT q.question_id,
       q.content,
       COUNT(acc.answer_id) AS so_answer
FROM question q
JOIN answer acc
ON q.question_id = acc.question_id
GROUP BY q.question_id, q.content
ORDER BY so_answer DESC
LIMIT 1;
-- Question 9: Thống kê số lượng account trong mỗi group
SELECT g.group_name,
       COUNT(ga.account_id) AS so_luong
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name;
-- Question 10: Tìm chức vụ có ít người nhất
SELECT p.position_name,
       COUNT(a.account_id) AS so_luong
FROM position p
LEFT JOIN account acc
ON p.position_id = acc.position_id
GROUP BY p.position_id, p.position_name
ORDER BY so_luong
LIMIT 1;
-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM
SELECT d.department_name, COUNT(a.account_id) AS so_dev
FROM department d
INNER JOIN account a
ON d.department_id = a.department_id
INNER JOIN position p
ON a.position_id = p.position_id
WHERE p.position_name = 'DEV'
GROUP BY d.department_name;
SELECT d.department_name, COUNT(a.account_id) AS so_test
FROM department d
INNER JOIN account a
ON d.department_id = a.department_id
INNER JOIN position p
ON a.position_id = p.position_id
WHERE p.position_name = 'TEST'
GROUP BY d.department_name;
SELECT d.department_name, COUNT(a.account_id) AS so_scrum_master
FROM department d
INNER JOIN account a
ON d.department_id = a.department_id
INNER JOIN position p
ON a.position_id = p.position_id
WHERE p.position_name = 'SCRUM_MASTER'
GROUP BY d.department_name;
SELECT d.department_name, COUNT(a.account_id) AS so_pm
FROM department d
INNER JOIN account a
ON d.department_id = a.department_id
INNER JOIN position p
ON a.position_id = p.position_id
WHERE p.position_name = 'PM'
GROUP BY d.department_name;
-- Question 12: Lấy thông tin chi tiết của câu hỏi bao gồm: thông tin cơ bản của question, loại câu hỏi, ai là người tạo ra câu hỏi, câu trả lời là gì, ...
SELECT q.question_id,
       q.content,
       tq.type_name,
       acc.fullname AS creator,
       ans.content AS answer,
       ans.is_correct
FROM question q
JOIN type_question tq
ON q.type_id = tq.type_id
JOIN account acc
ON q.creator_id = acc.account_id
LEFT JOIN answer ans
ON q.question_id = ans.question_id;
-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm
SELECT tq.type_name,
       COUNT(q.question_id) AS so_luong
FROM type_question tq
LEFT JOIN question q
ON tq.type_id = q.type_id
GROUP BY tq.type_id, tq.type_name;
-- Question 14:Lấy ra group không có account nào
SELECT g.group_name
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
WHERE ga.account_id IS NULL;
-- Question 16: Lấy ra question không có answer nào
SELECT q.question_id,
       q.content
FROM question q
LEFT JOIN answer acc
ON q.question_id = acc.question_id
WHERE acc.answer_id IS NULL;