create sequence order_seq;
--------------------------------------------------------
--  DDL for Table shop_order
--------------------------------------------------------
  create table shop_order
   (id Long(10) primary key,
	cost dec(12,2) not null,
	creation_date datetime2 not null,
	closing_date date,
	status varchar(10) not null,
	login varchar(10),
	foreign key (login) references shop_user(login)
    );
