create table `order`
(
    id int auto_increment,
    account_id int not null,
    stock_id int not null,
    price int not null,
    quantity int not null,
    create_time datetime not null,
    update_time datetime not null,
    constraint order_pk
        primary key (id)
);