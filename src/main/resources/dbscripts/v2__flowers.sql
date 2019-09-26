create sequence flower_seq;
--------------------------------------------------------
--  DDL for Table flower
--------------------------------------------------------
  create table flower
   (id Long(10) primary key,
   name varchar(10) not null,
	price dec(8,2) not null,
	quantity int(10) not null,
	quantity_in_cart int(10) default 0
    );
--------------------------------------------------------
--  Insert data
--------------------------------------------------------
insert into flower (id,name,price,quantity) values (flower_seq.nextval,'Rose', '150', '1000');
insert into flower (id,name,price,quantity) values (flower_seq.nextval,'Tulip', '100', '300');
insert into flower (id,name,price,quantity) values (flower_seq.nextval,'Lily', '160', '200');
insert into flower (id,name,price,quantity) values (flower_seq.nextval,'Iris', '170', '500');
insert into flower (id,name,price,quantity) values (flower_seq.nextval,'Carnation', '50', '1200');

commit;