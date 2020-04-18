DROP TABLE IF EXISTS TASK;
DROP TABLE IF EXISTS RANGE_DATES;

create sequence SQ_USER_ID;
create sequence SQ_TASK_ID;
create sequence SQ_RANGE_ID;

create table user(
    user_id int PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password_hash VARCHAR(60) NOT NULL
);

create table task(
    user_id int NOT NULL,
    task_id int PRIMARY KEY NOT NULL,
    task_name VARCHAR(30),
    task_description VARCHAR(50),
    start_date TIMESTAMP,
    close_date TIMESTAMP,
    is_active CHAR
);

create table range_dates(
    range_id int PRIMARY KEY NOT NULL,
    user_id int NOT NULL,
    task_id int NOT NULL,
    start_date TIMESTAMP,
    close_date TIMESTAMP,
    is_active CHAR
);
insert into USER (user_id, first_name, last_name, email, password_hash) values (SQ_TASK_ID.nextval,'Dummy','User','dummyUser@gmail.com','azsde3erfd235ged4r422jhgdwe84jhg3487r');
insert into TASK (user_Id,task_Id,task_name,task_description,start_date,close_date, is_active) VALUES(1,SQ_TASK_ID.nextval,'Test Task', 'Dummy task to test DB',systime, systime,1);
insert into RANGE_DATES (range_id, user_id, task_id, start_date, close_date, is_active) values(SQ_RANGE_ID.nextval,1,1,current_timestamp,current_timestamp,1);
