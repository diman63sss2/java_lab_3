create table book
(
    is_deleted         boolean      not null,
    author_id          bigint
        constraint fkjo8unm4wlmyoqw95qd8ckgimc
            references app_user,
    creation_date      timestamp(6) not null,
    id                 bigint       not null
        constraint book_pkey
            primary key,
    last_modified_date timestamp(6) not null,
    uuid               uuid         not null,
    content            varchar(4000)
);