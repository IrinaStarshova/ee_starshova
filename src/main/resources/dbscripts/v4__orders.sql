create sequence orders_seq;
--------------------------------------------------------
--  DDL for Table flowers
--------------------------------------------------------
  create table orders
   (id Long(10) primary key,
	cost dec(12,2) not null,
	creationDate date not null,
	closingDate date,
	status varchar(10) not null,
	login varchar(10)
    );

--------------------------------------------------------
--  Insert data
--------------------------------------------------------
--insert into orders (id,cost,creationDate,closingDate,status) values (orders_seq.nextval, '200', '2019.08.30','','create');
--commit;