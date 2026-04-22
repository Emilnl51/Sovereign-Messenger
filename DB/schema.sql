show databases;
create database sovereign_messenger;
use sovereign_messenger;
Show tables;
create table users (
	userID int auto_increment primary key,
	userName varchar(50) not null unique,
	passwordHash varchar(255) not null
);
create table messages (
	messageID int auto_increment primary key,
    senderID int not null,
    recipientID int not null,
    content text not null,
    timestamp datetime not null default current_timestamp,
    isRead boolean default false,
    foreign key(senderID) references users(userID),
    foreign key(recipientID) references users(userID),
    index chat_flow_idx (senderID, recipientID)
);