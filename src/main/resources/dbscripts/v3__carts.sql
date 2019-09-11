create sequence carts_seq;
--------------------------------------------------------
--  DDL for Table carts
--------------------------------------------------------
  create table carts
   (id Long(10) primary key,
   flowerId Long(10) not null,
   flowerName varchar(10) not null,
   quantity int(10) not null,
   totalPrice dec(10,2) not null,
   login varchar(10),
   orderId Long(10)
    );
