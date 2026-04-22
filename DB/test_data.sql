use sovereign_messenger;
insert into users (userName, passwordHash) values ('testUser', 'testPassword');
insert into users (userName, passwordHash) values ('t', 'p');
select * from users;