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

insert into PUBLIC.faculties (id, name, department, code, minimum_grade, uni_id, admission)
values  (1, 'اقتصاد', 'عمومی', '001', 290, 1, 300),
        (2, 'شرعیات صرف برای اناث', 'عمومی', '002', 267, 1, 80),
        (3, 'زمین شناسی', 'هایدرومترولوژی', '003', 244, 1, 60),
        (4, 'زمین شناسی', 'جیولوجی', '004', 288, 1, 60),
        (5, 'زمین شناسی', 'محیط زیست', '005', 275, 1, 70),
        (6, 'زمین شناسی', 'جغرافیه', '006', 237, 1, 80),
        (7, 'حقوق', 'حقوق', '007', 293, 1, 300),
        (8, 'علوم اجتماعی', 'تاریخ عمومی', '008', 239, 1, 80),
        (9, 'علوم اجتماعی', 'بشر شناسی و باستان شناسی', '009', 242, 1, 60),
        (10, 'علوم اجتماعی', 'فلسفه و جامعه شناسی', '010', 268, 1, 70),
        (11, 'هنرهای زیبا', 'تیاتر', '011', 217, 1, 90),
        (12, 'هنرهای زیبا', 'موسیقی', '012', 233, 1, 50),
        (13, 'هنرهای زیبا', 'سینما', '013', 241, 1, 50),
        (14, 'هنرهای زیبا', 'مجسمه', '014', 215, 1, 50),
        (15, 'هنرهای زیبا', 'گرافیک', '015', 256, 1, 50),
        (16, 'هنرهای زیبا', 'نقاشی', '016', 234, 1, 50),
        (17, 'زبان و ادبیات', 'فرانسوی', '017', 258, 1, 40),
        (18, 'زبان و ادبیات', 'آلمانی', '018', 258, 1, 40),
        (19, 'زبان و ادبیات', 'جاپانی', '019', 235, 1, 40),
        (20, 'زبان و ادبیات', 'انگلیسی', '020', 270, 1, 100),
        (21, 'زبان و ادبیات', 'ترکی', '021', 250, 1, 70),
        (22, 'زبان و ادبیات', 'دری', '022', 257, 1, 120),
        (23, 'زبان و ادبیات', 'پشتو', '023', 241, 1, 120),
        (24, 'زبان و ادبیات', 'عربی', '024', 242, 1, 60),
        (25, 'زبان و ادبیات', 'روسی', '025', 238, 1, 70),
        (26, 'روانشناسی و علوم تربیتی', 'اداره و منجمنت تربیتی', '026', 259, 1, 50),
        (27, 'روانشناسی و علوم تربیتی', 'روانشناسی', '027', 263, 1, 50),
        (28, 'روانشناسی و علوم تربیتی', 'پیداگوژی', '028', 231, 1, 50),
        (29, 'ژورنالیزم', 'عمومی', '029', 279, 1, 150),
        (30, 'انجنیری', 'سیول', '030', 309, 1, 100),
        (31, 'انجنیری', 'برق', '031', 294, 1, 70),
        (32, 'انجنیری', 'میخانیک', '032', 282, 1, 70),
        (33, 'انجنیری', 'مهندسی', '033', 301, 1, 50),
        (34, 'فارمسی', 'عمومی', '034', 283, 1, 140),
        (35, 'کامپیوتر ساینس', 'عمومی', '035', 291, 1, 230),
        (36, 'وترنری', 'عمومی', '036', 236, 1, 120),
        (37, 'زراعت', 'عمومی', '037', 271, 1, 300),
        (38, 'ساینس', 'عمومی', '038', 283, 1, 500),
        (39, 'اداره عامه', 'عمومی', '039', 276, 1, 100),
        (40, 'کیمیا', 'عمومی', '040', 248, 1, 100),
        (41, 'طب معالجوی', 'عمومی', '041', 309, 2, 180),
        (42, 'ستوماتولوژی', 'عمومی', '042', 295, 2, 140),
        (43, 'صحت عامه', 'عمومی', '043', 283, 2, 120),
        (44, 'نرسنگ', 'عمومی', '044', 265, 2, 140),
        (45, 'متمم طبی', 'تکنالوژی لابراتوار', '045', 281, 2, 50),
        (46, 'متمم طبی', 'انستیزی', '046', 270, 2, 50),
        (47, 'طب نظامی', 'معالجوی', '047', 305, 2, 40),
        (48, 'طب نظامی', 'صحت عامه', '048', 290, 2, 10),
        (49, 'زبان و ادبیات', 'دری', '049', 244, 3, 90),
        (50, 'زبان و ادبیات', 'انگلیسی', '050', 256, 3, 90),
        (51, 'زبان و ادبیات', 'پشتو', '051', 227, 3, 100),
        (52, 'زبان و ادبیات', 'عربی', '052', 224, 3, 60),
        (53, 'زبان و ادبیات', 'روسی', '053', 222, 3, 100),
        (54, 'علوم اجتماعی', 'تاریخ', '054', 237, 3, 60),
        (55, 'علوم اجتماعی', 'جغرافیه', '055', 234, 3, 60),
        (56, 'علوم اجتماعی', 'جامعه شناسی', '056', 249, 3, 60),
        (57, 'علوم طبیعی', 'علوم و فرهنگ اسلامی', '057', 237, 3, 60),
        (58, 'علوم طبیعی', 'ریاضی', '058', 261, 3, 70),
        (59, 'علوم طبیعی', 'فزیک', '059', 255, 3, 70),
        (60, 'علوم طبیعی', 'کامپیوتر ساینس', '060', 275, 3, 70),
        (61, 'علوم طبیعی', 'کیمیا', '061', 241, 3, 70),
        (62, 'علوم طبیعی', 'بیولوژی', '062', 243, 3, 70),
        (63, 'علوم طبیعی', 'محیط زیست و تعلیمات صحی', '063', 249, 3, 70),
        (64, 'تعلیمات مسلکی', 'روانشاسی', '064', 244, 3, 50),
        (65, 'تعلیمات مسلکی', 'تعلیم و تربیه', '065', 244, 3, 50),
        (66, 'تعلیما اختصاصی', 'روانشناسی ناشنوایان', '066', 196, 3, 50),
        (67, 'تعلیما اختصاصی', 'روانشناسی نابینایان', '067', 218, 3, 50),
        (68, 'تعلیما اختصاصی', 'روانشناسی عقب مانده گان', '068', 220, 3, 50),
        (69, 'تربیت بدنی', 'عمومی', '069', 220, 3, 140),
        (70, 'ساختمانی', 'ساختمان های صنعتی و مدنی', '070', 309, 4, 50),
        (71, 'ساختمانی', 'مهندسی', '071', 302, 4, 50),
        (72, 'ساختمانی', 'ساختمانهای هایدرولیکی', '072', 281, 4, 50),
        (73, 'ساختمانی', 'ساختمانهای ترانسپورتی', '073', 301, 4, 50),
        (74, 'ساختمانی', 'آبرسانی و محیط زیست', '074', 284, 4, 50),
        (75, 'ساختمانی', 'شهر سازی', '075', 290, 4, 50),
        (76, 'جیولوژی و معادن', 'نفت و گاز', '076', 274, 4, 50),
        (77, 'جیولوژی و معادن', 'استخراج و معادن', '077', 288, 4, 50),
        (78, 'جیولوژی و معادن', 'اکتشاف معادن', '078', 270, 4, 50),
        (79, 'جیولوژی و معادن', 'جیودیزی', '079', 292, 4, 50),
        (80, 'تکنالوژی کیمیاوی', 'تکنالوژی مواد عضوی', '080', 242, 4, 50),
        (81, 'تکنالوژی کیمیاوی', 'تکنالوژی مواد غیر عضوی', '081', 250, 4, 50),
        (82, 'تکنالوژی کیمیاوی', 'تکنالوژی مواد غذایی', '082', 250, 4, 50),
        (83, 'کامپیوتر و انفورماتیک', ' (IT) تکنالوژی معلوماتی', '083', 276, 4, 50),
        (84, 'کامپیوتر و انفورماتیک', 'کامپیوتر ساینس', '084', 295, 4, 50),
        (85, 'کامپیوتر و انفورماتیک', 'کامپیوتر انجنیرینگ', '085', 284, 4, 50),
        (86, 'الکترومیخانیک', 'تأمین برق', '086', 287, 4, 50),
        (87, 'الکترومیخانیک', 'اتومیخانیک', '087', 265, 4, 60),
        (88, 'انجنیری منابع آب و محیط زیست', 'آبیاری', '088', 275, 4, 60),
        (89, 'انجنیری منابع آب و محیط زیست', 'آبرسانی و انجنیری هایدرولیک', '089', 281, 4, 60),
        (90, 'انجنیری', 'عمومی', '090', 296, 5, 100),
        (91, 'طب', 'عمومی', '091', 304, 5, 120),
        (92, 'کامپیوتر ساینس', 'عمومی', '092', 279, 5, 70),
        (93, 'اقتصاد', 'عمومی', '093', 283, 5, 200),
        (94, 'ادبیات', 'عمومی', '094', 255, 5, 300),
        (95, 'تعلیم و تربیه', 'عمومی', '095', 238, 5, 980),
        (96, 'حقوق', 'عمومی', '096', 284, 5, 200),
        (97, 'زراعت', 'عمومی', '097', 258, 5, 250),
        (98, 'ساینس', 'عمومی', '098', 252, 5, 600),
        (99, 'شرعیات', 'عمومی', '099', 264, 5, 150),
        (100, 'هنرها', 'عمومی', '100', 235, 5, 130),
        (101, 'ژورنالیزم', 'عمومی', '101', 268, 5, 150),
        (102, 'وترنری', 'عمومی', '102', 229, 5, 100),
        (103, 'ادارهء عامه', 'عمومی', '103', 274, 5, 100),
        (104, 'انجنیری', 'عمومی', '104', 292, 6, 200),
        (105, 'کامپیوتر ساینس', 'عمومی', '105', 285, 6, 70),
        (106, 'طب', 'عمومی', '106', 305, 6, 100),
        (107, 'اقتصاد', 'عمومی', '107', 287, 6, 220),
        (108, 'ادبیات', 'عمومی', '108', 254, 6, 550),
        (109, 'تعلیم و تربیه', 'عمومی', '109', 239, 6, 650),
        (110, 'حقوق', 'عمومی', '110', 295, 6, 140),
        (111, 'زراعت', 'عمومی', '111', 258, 6, 120),
        (112, 'ژورنالیزم', 'عمومی', '112', 269, 6, 140),
        (113, 'ادارهء عامه', 'عمومی', '113', 268, 6, 140),
        (114, 'شرعیات', 'عمومی', '114', 263, 6, 120),
        (115, 'ادبیات', 'عمومی', '115', 235, 7, 260),
        (116, 'انجنیری', 'عمومی', '116', 294, 7, 100),
        (117, 'حقوق', 'عمومی', '117', 280, 7, 140),
        (118, 'زراعت', 'عمومی', '118', 235, 7, 340),
        (119, 'شرعیات', 'عمومی', '119', 254, 7, 120),
        (120, 'تعلیم و تربیه', 'عمومی', '120', 215, 7, 340),
        (121, 'طب', 'عمومی', '121', 305, 7, 80),
        (122, 'انجنيری', 'عمومی', '122', 286, 8, 100),
        (123, 'تعلیم و تربیه', 'عمومی', '123', 224, 8, 600),
        (124, 'تعلیم و تربیه', 'بلوچی', '124', 198, 8, 30),
        (125, 'زراعت', 'عمومی', '125', 251, 8, 160),
        (126, 'طب', 'عمومی', '126', 293, 8, 100),
        (127, 'اقتصاد', 'عمومی', '127', 275, 8, 150),
        (128, 'شرعیات', 'عمومی', '128', 250, 8, 100),
        (129, 'ژورنالیزم', 'عمومی', '129', 264, 8, 120),
        (130, 'حقوق', 'عمومی', '130', 275, 8, 100),
        (131, 'ادارهء عامه', 'عمومی', '131', 254, 8, 100),
        (132, 'تعلیم و تربیه', 'عمومی', '132', 232, 9, 320),
        (133, 'زراعت', 'عمومی', '133', 251, 9, 120),
        (134, 'ادبیات', 'عمومی', '134', 245, 9, 220),
        (135, 'انجنیری', 'عمومی', '135', 285, 9, 50),
        (136, 'شرعیات', 'عمومی', '136', 257, 9, 100),
        (137, 'اقتصاد', 'عمومی', '137', 271, 9, 100),
        (138, 'حقوق', 'عمومی', '138', 273, 9, 100),
        (139, 'ادبیات', 'عمومی', '139', 238, 10, 160),
        (140, 'زراعت', 'عمومی', '140', 233, 10, 160),
        (141, 'تعلیم و تربیه', 'عمومی', '141', 216, 10, 160),
        (142, 'ادبیات', 'عمومی', '142', 247, 11, 400),
        (143, 'اقتصاد', 'عمومی', '143', 281, 11, 200),
        (144, 'انجنیری', 'عمومی', '144', 296, 11, 100),
        (145, 'تعلیم و تربیه', 'عمومی', '145', 228, 11, 600),
        (146, 'حقوق', 'عمومی', '146', 278, 11, 200),
        (147, 'زراعت', 'عمومی', '147', 241, 11, 250),
        (148, 'شرعیات (ذکور)', 'عمومی', '148', 250, 11, 80),
        (149, 'شرعیات (اناث)', 'عمومی', '149', 250, 11, 80),
        (150, 'طب', 'عمومی', '150', 299, 11, 120),
        (151, 'وترنری', 'عمومی', '151', 221, 11, 100),
        (152, 'ساینس', 'عمومی', '152', 242, 11, 250),
        (153, 'کامپیوتر ساینس', 'عمومی', '153', 278, 11, 100),
        (154, 'ژورنالیزم', 'عمومی', '154', 264, 11, 150),
        (155, 'اداره عامه', 'عمومی', '155', 240, 11, 150),
        (156, 'زراعت', 'عمومی', '156', 250, 12, 320),
        (157, 'حقوق', 'عمومی', '157', 284, 12, 220),
        (158, 'شرعیات', 'عمومی', '158', 251, 12, 130),
        (159, 'اداره و تجارت', 'عمومی', '159', 276, 12, 200),
        (160, 'تعلیم و تربیه', 'عمومی', '160', 230, 12, 600),
        (161, 'ژورنالیزم', 'عمومی', '161', 265, 12, 200),
        (162, 'کامپیوتر ساینس', 'عمومی', '162', 274, 12, 150),
        (163, 'طب', 'عمومی', '163', 297, 12, 140),
        (164, 'وترنری', 'عمومی', '164', 206, 12, 120),
        (165, 'انجنیری', 'عمومی', '165', 288, 12, 150),
        (166, 'ادبیات', 'عمومی', '166', 255, 12, 250),
        (167, 'زراعت', 'عمومی', '167', 234, 13, 160),
        (168, 'شرعیات', 'عمومی', '168', 239, 13, 160),
        (169, 'تعلیم و تربیه', 'عمومی', '169', 212, 13, 350),
        (170, 'کامیپیوترساینس', 'عمومی', '170', 266, 13, 130),
        (171, 'انجنيری', 'عمومی', '171', 285, 14, 120),
        (172, 'حقوق', 'عمومی', '172', 273, 14, 120),
        (173, 'تعلیم و تربیه', 'عمومی', '173', 218, 14, 550),
        (174, 'طب', 'عمومی', '174', 294, 14, 120),
        (175, 'زراعت', 'عمومی', '175', 230, 14, 300),
        (176, 'ادبیات', 'عمومی', '176', 235, 14, 200);


/* Tutorials Table Related Query */
drop table if exists `news`;

create table `news` (
    `id`  INTEGER auto_increment,
    `title`  VARCHAR(512),
    `content`  CLOB,
    `date` datetime null,
    `url_link` VARCHAR(256),
    `description`  CLOB(256),
    primary key (`id`)
);

insert into PUBLIC.NEWS (ID, TITLE, CONTENT, DATE, URL_LINK, DESCRIPTION)
values  (15, '.html', '<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title></title>
</head>
<body>
<p>در حال آماده سازی</p>

</body></html>', null, 'http://www.golbarg.net/news/', null);

/* Tutorials Table Related Query */
drop table if exists `news_article`;

create table `news_article` (
    `id`          INTEGER auto_increment,
    `title`       CLOB,
    `content`     CLOB,
    `url_link`    CLOB,
    `date`        VARCHAR(64),
    `description` VARCHAR(255),
    `category`    INTEGER,
    primary key (`id`)
);

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


