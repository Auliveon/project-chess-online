create table user
(
    pid         bigint auto_increment
        primary key,
    first_name  varchar(100)                 not null,
    last_name   varchar(100)                 not null,
    login       varchar(100)                 not null,
    middle_name varchar(100)                 null,
    password    varchar(255)                 not null,
    status      varchar(20) default 'ACTIVE' not null,
    constraint UK_ew1hvam8uwaknuaellwhqchhb
        unique (login)
);

INSERT INTO chessonline.user (pid, first_name, last_name, login, middle_name, password, status) VALUES (1, 'Kirill', 'Savitsky', '1', 'V', '$2y$08$nYFhuCQAfNP7vpWHCPExCeum4vndKbF6hH11om3Z5at/SIu85NisG', 'ACTIVE');
INSERT INTO chessonline.user (pid, first_name, last_name, login, middle_name, password, status) VALUES (2, 'Ivan', 'Lavrinovich', '2', 'V', '$2y$08$yXiOvQjOofgtwwMOZh8fV.jmByADquMAdZJNRRxM2MNfG4h25RzA2', 'ACTIVE');