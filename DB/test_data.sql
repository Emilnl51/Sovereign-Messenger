use sovereign_messenger;
insert into users (userName, passwordHash) values ('testUser', 'testPassword');
insert into users (userName, passwordHash) values ('t', 'p');
insert into users (userName, passwordHash) values ('a', 'a');
insert into users (userName, passwordHash) values ('b', 'b');
select * from users;