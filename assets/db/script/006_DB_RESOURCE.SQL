TRUNCATE TABLE PUBLIC.RESOURCE_CATEGORIES;

INSERT INTO PUBLIC.RESOURCE_CATEGORIES (ID, TITLE) VALUES (1, 'کتاب های صنف دوازدهم');
INSERT INTO PUBLIC.RESOURCE_CATEGORIES (ID, TITLE) VALUES (2, 'کتاب های صنف یازدهم');
INSERT INTO PUBLIC.RESOURCE_CATEGORIES (ID, TITLE) VALUES (3, 'کتاب های صنف دهم');
INSERT INTO PUBLIC.RESOURCE_CATEGORIES (ID, TITLE) VALUES (4, 'کتاب های صنف نهم');

TRUNCATE TABLE PUBLIC.RESOURCES;

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
