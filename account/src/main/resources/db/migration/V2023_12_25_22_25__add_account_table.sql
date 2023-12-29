create table account
(
    id          int auto_increment comment '账户ID',
    balance     bigint default 0 not null comment '余额',
    create_time datetime         not null comment '创建时间',
    update_time datetime         not null comment '更新时间',
    constraint table_name_pk
        primary key (id)
);