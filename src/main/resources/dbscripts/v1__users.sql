--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------
  create table "USERS"
   ("LOGIN" VARCHAR2(45 CHAR) PRIMARY KEY,
	"PASSWORD" VARCHAR2(45 CHAR) NOT NULL,
    );

--------------------------------------------------------
--  Insert data
--------------------------------------------------------
Insert into users (login,password) values ('admin', 'admin123');
commit;
