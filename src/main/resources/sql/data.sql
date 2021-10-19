insert into CHESS_ONLINE_ROLE(pid, description) values ('ADMIN', 'Вершина пищевой цепи не имеющее врагов');
insert into CHESS_ONLINE_ROLE(pid, description) values ('USER', 'Низшее звено пищевой цепи');

insert into CHESS_ONLINE_USER(login, password, wins) values ('1', '$2a$12$HHhnWYR1egi934K98E6fVODBNFYJGHSHvijEs7IKd19noWgDMx9w.', '0');
insert into CHESS_ONLINE_USER(login, password, wins) values ('2', '$2a$12$jarkdQlbp9s3o2Xrlp7lN.IKvBgzU2v3tkhmfrw1HTY3JO6zKYch6', '0');

insert into CHESS_ONLINE_USER_ROLES(USERS_LOGIN, ROLES_PID) values ('1', 'ADMIN');
insert into CHESS_ONLINE_USER_ROLES(USERS_LOGIN, ROLES_PID) values ('2', 'ADMIN');
