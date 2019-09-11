--------------------------------------------------------
--  DDL for Table users
--------------------------------------------------------
  create table users
   (login varchar(10) primary key,
	password varchar(10) not null
    );
      
--------------------------------------------------------
--  DDL for Table customers
--------------------------------------------------------
  create table customers
    (login varchar(10) primary key,
	password varchar(10) not null,
	firstName varchar(20) not null,
    patronymic varchar(20) not null,
    lastName varchar(20) not null,
    address varchar(20) not null,
    phoneNumber varchar(20) not null,
    balance dec(8,2) not null,
    discount int(2) not null,
    cartCost dec(12,2) default '0'
    );

--------------------------------------------------------
--  Insert data
--------------------------------------------------------
insert into users (login,password) values ('admin', 'admin123');

insert into customers (login,password,firstName,patronymic,lastName,address,phoneNumber,balance,discount)
                values ('john','123','John','J','Doe','Some street','89105555555','2000','5');
commit;
