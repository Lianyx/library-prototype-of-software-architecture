CREATE TABLE Book (
  id         VARCHAR(20),
  name       VARCHAR(30),
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
  username      VARCHAR(20),
  bookId        VARCHAR(20),
  operationType VARCHAR(10),
  borrowTime    DATETIME,
  returnTime    DATETIME
  # enum需要处理
)
  CHARACTER SET = utf8;
;

CREATE TABLE Role (
  type     VARCHAR(15),
  maximum  INT,
  dayLimit INT
  # set需要处理
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
  permission VARCHAR(10)
)
  CHARACTER SET = utf8;
;

CREATE TABLE RoleCategory (
  type       VARCHAR(15),
  categoryId VARCHAR(15)
)
  CHARACTER SET = utf8;
;