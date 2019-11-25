--------------------------------------------------------
--  DDL for Table shop_user
--------------------------------------------------------
  create table shop_user
    (login varchar(10) primary key,
	password varchar(10) not null,
	role varchar(5) not null
	    check (role in('USER', 'ADMIN')),
	first_name varchar(20),
    patronymic varchar(20),
    last_name varchar(20),
    address varchar(20),
    phone_number varchar(20),
    balance dec(8,2) default '0' check (balance>=0),
    discount int(2) default '0' check (discount>=0),
    cart_cost dec(12,2) default '0'
    );

--------------------------------------------------------
--  Insert data
--------------------------------------------------------
insert into shop_user (login,password,role) values ('admin', 'admin123', 'ADMIN');

insert into shop_user (login,password,role,first_name,patronymic,last_name,address,phone_number,balance,discount)
                values ('john','123','USER','John','J','Doe','Some street','89105555555','2000','5');
commit;
