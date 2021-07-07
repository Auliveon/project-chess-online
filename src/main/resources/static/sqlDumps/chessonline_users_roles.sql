create table users_roles
(
    users_pid bigint       not null,
    roles_pid varchar(255) not null,
    constraint FK2exp4d5ob8gdd1ikye0y93doi
        foreign key (users_pid) references user (pid),
    constraint FK35b894078x102ch97q1748c47
        foreign key (roles_pid) references role (pid)
);

INSERT INTO chessonline.users_roles (users_pid, roles_pid) VALUES (1, 'ADMIN');
INSERT INTO chessonline.users_roles (users_pid, roles_pid) VALUES (2, 'ADMIN');