--------------------------------------------------------
--  DDL for Table user_
--------------------------------------------------------
  create table user_
   (login varchar(10) primary key,
	password varchar(10) not null
    );
      
--------------------------------------------------------
--  DDL for Table customer
--------------------------------------------------------
  create table customer
    (login varchar(10) primary key,
	password varchar(10) not null,
	first_name varchar(20) not null,
    patronymic varchar(20) not null,
    last_name varchar(20) not null,
    address varchar(20) not null,
    phone_number varchar(20) not null,
    balance dec(8,2) not null,
    discount int(2) not null,
    cart_cost dec(12,2) default '0'
    );

--------------------------------------------------------
--  Insert data
--------------------------------------------------------
insert into user_ (login,password) values ('admin', 'admin123');

insert into customer (login,password,first_name,patronymic,last_name,address,phone_number,balance,discount)
                values ('john','123','John','J','Doe','Some street','89105555555','2000','5');
commit;
