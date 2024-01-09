-- auto-generated definition
create table app_user
(
    is_deleted         boolean      not null,
    id                 bigint       not null
        constraint app_user_pkey
            primary key,
    last_modified_date timestamp(6) not null,
    registration_date  timestamp(6) not null,
    uuid               uuid         not null,
    email              varchar(255) not null,
    first_name         varchar(255) not null,
    last_name          varchar(255) not null,
    password           varchar(255) not null,
    role               varchar(255)
        constraint app_user_role_check
            check ((role)::text = ANY ((ARRAY ['USER'::character varying, 'ADMIN'::character varying])::text[])),
    username           varchar(255) not null
);

alter table app_user
    owner to postgres;
