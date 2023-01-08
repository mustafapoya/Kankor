/* Users Table Related Query */
drop table if exists `users`;

create table `users` (
    `id`   INTEGER not null auto_increment,
    `name` varchar(256) not null,
    `last_name` varchar(256),
    `father_name` varchar(256) not null,
    `user_name` varchar(128),
    `password` varchar(128),
    `location_id` INTEGER,
    `school_name` varchar(256),
    `phone_number` varchar(16),
    `gender` ENUM('M', 'F', 'U'),
    `photo` varchar(256),
    primary key (`id`)
);

create unique index UNIQUE_NAME on users (user_name);

INSERT INTO PUBLIC.USERS (NAME, LAST_NAME, FATHER_NAME, USER_NAME, PASSWORD, LOCATION_ID, SCHOOL_NAME, PHONE_NUMBER, GENDER, PHOTO)
    VALUES ('Mustafa', 'Poya', 'Karim', 'Mustafa', '123456', 1, 'Qazanchi Baba', '0782552663', 'M', null);

INSERT INTO PUBLIC.USERS (NAME, LAST_NAME, FATHER_NAME, USER_NAME, PASSWORD, LOCATION_ID, SCHOOL_NAME, PHONE_NUMBER, GENDER, PHOTO)
    VALUES ('علی', 'احمدی', 'محمد', 'ali', '123', 2, 'مزار شریف', '0782552663', 'M', '');

/* Users Table Related Query */
drop table if exists `EMAILS`;

create table EMAILS (
    `ID`      INTEGER AUTO_INCREMENT,
    `EMAIL`   VARCHAR(64),
    `PHONE`   VARCHAR(16),
    `TITLE`   VARCHAR(256),
    `CONTENT` CLOB,
    primary key (`ID`)
);

/* Configs Table Related Query */
drop table if exists `configs`;

create table `configs` (
     `id` INTEGER not null auto_increment,
     `conf_key` varchar(256),
     `conf_value` varchar(256),
     `created_at` timestamp,
     `updated_at` timestamp,
     primary key (`id`)
);

/* Configs Table Related Query */
drop table if exists `EXAMS`;

create table EXAMS (
    ID               INTEGER AUTO_INCREMENT,
    USER_ID          INTEGER       not null,
    KONKOR_ID        VARCHAR(128)  not null,
    EXAM_DATE        VARCHAR(256)  not null,
    EXAM_DURATION    VARCHAR(128)  not null,
    MATH_GRADE       DECIMAL(5, 2) not null,
    NATURAL_GRADE    DECIMAL(5, 2) not null,
    SOCIAL_GRADE     DECIMAL(5, 2) not null,
    ALSANA_GRADE     DECIMAL(5, 2) not null,
    EXAM_GRADE       DECIMAL(5, 2) not null,
    EXAM_PASSEDFIELD VARCHAR(256)  not null,
    primary key (`ID`)
);





/* Kankor Form Table Related Query */
drop table if exists `kankor_forms`;

create table `kankor_forms`(
    `id` INTEGER not null auto_increment,
    `user_id` INTEGER not null,
    `name` varchar(256) not null,
    `last_name` varchar(256),
    `father_name` varchar(256) not null,
    `grand_father_name` varchar(256) not null,
    `current_province` INTEGER,
    `current_district` varchar(256),
    `current_village` varchar(256),
    `original_province` INTEGER,
    `original_district` varchar(256),
    `original_village` varchar(256),
    `graduate_year` INTEGER,
    `school_name` varchar(256),
    `tazkira_id` varchar(256),
    `gender` ENUM('M', 'F', 'U'),
    `language` varchar(256),
    primary key (`id`)
);

/* Tutorials Table Related Query */
drop table if exists `KANKOR_RESULTS`;

create table KANKOR_RESULTS (
    `ID`                INTEGER not null,
    `KANKOR_ID`         VARCHAR(256),
    `NAME`              VARCHAR,
    `FATHER_NAME`       VARCHAR,
    `GRAND_FATHER_NAME` VARCHAR,
    `SCHOOL`            VARCHAR,
    `PROVINCE`          VARCHAR(256),
    `SCORE`             DECIMAL,
    `RESULT`            VARCHAR(256),
    primary key (`ID`)
);

insert into PUBLIC.KANKOR_RESULTS (ID, KANKOR_ID, NAME, FATHER_NAME, GRAND_FATHER_NAME, SCHOOL, PROVINCE, SCORE, RESULT)
values  (1, 'E16619590', 'مصطفی', 'کریم', 'محمد', 'خاتم الانبیا', 'بغلان', 281, 'کامپیوتر ساینس هرات');

/* Tutorials Table Related Query */
drop table if exists `MAKTAB_RESULTS`;

create table MAKTAB_RESULTS (
    `ID`            INTEGER AUTO_INCREMENT,
    `KANKOR_ID`     VARCHAR(64),
    `NAME`          VARCHAR(128),
    `SCHOOL`        VARCHAR(128),
    `PROVINCE`      VARCHAR(128),
    `SCORE`         DOUBLE,
    `PASSED_FIELD`  VARCHAR(128),
    `EXAM_DATE`     VARCHAR(128),
    `EXAM_DURATION` VARCHAR(128),
    primary key (`ID`)
);

insert into PUBLIC.MAKTAB_RESULTS (ID, KANKOR_ID, NAME, SCHOOL, PROVINCE, SCORE, PASSED_FIELD, EXAM_DATE, EXAM_DURATION)
values  (1, '211223', 'احمدالله احمدی', 'خاتم الانبیا', 'کابل', 312.5, 'انجینری', '02/20/2015', '01:22:30'),
        (2, '12324', 'بسم الله محمدی', 'تجربوی', 'هرات', 300, 'اقتصاد', '01/20/2015', '02:00:00'),
        (3, '1353356', 'اکبر', 'مولانا جلال الدین بلخی', 'مزار', 290, 'کامپیوتر ساینس', '01/10/2015', '01:30:00'),
        (4, '14567543', 'نوید', 'خاتم الانبیا', 'کابل', 250, 'زراعت', '05/01/2014', '02:02:02');


/* Locations Table Related Query */
drop table if exists `locations`;

create table `locations` (
    `id` INTEGER not null auto_increment,
    `type` INTEGER NOT NULL,
    `zone` varchar(16) ,
    `parent_id` INTEGER,
    `name` varchar(127) NOT NULL,
    `name_da` varchar(127) NOT NULL,
    primary key (`id`)
);

DELETE FROM `locations` where "id" is not null;

/* Tutorials Table Related Query */
drop table if exists `tutorials`;

create table `tutorials` (
    `id` INTEGER not null auto_increment,
    `title` VARCHAR(256),
    primary key (`id`)
);

INSERT INTO tutorials (ID, TITLE) VALUES (1, 'مشاوره');
INSERT INTO tutorials (ID, TITLE) VALUES (2, 'آشنایی با رشته ها');
INSERT INTO tutorials (ID, TITLE) VALUES (3, 'راهنمایی استفاده از برنامه');

/* Tutorials Table Related Query */
drop table if exists `tutorial_details`;

create table tutorial_details (
    `ID`   INTEGER not null auto_increment,
    `TUTORIAL_ID`   INTEGER not null,
    `TITLE`         VARCHAR(256),
    `SHORT_DESC`    CLOB,
    `FULL_DESC`     CLOB,
    `SHORT_DESC_ICON` VARCHAR(256),
    `ICON_NAME`     VARCHAR(256),

    primary key (`ID`)
);