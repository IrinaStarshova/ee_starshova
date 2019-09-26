create sequence cart_seq;
--------------------------------------------------------
--  DDL for Table cart
--------------------------------------------------------
  create table cart
   (id Long(10) primary key,
   flower_id Long(10) not null,
   flowerName varchar(10) not null,
   quantity int(10) not null,
   total_price dec(10,2) not null,
   login varchar(10),
   order_id Long(10)
    );
