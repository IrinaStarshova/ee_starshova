create sequence flowers_seq;
--------------------------------------------------------
--  DDL for Table flowers
--------------------------------------------------------
  create table flowers
   (id Long(10) primary key,
   name varchar(10) not null,
	price dec(8,2) not null,
	quantity int(10) not null,
	quantityInCart int(10) default 0
    );
--------------------------------------------------------
--  Insert data
--------------------------------------------------------
insert into flowers (id,name,price,quantity) values (flowers_seq.nextval,'Rose', '150', '1000');
insert into flowers (id,name,price,quantity) values (flowers_seq.nextval,'Tulip', '100', '300');
insert into flowers (id,name,price,quantity) values (flowers_seq.nextval,'Lily', '160', '200');
insert into flowers (id,name,price,quantity) values (flowers_seq.nextval,'Iris', '170', '500');
insert into flowers (id,name,price,quantity) values (flowers_seq.nextval,'Carnation', '50', '1200');

commit;