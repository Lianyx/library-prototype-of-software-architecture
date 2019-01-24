# TODO 可以更进一步地反射生成insert和selectById的那种语句

DROP TABLE Book;
DROP TABLE Category;
DROP TABLE Message;
DROP TABLE Record;
DROP TABLE Role;
DROP TABLE User;
DROP TABLE UserPermission;
DROP TABLE RoleCategory;


CREATE TABLE Book (
  id         VARCHAR(20),
  name       VARCHAR(50),
  author     VARCHAR(15),
  ebookPath  VARCHAR(30),
  ebookType  VARCHAR(15),
  categoryId VARCHAR(15)
  # Category需要处理，enum需要处理
)
  CHARACTER SET = utf8;
;

CREATE TABLE Category (
  id   VARCHAR(15),
  name VARCHAR(20)
)
  CHARACTER SET = utf8;
;

CREATE TABLE Message (
  type       VARCHAR(15),
  toUsername VARCHAR(20),
  content    VARCHAR(25),
  time       DATETIME
)
  CHARACTER SET = utf8;
;

CREATE TABLE Record (
  username   VARCHAR(20),
  bookId     VARCHAR(20),
  borrowTime DATETIME,
  returnTime DATETIME
  # enum需要处理
)
  CHARACTER SET = utf8;
;

CREATE TABLE Role (
  type     VARCHAR(15),
  maximum  INT,
  dayLimit INT
  # set需要处理d
)
  CHARACTER SET = utf8;
;

CREATE TABLE User (
  username VARCHAR(20),
  password VARCHAR(20),
  role     VARCHAR(10),
  debt     DOUBLE
  # Role需要处理，set需要处理
)
  CHARACTER SET = utf8;
;

CREATE TABLE UserPermission (
  username   VARCHAR(20),
  permission VARCHAR(20)
)
  CHARACTER SET = utf8;
;

CREATE TABLE RoleCategory (
  type       VARCHAR(15),
  categoryId VARCHAR(15)
)
  CHARACTER SET = utf8;
;


INSERT INTO Book (id, name, author, ebookPath, ebookType, categoryId) VALUES (?, ?, ?, ?, ?, ?);
INSERT INTO Category (id, name) VALUES (?, ?);
INSERT INTO Message (type, toUsername, content, time) VALUES (?, ?, ?, ?);
INSERT INTO Record (username, bookId, borrowTime, returnTime) VALUES (?, ?, ?, ?);
INSERT INTO Role (type, maximum, dayLimit) VALUES (?, ?, ?);
INSERT INTO User (username, password, role, debt) VALUES (?, ?, ?, ?);

INSERT INTO RoleCategory (type, categoryId) VALUES (?, ?);
INSERT INTO UserPermission (username, permission) VALUES (?, ?);

UPDATE User
SET password = ?, role = ?, debt = ?
WHERE username = ?;

UPDATE Book
SET name = ?, author = ?, ebookPath = ?, ebookType = ?, categoryId = ?
WHERE id = ?;

UPDATE Record
SET returnTime = ?
WHERE username = ? AND bookId = ? AND borrowTime = ?;

SELECT *
FROM Book
WHERE id = ?;

SELECT *
FROM Category
WHERE id = ?;

SELECT *
FROM Record
WHERE username = ?;

SELECT *
FROM Role
WHERE type = ?;

SELECT categoryId
FROM RoleCategory
WHERE type = ?;

SELECT *
FROM User
WHERE username = ?;

SELECT permission
FROM UserPermission
WHERE username = ?;


SELECT *
FROM Record
WHERE username LIKE ?
      OR Record.bookId IN (SELECT id
                           FROM Book
                           WHERE Book.name LIKE ?);

SELECT
  Record.bookId,
  Record.username,
  Record.borrowTime,
  Record.returnTime,
  Book.name AS bookName
FROM Record, Book
WHERE Record.bookId = Book.id
      AND username LIKE ?
      AND Book.name LIKE ?;

SELECT
  Record.bookId,
  Record.username,
  Record.borrowTime,
  Record.returnTime,
  Book.name AS bookName
FROM Record, Book
WHERE Record.bookId = Book.id;

SELECT *
FROM Message
WHERE type IN (SELECT role
               FROM User
               WHERE username = ?)
      OR toUsername = ?;










































