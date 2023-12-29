create table storage
(
    id int auto_increment,
    name varchar(255) not null,
    price int not null,
    quantity int not null,
    create_time datetime not null,
    update_time datetime not null,
    constraint storage_pk
        primary key (id)
);

