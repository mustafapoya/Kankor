/* Users Table Related Query */
drop table if exists `USERS`;

create table `USERS` (
    `ID`   INTEGER not null auto_increment,
    `NAME` varchar(256) not null,
    `LAST_NAME` varchar(256),
    `USER_NAME` varchar(128),
    `PASSWORD` varchar(128),
    `LOCATION_ID` INTEGER,
    `SCHOOL_NAME` varchar(256),
    `PHONE_NUMBER` varchar(16),
    `GENDER` ENUM('M', 'F', 'U'),
    `PHOTO` varchar(256),
    primary key (`ID`)
);

create unique index UNIQUE_NAME on USERS (user_name);

INSERT INTO PUBLIC.USERS (NAME, LAST_NAME, USER_NAME, PASSWORD, LOCATION_ID, SCHOOL_NAME, PHONE_NUMBER, GENDER, PHOTO)
    VALUES ('Mustafa', 'Poya', 'Mustafa', '123456', 1, 'Qazanchi Baba', '0782552663', 'M', null);

/* User Emails Table Related Query */
drop table if exists `USER_EMAILS`;

create table USER_EMAILS (
    `ID`      INTEGER AUTO_INCREMENT,
    `USER_ID` INTEGER,
    `EMAIL`   VARCHAR(64),
    `PHONE`   VARCHAR(16),
    `TITLE`   VARCHAR(256),
    `CONTENT` CLOB,
    primary key (`ID`)
--     ,foreign key (USER_ID) references USERS(ID) ON delete no action
);

/* User Kankor Form Table Related Query*/
drop table if exists `USER_KANKOR_FORMS`;

create table `USER_KANKOR_FORMS`(
   `ID` INTEGER not null auto_increment,
   `USER_ID` INTEGER not null,
   `FATHER_NAME` varchar(256) not null,
   `GRAND_FATHER_NAME` varchar(256) not null,
   `CURRENT_PROVINCE` INTEGER,
   `CURRENT_DISTRICT` varchar(256),
   `CURRENT_VILLAGE` varchar(256),
   `ORIGIN_PROVINCE` INTEGER,
   `ORIGIN_DISTRICT` varchar(256),
   `ORIGIN_VILLAGE` varchar(256),
   `GRADUATE_YEAR` INTEGER,
   `TAZKIRA_ID` varchar(256),
   `LANGUAGE` varchar(256),
   primary key (`ID`)
--     ,foreign key (USER_ID) references USERS(ID) ON delete no action
);

/* Exams Table Related Query */
drop table if exists `EXAMS`;

create table EXAMS (
   ID               INTEGER AUTO_INCREMENT,
   USER_ID          INTEGER       not null,
   EXAM_DATE        VARCHAR(256)  not null,
   EXAM_DURATION    VARCHAR(128)  not null,
   MATH_SCORE       DECIMAL(5, 2) not null,
   NATURAL_SCORE    DECIMAL(5, 2) not null,
   SOCIAL_SCORE     DECIMAL(5, 2) not null,
   ALSANA_SCORE     DECIMAL(5, 2) not null,
   PASSED_FIELD     VARCHAR(256)  not null,
   primary key (`ID`)
--    ,foreign key (USER_ID) references USERS(ID) ON DELETE NO ACTION
);

/* Configs Table Related Query */
drop table if exists `CONFIGS`;

create table `CONFIGS` (
     `ID` INTEGER not null auto_increment,
     `CONF_KEY` varchar(256),
     `CONF_VALUE` varchar(256),
     `CREATED_AT` timestamp,
     `UPDATED_AT` timestamp,
     primary key (`ID`)
);

/* Tutorials Table Related Query */
drop table if exists `NEWS`;

create table `NEWS` (
    `ID`  INTEGER auto_increment,
    `CATEGORY` VARCHAR(512),
    `TITLE`  VARCHAR(512),
    `DESCRIPTION`  CLOB(256),
    `URL_LINK` VARCHAR(256),
    `CONTENT`  CLOB,
    `NEWS_DATE` datetime null,
    primary key (`ID`)
);

INSERT INTO PUBLIC.NEWS (`ID`, `CATEGORY`, `TITLE`, `DESCRIPTION`, `URL_LINK`, `CONTENT`, `NEWS_DATE`)
VALUES
    (1, 'important', 'Important News', 'description', 'http://www.golbarg.net/news/', '<html><head>\r\n<meta http-equiv="Content-Type" content="text/html; charset=utf-8">\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p>در حال آماده سازی</p>\r\n\r\n</body></html>', '2023-02-25'),
    (2, 'کتگوری', 'عنوان', 'توضیحات', 'google.com', 'مطلب', '2023-02-26');

/* Tutorials Table Related Query */
drop table if exists `TUTORIALS`;

create table `TUTORIALS`
(
    `ID` INTEGER not null auto_increment,
    `TITLE` VARCHAR(256),
    primary key (`ID`)
);

/* Tutorials Table Related Query */
drop table if exists `TUTORIAL_DETAILS`;

create table `TUTORIAL_DETAILS` (
    `ID`              INTEGER not null auto_increment,
    `TUTORIAL_ID`     INTEGER not null,
    `TITLE`           VARCHAR(256),
    `SHORT_DESC`      CLOB,
    `FULL_DESC`       CLOB,
    `SHORT_DESC_ICON` VARCHAR(256),
    `ICON_NAME`       VARCHAR(256),
    primary key (`ID`)
--  ,foreign key (TUTORIAL_ID) references TUTORIALS(ID)
);

drop table if exists `KANKOR_RESULTS`;

