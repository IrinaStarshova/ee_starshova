create sequence order_seq;
--------------------------------------------------------
--  DDL for Table order
--------------------------------------------------------
  create table order_
   (id Long(10) primary key,
	cost dec(12,2) not null,
	creation_date date not null,
	closing_date date,
	status varchar(10) not null,
	login varchar(10)
    );
