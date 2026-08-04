USE dtn2603;
-- Question 1: Thêm ít nhất 10 record vào mỗi table
INSERT INTO department(department_name) VALUES
('Legal'),('Procurement'),('Logistics'),('Security'),('Facilities'),
('Public Relations'),('Business Development'),('Strategy'),('Innovation'),('Customer Service');
INSERT INTO `account`(email,username,fullname,department_id,position_id,create_date) VALUES
('a6@mail.com','user6','Nguyen Van F',6,1,'2026-08-06'),
('a7@mail.com','user7','Tran Thi G',7,2,'2026-08-07'),
('a8@mail.com','user8','Le Van H',8,3,'2026-08-08'),
('a9@mail.com','user9','Pham Thi I',9,4,'2026-08-09'),
('a10@mail.com','user10','Hoang Van J',10,1,'2026-08-10'),
('a11@mail.com','user11','Nguyen Thi K',1,2,'2026-08-11'),
('a12@mail.com','user12','Tran Van L',2,3,'2026-08-12'),
('a13@mail.com','user13','Le Thi M',3,4,'2026-08-13'),
('a14@mail.com','user14','Pham Van N',4,1,'2026-08-14'),
('a15@mail.com','user15','Hoang Thi O',5,2,'2026-08-15');
INSERT INTO `group`(group_name,creator_id,create_date) VALUES
('Group F',6,'2026-02-06'),
('Group G',7,'2026-02-07'),
('Group H',8,'2026-02-08'),
('Group I',9,'2026-02-09'),
('Group J',10,'2026-02-10'),
('Group K',11,'2026-02-11'),
('Group L',12,'2026-02-12'),
('Group M',13,'2026-02-13'),
('Group N',14,'2026-02-14'),
('Group O',15,'2026-02-15');
INSERT INTO group_account VALUES
(6,6,'2026-02-11'),
(7,7,'2026-02-12'),
(8,8,'2026-02-13'),
(9,9,'2026-02-14'),
(10,10,'2026-02-15'),
(1,11,'2026-02-16'),
(2,12,'2026-02-17'),
(3,13,'2026-02-18'),
(4,14,'2026-02-19'),
(5,15,'2026-02-20');
INSERT INTO category_question(category_name) VALUES
('Python'),('JavaScript'),('React'),('Angular'),('Vue.js');
INSERT INTO question(content,category_id,type_id,creator_id,create_date) VALUES
('What is Python?',6,1,6,'2026-08-06'),
('Explain list comprehension.',6,2,7,'2026-08-07'),
('What is JavaScript?',7,3,8,'2026-08-08'),
('Explain closure.',7,4,9,'2026-08-09'),
('What is React?',8,5,10,'2026-08-10'),
('Explain useState hook.',8,1,11,'2026-08-11'),
('What is Angular?',9,2,12,'2026-08-12'),
('Explain dependency injection.',9,3,13,'2026-08-13'),
('What is Vue.js?',10,4,14,'2026-08-14'),
('Explain computed properties.',10,5,15,'2026-08-15');
INSERT INTO answer(content,question_id,is_correct) VALUES
('Python is a language',6,1),
('List comprehension is...',7,1),
('JavaScript is a language',8,1),
('Closure is...',9,1),
('React is a library',10,1),
('useState is a hook',11,1),
('Angular is a framework',12,1),
('Dependency injection is...',13,1),
('Vue.js is a framework',14,1),
('Computed properties are...',15,1);
INSERT INTO exam(code,title,category_id,duration,creator_id,create_date) VALUES
('EX006','Python Test',6,'01:00:00',6,'2026-04-06'),
('EX007','JavaScript Test',7,'01:00:00',7,'2026-04-07'),
('EX008','React Test',8,'01:30:00',8,'2026-04-08'),
('EX009','Angular Test',9,'00:45:00',9,'2026-04-09'),
('EX010','Vue.js Test',10,'01:15:00',10,'2026-04-10'),
('EX011','Advanced Java Test',1,'02:00:00',11,'2026-04-11'),
('EX012','Advanced SQL Test',3,'01:30:00',12,'2026-04-12'),
('EX013','.NET Advanced Test',2,'01:30:00',13,'2026-04-13'),
('EX014','Postman Advanced Test',4,'01:00:00',14,'2026-04-14'),
('EX015','Ruby Advanced Test',5,'01:30:00',15,'2026-04-15');
INSERT INTO exam_question VALUES
(6,6),(7,7),(8,8),(9,9),(10,10),
(11,11),(12,12),(13,13),(14,14),(15,15);
-- Question 2: lấy ra tất cả các phòng ban
SELECT * FROM department;
-- Question 3: lấy ra id của phòng ban "Sale"
SELECT department_id FROM department WHERE department_name='Sales';
-- Question 4: lấy ra thông tin account có full name dài nhất
SELECT * FROM account WHERE LENGTH(fullname)=(SELECT MAX(LENGTH(fullname)) FROM account);
-- Question 5: Lấy ra thông tin account có full name dài nhất và thuộc phòng ban có id= 3
SELECT * FROM account WHERE department_id=3 AND LENGTH(fullname)=(SELECT MAX(LENGTH(fullname)) FROM account WHERE department_id=3);
-- Question 6: Lấy ra tên group đã tham gia trước ngày 20/12/2019
SELECT group_name FROM `group` WHERE create_date<'2019-12-20';
-- Question 7: Lấy ra ID của question có >= 4 câu trả lời
SELECT question_id FROM answer GROUP BY question_id HAVING COUNT(*)>=4;
-- Question 8: Lấy ra các mã đề thi có thời gian thi >= 60 phút và được tạo trước ngày 20/12/2019
SELECT code FROM exam WHERE duration>='01:00:00' AND create_date<'2019-12-20';
-- Question 9: Lấy ra 5 group được tạo gần đây nhất
SELECT * FROM `group` ORDER BY create_date DESC LIMIT 5;
-- Question 10: Đếm số nhân viên thuộc department có id = 2
SELECT COUNT(*) AS total_employee FROM account WHERE department_id=2;
-- Question 11: Lấy ra nhân viên có tên bắt đầu bằng chữ "D" và kết thúc bằng chữ "o"
SELECT * FROM account WHERE fullname LIKE 'D%o';
