/* Users Table Related Query */
drop table if exists `users`;

create table `users` (
    `id`   INTEGER not null auto_increment,
    `name` varchar(256) not null,
    `last_name` varchar(256),
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

INSERT INTO PUBLIC.USERS (NAME, LAST_NAME, USER_NAME, PASSWORD, LOCATION_ID, SCHOOL_NAME, PHONE_NUMBER, GENDER, PHOTO)
    VALUES ('Mustafa', 'Poya', 'Mustafa', '123456', 1, 'Qazanchi Baba', '0782552663', 'M', null);

INSERT INTO PUBLIC.USERS (NAME, LAST_NAME, USER_NAME, PASSWORD, LOCATION_ID, SCHOOL_NAME, PHONE_NUMBER, GENDER, PHOTO)
    VALUES ('علی', 'احمدی', 'ali', '123', 2, 'مزار شریف', '0782552663', 'M', '');


/* Users Table Related Query */
drop table if exists `USER_EMAILS`;

create table USER_EMAILS (
    `ID`      INTEGER AUTO_INCREMENT,
    `USER_ID` INTEGER,
    `EMAIL`   VARCHAR(64),
    `PHONE`   VARCHAR(16),
    `TITLE`   VARCHAR(256),
    `CONTENT` CLOB,
    primary key (`ID`),
    foreign key (USER_ID) references USERS(ID)
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
   primary key (`ID`),
   foreign key (USER_ID) references USERS(ID)
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

/* Tutorials Table Related Query */
drop table if exists `faculties`;

create table `faculties` (
    `id`            INTEGER  auto_increment,
    `name`          VARCHAR(128) not null,
    `department`    VARCHAR(128) not null,
    `code`          CHAR(16)     not null,
    `minimum_grade` INTEGER      not null,
    `uni_id`        INTEGER      not null,
    `admission`     INTEGER      not null,
    primary key (`id`)
);

/* Tutorials Table Related Query */
drop table if exists `news`;

create table `news` (
    `id`  INTEGER auto_increment,
    `category` VARCHAR(512),
    `title`  VARCHAR(512),
    `description`  CLOB(256),
    `url_link` VARCHAR(256),
    `content`  CLOB,
    `NEWS_DATE` datetime null,

    primary key (`id`)
);

INSERT INTO PUBLIC.NEWS (`id`, `category`, `title`, `description`, `url_link`, `content`, `news_date`)
VALUES
    (1, 'important', 'Important News', 'description', 'http://www.golbarg.net/news/', '<html><head>\r\n<meta http-equiv="Content-Type" content="text/html; charset=utf-8">\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p>در حال آماده سازی</p>\r\n\r\n</body></html>', '2023-02-25'),
    (2, 'کتگوری', 'عنوان', 'توضیحات', 'google.com', 'مطلب', '2023-02-26');

/* Tutorials Table Related Query */
drop table if exists `tutorials`;

create table `tutorials`
(
    `id` INTEGER not null auto_increment,
    `title` VARCHAR(256),
    primary key (`id`)
);

INSERT INTO tutorials (ID, TITLE) VALUES (1, 'مشاوره');
INSERT INTO tutorials (ID, TITLE) VALUES (2, 'آشنایی با رشته ها');
INSERT INTO tutorials (ID, TITLE) VALUES (3, 'راهنمایی استفاده از برنامه');

/* Tutorials Table Related Query */
drop table if exists `tutorial_details`;

create table `tutorial_details` (
    `ID`              INTEGER not null auto_increment,
    `TUTORIAL_ID`     INTEGER not null,
    `TITLE`           VARCHAR(256),
    `SHORT_DESC`      CLOB,
    `FULL_DESC`       CLOB,
    `SHORT_DESC_ICON` VARCHAR(256),
    `ICON_NAME`       VARCHAR(256),
    primary key (`ID`)
);

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

DELETE FROM `locations` where `id` is not null;

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

/* Questions Table Related Query */
drop table if exists `QUESTION_SUBJECTS`;

create table QUESTION_SUBJECTS (
   ID               INTEGER AUTO_INCREMENT,
   SUBJECT_TYPE      VARCHAR    not null,
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
    primary key (`ID`)
);


/* Tutorials Table Related Query */
drop table if exists `RESOURCE_CATEGORIES`;

create table `RESOURCE_CATEGORIES` (
    `ID`    INTEGER AUTO_INCREMENT,
    `TITLE` VARCHAR,

    primary key (`ID`)
);

INSERT INTO PUBLIC.RESOURCE_CATEGORIES (TITLE) VALUES ('کتاب های صنف دوازدهم');
INSERT INTO PUBLIC.RESOURCE_CATEGORIES (TITLE) VALUES ('کتاب های صنف یازدهم');
INSERT INTO PUBLIC.RESOURCE_CATEGORIES (TITLE) VALUES ('کتاب های صنف دهم');
INSERT INTO PUBLIC.RESOURCE_CATEGORIES (TITLE) VALUES ('کتاب های صنف نهم');

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
    foreign key (CATEGORY_ID) references RESOURCE_CATEGORIES(ID)
);

insert into PUBLIC.RESOURCES (ID, CATEGORY_ID, NAME, FILE_NAME, DESCRIPTION, SIZE)
values  (3, 1, 'کتاب دری صنف دوازدهم', '12/G12_dr_Dari Language.pdf', null, 2.60),
        (4, 1, 'کتاب جغرافیه صنف دوازدهم', '12/G12_dr_geography.pdf', null, 5.60),
        (5, 1, 'کتاب تاریخ صنف دوازدهم', '12/G12_dr_History.pdf', null, 10.30),
        (8, 1, 'کتاب ریاضی صنف دوازدهم', '12/G12_dr_Math.pdf', null, 6.70),
        (6, 1, 'کتاب تعلیم و تربیه اسلامی (مذهب جعفری) صنف دوازدهم', '12/G12_dr_Islamic Education (J).pdf', null, 1.80),
        (7, 1, 'کتاب تعلیم و تربیه اسلامی صنف دوازدهم', '12/G12_dr_Islamic Education.pdf', null, 1.60),
        (9, 1, 'کتاب پشتو صنف دوازدهم', '12/G12_dr_Pashto Language.pdf', null, 5.90),
        (10, 1, 'کتاب فزیک صنف دوازدهم', '12/G12_dr_Physics.pdf', null, 4.90),
        (2, 1, 'کتاب کیمیا صنف دوازدهم', '12/G12_dr_Chemistry.pdf', '', 39.60),
        (11, 2, 'کتاب بیولوژی صنف یازدهم', '11/G11_dr_Biology.pdf', null, 11.06),
        (26, 3, 'کتاب فرهنگ صنف دهم', '10/G10_dr_Calture.pdf', null, 8.70),
        (27, 3, 'کتاب کیمیا صنف دهم', '10/G10_dr_Chemisty.pdf', null, 8.30),
        (28, 3, 'کتاب کمپیوتر صنف دهم', '10/G10_dr_Computer.pdf', null, 10.20),
        (29, 3, 'کتاب زبان و ادبیات دری صنف دهم', '10/G10_dr_Dari Language.pdf', null, 1.60),
        (30, 3, 'کتاب جغرافیه صنف دهم', '10/G10_dr_Geography.pdf', null, 11.30),
        (31, 3, 'کتاب جیولوژی صنف دهم', '10/G10_dr_Geology.pdf', null, 4.30),
        (32, 3, 'کتاب تاریخ صنف دهم', '10/G10_dr_History.pdf', null, 4.50),
        (33, 3, 'کتاب تعلیم و تربیه اسلامی صنف دهم (مذهب جعفری)', '10/G10_dr_Islamic Education (J).pdf', null, 1.17),
        (34, 3, 'کتاب تعلیم و تربیه اسلامی صنف دهم', '10/G10_dr_Islamic Education.pdf', null, 1.50),
        (35, 3, 'کتاب پشتو صنف دهم', '10/G10_dr_Pashto Language.pdf', null, 5.30),
        (36, 3, 'کتاب فزیک صنف دهم', '10/G10_dr_Physics.pdf', null, 6.50),
        (37, 3, 'کتاب تفسیر شریف صنف دهم', '10/G10_dr_Taphseer.pdf', null, 1.40),
        (12, 2, 'کتاب فرهنگ صنف یازدهم', '11/G11_dr_Calture.pdf', null, 8.40),
        (13, 2, 'کتاب کیمیا صنف یازدهم', '11/G11_dr_Chemistry.pdf', null, 9.20),
        (14, 2, 'کتاب تعلیمات مدنی صنف یازدهم', '11/G11_dr_Civics.pdf', null, 3.50),
        (15, 2, 'کتاب کمپیوتر صنف یازدهم', '11/G11_dr_Computer.pdf', null, 7.10),
        (16, 2, 'کتاب زبان و ادبیات دری صنف یازدهم', '11/G11_dr_Dari Language.pdf', null, 6.20),
        (17, 2, 'کتاب جغرافیه صنف یازدهم', '11/G11_dr_geography.pdf', null, 10.50),
        (18, 2, 'کتاب تاریخ صنف یازدهم', '11/G11_dr_History.pdf', null, 42.80),
        (19, 2, 'کتاب تعلیم و تربیه اسلامی صنف یازدهم (مذهب جعفری)', '11/G11_dr_Islamic Education (J).pdf', null, 0.99),
        (20, 2, 'کتاب تعلیم و تربیه اسلامی صنف یازدهم', '11/G11_dr_Islamic Education.pdf', null, 1.50),
        (21, 2, 'کتاب ریاضی صنف یازدهم', '11/G11_dr_Math.pdf', null, 7.60),
        (22, 2, 'کتاب پشتو صنف یازدهم', '11/G11_dr_Pashto Language.pdf', null, 5.07),
        (23, 2, 'کتاب فزیک صنف یازدهم', '11/G11_dr_Physics.pdf', null, 3.80),
        (24, 2, 'کتاب تفسیر شریف صنف یازدهم', '11/G11_dr_Taphseer.pdf', null, 1.30),
        (25, 3, 'کتاب بیولوژی صنف دهم', '10/G10_dr_Biology.pdf', null, 5.70),
        (38, 4, 'کتاب هنر صنف نهم', '9/G9_dr_Arts.pdf', null, 11.60),
        (39, 4, 'کتاب کیمیا صنف نهم', '9/G9_dr_Chemistry.pdf', null, 1.60),
        (40, 4, 'کتاب تعلیمات مدنی صنف نهم', '9/G9_dr_Civics.pdf', null, 2.06),
        (41, 4, 'کتاب زبان و ادبیات دری صنف نهم', '9/G9_dr_Dari Language.pdf', null, 3.60),
        (42, 4, 'کتاب جغرافیه صنف نهم', '9/G9_dr_geography.pdf', null, 11.10),
        (43, 4, 'کتاب تاریخ صنف نهم', '9/G9_dr_History.pdf', null, 3.70),
        (44, 4, 'کتاب تعلیم و تربیه اسلامی صنف نهم (مذهب جعفری)', '9/G9_dr_Islamic Studies (J).pdf', null, 2.80),
        (45, 4, 'کتاب تعلیم و تربیه اسلامی صنف نهم', '9/G9_dr_Islamic Studies.pdf', null, 2.90),
        (46, 4, 'کتاب ریاضی صنف نهم', '9/G9_dr_Math.pdf', null, 43.29),
        (47, 4, 'کتاب فزیک صنف نهم', '9/G9_dr_Physic.pdf', null, 3.49),
        (48, 4, 'کتاب حرفه صنف نهم', '9/G9_dr_Profession.pdf', null, 4.52),
        (49, 4, 'کتاب تعلیم قرآن کریم صنف نهم', '9/G9_dr_Tajweed.pdf', null, 22.12),
        (1, 1, 'کتاب بیولوژی صنف دوازدهم', '12/G12_dr_Biology.pdf', 'null', 6.80);


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

insert into PUBLIC.UNIVERSITIES (ID, TITLE)
values  (1, 'کابل'),
        (2, 'طبی کابل'),
        (3, 'تعلیم و تربیه کابل'),
        (4, 'پولیتخنیک'),
        (5, 'هرات'),
        (6, 'بلخ'),
        (7, 'البیرونی'),
        (8, 'کندهار'),
        (9, 'تخار'),
        (10, 'لغمان'),
        (11, 'ننگرهار'),
        (12, 'شيخ زايد خوست'),
        (13, 'کنر'),
        (14, 'پکتیا');

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