create table KANKOR_RESULTS (
    `ID`                INTEGER not null,
    `KANKOR_YEAR`       INTEGER,
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

/* Locations Table Related Query */
drop table if exists `LOCATIONS`;

create table `LOCATIONS` (
    `ID` INTEGER not null auto_increment,
    `TYPE` INTEGER NOT NULL,
    `ZONE` varchar(16) ,
    `PARENT_ID` INTEGER,
    `NAME` varchar(127) NOT NULL,
    `NAME_DA` varchar(127) NOT NULL,
    primary key (`ID`)
);

DELETE FROM `LOCATIONS` where `ID` is not null;

/* Tutorials Table Related Query */
drop table if exists `TUTORIALS`;

create table `TUTORIALS` (
    `ID` INTEGER not null auto_increment,
    `TITLE` VARCHAR(256),
    primary key (`ID`)
);

/* Tutorials Table Related Query */
drop table if exists `TUTORIAL_DETAILS`;

create table TUTORIAL_DETAILS (
    `ID`   INTEGER not null auto_increment,
    `TUTORIAL_ID`   INTEGER not null,
    `TITLE`         VARCHAR(256),
    `SHORT_DESC`    CLOB,
    `FULL_DESC`     CLOB,
    `SHORT_DESC_ICON` VARCHAR(256),
    `ICON_NAME`     VARCHAR(256),
    primary key (`ID`)
--  ,foreign key (TUTORIAL_ID) references TUTORIAL_DETAILS(ID)
);

/* Questions Table Related Query */
drop table if exists `QUESTION_SUBJECTS`;

create table QUESTION_SUBJECTS (
   ID               INTEGER AUTO_INCREMENT,
   SUBJECT_TYPE     VARCHAR    not null,
   SUBJECT_KEY      VARCHAR    not null,
   SUBJECT_TITLE_PE VARCHAR    not null,
   primary key (`ID`)
);

/* Questions Table Related Query */
drop table if exists `QUESTIONS`;

create table QUESTIONS
(
    ID              INTEGER AUTO_INCREMENT,
    SUBJECT_ID      INTEGER,
    QUESTION        CLOB not null,
    CHOICE1         CLOB not null,
    CHOICE2         CLOB not null,
    CHOICE3         CLOB not null,
    CHOICE4         CLOB not null,
    CORRECT_CHOICE  INTEGER not null,
    RELATED_CLASS   VARCHAR,
    QUESTION_TYPE   VARCHAR,
    QUESTION_UPDATE INTEGER,
    BOOKMARK        BOOLEAN,
    primary key (`ID`),
--     foreign key (SUBJECT_ID) references QUESTION_SUBJECTS(ID)
);

/* Tutorials Table Related Query */
drop table if exists `RESOURCE_CATEGORIES`;

create table `RESOURCE_CATEGORIES` (
    `ID`    INTEGER AUTO_INCREMENT,
    `TITLE` VARCHAR,
    primary key (`ID`)
);

/* Tutorials Table Related Query */
drop table if exists `RESOURCES`;

create table `RESOURCES` (
    ID          INTEGER AUTO_INCREMENT,
    CATEGORY_ID INTEGER NOT NULL,
    NAME        VARCHAR(256),
    FILE_NAME   VARCHAR(256),
    DESCRIPTION CLOB,
    SIZE        DECIMAL(4, 2),
    primary key (`ID`),
--     foreign key (CATEGORY_ID) references RESOURCE_CATEGORIES(ID)
);

/* Reviews Table Related Query */
drop table if exists `REVIEWS`;

create table REVIEWS (
    ID                INTEGER AUTO_INCREMENT,
    QUESTION_ID       INTEGER,
    SUBJECT_NAME      VARCHAR(256),

    primary key (`ID`)
);

insert into PUBLIC.REVIEWS (ID, QUESTION_ID, SUBJECT_NAME)
values  (1, 21, 'history'),
        (2, 1, 'history'),
        (3, 1, 'history'),
        (4, 1, 'history');

/* Updates Table Related Query */
drop table if exists `UNIVERSITIES`;

create table `UNIVERSITIES` (
    ID INTEGER AUTO_INCREMENT,
    TITLE VARCHAR(256) not null,
    primary key (`ID`)
);

/* University Faculties Table Related Query */
drop table if exists `UNIVERSITY_FACULTIES`;

create table `UNIVERSITY_FACULTIES` (
     `ID`            INTEGER  auto_increment,
     `UNIVERSITY_ID` INTEGER      not null,
     `NAME`          VARCHAR(128) not null,
     `DEPARTMENT`    VARCHAR(128) not null,
     `CODE`          VARCHAR(16)  not null,
     `MINIMUM_GRADE` INTEGER      not null,
     `ADMISSION`     INTEGER      not null,
     primary key (`ID`)
--   ,foreign key (UNIVERSITY_ID) references UNIVERSITIES(ID)
);

/* Updates Table Related Query */
drop table if exists `UPDATES`;

create table `UPDATES` (
    ID          INTEGER AUTO_INCREMENT,
    CONTENT     VARCHAR(256),
    DESCRIPTION CLOB,
    primary key (`ID`)
);

insert into PUBLIC.UPDATES (ID, CONTENT, DESCRIPTION)
values  (0, 'سوالات اولیه', 'این گزینه شامل سوالاتی است که از قبل در داخل برنامه وجود داشته است'),
        (1, 'سوالات تاریخ', '<html>
	<body dir = "rtl">
		<p>
		این بخش دانلود شامل 150 سوال از بخش کیمیا میباشد که شامل
		</p>
	</body>
</html>'),
        (2, 'سوالات کیمیا', 'این بخش شامل سوالاتی کیمیا است');


