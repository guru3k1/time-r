DROP TABLE IF EXISTS TASK;
DROP TABLE IF EXISTS RANGE_DATES;

create sequence SQ_USER_ID;
create sequence SQ_TASK_ID;
create sequence SQ_RANGE_ID;


create table task(
    user_id int PRIMARY KEY NOT NULL,
    task_id int NOT NULL,
    task_name VARCHAR(30),
    task_description VARCHAR(30),
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

insert into TASK (user_Id,task_Id,task_name,task_description,start_date,close_date, is_active) VALUES(SQ_USER_ID.nextval,1,'Test Task', 'Dummy task to test DB',systime, systime,1);
insert into RANGE_DATES (range_id, user_id, task_id, start_date, close_date, is_active) values(SQ_RANGE_ID.nextval,1,1,parsedatetime('16-04-2020 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('16-04-2020 20:05:25.100', 'dd-MM-yyyy hh:mm:ss.SS'),1);

insert into RANGE_DATES (range_id, user_id, task_id, start_date, close_date, is_active) values(SQ_RANGE_ID.nextval,1,1,parsedatetime('17-04-2020 09:05:22.690', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('17-04-2020 11:35:25.100', 'dd-MM-yyyy hh:mm:ss.SS'),1);