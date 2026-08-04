CREATE DATABASE IF NOT EXISTS dtn2603;
USE dtn2603;

CREATE TABLE department(
 department_id INT AUTO_INCREMENT PRIMARY KEY,
 department_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE `position`(
 position_id INT AUTO_INCREMENT PRIMARY KEY,
 position_name ENUM('DEV','TEST','SCRUM_MASTER','PM') NOT NULL UNIQUE
);

CREATE TABLE `account`(
 account_id INT AUTO_INCREMENT PRIMARY KEY,
 email VARCHAR(100) NOT NULL UNIQUE,
 username VARCHAR(100) NOT NULL UNIQUE,
 fullname VARCHAR(100) NOT NULL,
 department_id INT NOT NULL,
 position_id INT NOT NULL,
 create_date DATE NOT NULL DEFAULT (CURRENT_DATE),
 CONSTRAINT fk_acc_dep FOREIGN KEY(department_id) REFERENCES department(department_id),
 CONSTRAINT fk_acc_pos FOREIGN KEY(position_id) REFERENCES `position`(position_id)
);

CREATE TABLE `group`(
 group_id INT AUTO_INCREMENT PRIMARY KEY,
 group_name VARCHAR(100) NOT NULL UNIQUE,
 creator_id INT NOT NULL,
 create_date DATE NOT NULL DEFAULT (CURRENT_DATE),
 CONSTRAINT fk_group_creator FOREIGN KEY(creator_id) REFERENCES `account`(account_id)
);

CREATE TABLE group_account(
 group_id INT,
 account_id INT,
 join_date DATE NOT NULL DEFAULT (CURRENT_DATE),
 PRIMARY KEY(group_id,account_id),
 FOREIGN KEY(group_id) REFERENCES `group`(group_id),
 FOREIGN KEY(account_id) REFERENCES `account`(account_id)
);

CREATE TABLE type_question(
 type_id INT AUTO_INCREMENT PRIMARY KEY,
 type_name ENUM('ESSAY','MULTIPLE_CHOICE') NOT NULL UNIQUE
);

CREATE TABLE category_question(
 category_id INT AUTO_INCREMENT PRIMARY KEY,
 category_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE question(
 question_id INT AUTO_INCREMENT PRIMARY KEY,
 content TEXT NOT NULL,
 category_id INT NOT NULL,
 type_id INT NOT NULL,
 creator_id INT NOT NULL,
 create_date DATE NOT NULL DEFAULT (CURRENT_DATE),
 FOREIGN KEY(category_id) REFERENCES category_question(category_id),
 FOREIGN KEY(type_id) REFERENCES type_question(type_id),
 FOREIGN KEY(creator_id) REFERENCES `account`(account_id)
);

CREATE TABLE answer(
 answer_id INT AUTO_INCREMENT PRIMARY KEY,
 content TEXT NOT NULL,
 question_id INT NOT NULL,
 is_correct BOOLEAN NOT NULL,
 FOREIGN KEY(question_id) REFERENCES question(question_id)
);

CREATE TABLE exam(
 exam_id INT AUTO_INCREMENT PRIMARY KEY,
 code VARCHAR(20) NOT NULL UNIQUE,
 title VARCHAR(100) NOT NULL,
 category_id INT NOT NULL,
 duration TIME NOT NULL,
 creator_id INT NOT NULL,
 create_date DATE NOT NULL DEFAULT (CURRENT_DATE),
 FOREIGN KEY(category_id) REFERENCES category_question(category_id),
 FOREIGN KEY(creator_id) REFERENCES `account`(account_id)
);

CREATE TABLE exam_question(
 exam_id INT,
 question_id INT,
 PRIMARY KEY(exam_id,question_id),
 FOREIGN KEY(exam_id) REFERENCES exam(exam_id),
 FOREIGN KEY(question_id) REFERENCES question(question_id)
);

INSERT INTO department(department_name) VALUES
('Sales'),('Marketing'),('HR'),('IT'),('Finance');

INSERT INTO `position`(position_name) VALUES
('DEV'),('TEST'),('SCRUM_MASTER'),('PM');

INSERT INTO `account`(email,username,fullname,department_id,position_id,create_date) VALUES
('a1@mail.com','user1','Nguyen Van A',1,1,'2026-08-01'),
('a2@mail.com','user2','Tran Thi B',2,2,'2026-08-02'),
('a3@mail.com','user3','Le Van C',3,3,'2026-08-03'),
('a4@mail.com','user4','Pham Thi D',4,4,'2026-08-04'),
('a5@mail.com','user5','Hoang Van E',5,1,'2026-08-05');

INSERT INTO `group`(group_name,creator_id,create_date) VALUES
('Group A',1,'2026-02-01'),
('Group B',2,'2026-02-02'),
('Group C',3,'2026-02-03'),
('Group D',4,'2026-02-04'),
('Group E',5,'2026-02-05');

INSERT INTO group_account VALUES
(1,1,'2026-02-10'),
(2,2,'2026-02-10'),
(3,3,'2026-02-10'),
(4,4,'2026-02-10'),
(5,5,'2026-02-10');

INSERT INTO type_question(type_name) VALUES
('ESSAY'),('MULTIPLE_CHOICE');

INSERT INTO category_question(category_name) VALUES
('Java'),('.NET'),('SQL'),('Postman'),('Ruby');

INSERT INTO question(content,category_id,type_id,creator_id,create_date) VALUES
('What is Java?',1,1,1,'2026-08-01'),
('Explain OOP.',1,1,2,'2026-8-02'),
('SQL JOIN?',3,2,3,'2026-08-03'),
('HTTP methods?',4,2,4,'2026-08-04'),
('Ruby class?',5,1,5,'2026-08-05');

INSERT INTO answer(content,question_id,is_correct) VALUES
('Java is a language',1,1),
('Object-oriented programming',2,1),
('INNER JOIN',3,1),
('GET',4,1),
('class Demo',5,1);

INSERT INTO exam(code,title,category_id,duration,creator_id,create_date) VALUES
('EX001','Java Test',1,'01:00:00',1,'2026-04-01'),
('EX002','.NET Test',2,'01:00:00',2,'2026-04-02'),
('EX003','SQL Test',3,'01:30:00',3,'2026-04-03'),
('EX004','Postman Test',4,'00:45:00',4,'2026-04-04'),
('EX005','Ruby Test',5,'01:15:00',5,'2026-04-05');

INSERT INTO exam_question VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(5,5);
