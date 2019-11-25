create sequence cart_seq;
--------------------------------------------------------
--  DDL for Table cart
--------------------------------------------------------
  create table cart
   (id Long(10) primary key,
   flower_id Long(10) not null,
   quantity int(10) not null,
   total_price dec(10,2) not null,
   login varchar(10),
   order_id Long(10),
   foreign key (flower_id) references flower(id),
   foreign key (login) references shop_user(login),
   foreign key (order_id) references shop_order(id)
    );
