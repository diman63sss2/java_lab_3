CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

insert into app_user (is_deleted, last_modified_date, registration_date, uuid, email, first_name, last_name, password, role, username)
VALUES
    ( false, now(), now(), UUID_generate_v4(), 'dimam@email.ru', 'diman', 'diman', 'diman', 'USER', 'diman'),
    ( false, now(), now(), UUID_generate_v4(), 'dimas@email.ru', 'dimas', 'dimas', 'dimas', 'USER', 'dimas'),
    ( false, now(), now(), UUID_generate_v4(), 'dezdemoniy@email.ru', 'dezdemoniy', 'dezdemoniy', 'dezdemoniy', 'ADMIN', 'dezdemoniy');