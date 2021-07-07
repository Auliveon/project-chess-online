create table role
(
    pid         varchar(255) not null
        primary key,
    description varchar(255) null
);

INSERT INTO chessonline.role (pid, description) VALUES ('ADMIN', 'Администор сайта со всеми правами');
INSERT INTO chessonline.role (pid, description) VALUES ('USER', 'Зарегестрированный пользователь');