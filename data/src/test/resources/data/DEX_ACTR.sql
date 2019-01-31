--     ROLE_ADMINISTRATOR, // 0
--     ROLE_MANAGER,         // 1
--     ROLE_STAFF,          // 2

-- STAFF
INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE, FAX, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'),
  'ST000X', '12345', 0, 'NAZIFAH ROSLI', 'nazifah.rosli@spotit', NULL, NULL, NULL, 1, 1,
        CURRENT_TIMESTAMP);
        UPDATE DEX_ACTR SET IDENTITY_NO = 'ID1001' WHERE CODE = 'ST000X';
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'nazifah.rosli@spotit') where EMAIL= 'nazifah.rosli@spotit';


-- FM
INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE, FAX, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'),
  'ST0001', '12345', 0, 'FARIDZWAN ZAINAL', 'faridzwan.zainal@spotit', NULL, NULL, NULL, 1, 1,
        CURRENT_TIMESTAMP);
        UPDATE DEX_ACTR SET IDENTITY_NO = 'ID2002' WHERE CODE = 'ST0001';
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'faridzwan.zainal@spotit')where EMAIL= 'faridzwan.zainal@spotit';
-- INSERT INTO DEX_FACI (id) VALUES ((SELECT ID FROM DEX_ACTR where email = 'faridzwan.zainal@spotit'));



INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE, FAX, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'),
  'ST0002', '12345', 0, 'HUDA ZULKIFLI', 'huda.zulkifli@spotit', NULL, NULL, NULL, 1, 1,
        CURRENT_TIMESTAMP);
        UPDATE DEX_ACTR SET IDENTITY_NO = 'ID3003' WHERE CODE = 'ST0002';
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'huda.zulkifli@spotit')
where EMAIL= 'huda.zulkifli@spotit';
-- INSERT INTO DEX_FACI (id) VALUES ((SELECT ID FROM DEX_ACTR where email = 'huda.zulkifli@spotit'));




INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE, FAX, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'),
  'ST0003', '12345', 0, 'HAFIZI HAMID', 'hafizi.hamid@spotit', NULL, NULL, NULL, 1, 1,
        CURRENT_TIMESTAMP);
        UPDATE DEX_ACTR SET IDENTITY_NO = 'ID4004' WHERE CODE = 'ST0003';
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'hafizi.hamid@spotit')
where EMAIL= 'hafizi.hamid@spotit';
-- INSERT INTO DEX_TECH (id) VALUES ((SELECT ID FROM DEX_ACTR where email = 'hafizi.hamid@spotit'));


INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE, FAX, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'),
  'ST0004', '12345', 0, 'FAZILAH ROSMAN', 'fazilah.rosman@spotit', NULL, NULL, NULL, 1, 1,
        CURRENT_TIMESTAMP);
        UPDATE DEX_ACTR SET IDENTITY_NO = 'ID5005' WHERE CODE = 'ST0004';
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'fazilah.rosman@spotit')
where EMAIL= 'fazilah.rosman@spotit';
-- INSERT INTO DEX_TECH (id) VALUES ((SELECT ID FROM DEX_ACTR where email = 'fazilah.rosman@spotit'));


INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE, FAX, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'),
  'ST0005', '12345', 0, 'KHAIRULNIZAM LATIF', 'khairulnizam.latif@spotit', NULL, NULL, NULL, 1, 1,
        CURRENT_TIMESTAMP);
        UPDATE DEX_ACTR SET IDENTITY_NO = 'ID6006' WHERE CODE = 'ST0005';
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'khairulnizam.latif@spotit')
where EMAIL= 'khairulnizam.latif@spotit';
-- INSERT INTO DEX_FACI (id) VALUES ((SELECT ID FROM DEX_ACTR where email = 'khairulnizam.latif@spotit'));



INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE, FAX, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'),
  'ST0006', '12345', 0, 'FAHIM YUSOF', 'fahim.yusof@spotit', NULL, NULL, NULL, 1, 1,
        CURRENT_TIMESTAMP);
        UPDATE DEX_ACTR SET IDENTITY_NO = 'ID7007' WHERE CODE = 'ST0006';
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'fahim.yusof@spotit')
where EMAIL= 'fahim.yusof@spotit';
-- INSERT INTO DEX_FACI (id) VALUES ((SELECT ID FROM DEX_ACTR where email = 'fahim.yusof@spotit'));



INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE, FAX, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'),
  'ST0007', '12345', 0, 'ISMAT HAMDAN', 'ismat.hamdan@spotit', NULL, NULL, NULL, 1, 1,
        CURRENT_TIMESTAMP);
        UPDATE DEX_ACTR SET IDENTITY_NO = 'ID8008' WHERE CODE = 'ST0007';
                                                                WHERE CODE = 'IT'));
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'ismat.hamdan@spotit')
where EMAIL= 'ismat.hamdan@spotit';
-- INSERT INTO DEX_TECH (id) VALUES ((SELECT ID FROM DEX_ACTR where email = 'ismat.hamdan@spotit'));





INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE, FAX, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'),
  'ST0008', '12345', 0, 'BAIZURA BASAR', 'baizura.basar@spotit', NULL, NULL, NULL, 1, 1,
        CURRENT_TIMESTAMP);
        UPDATE DEX_ACTR SET IDENTITY_NO = 'ID9009' WHERE CODE = 'ST0008';
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'baizura.basar@spotit')
where EMAIL= 'baizura.basar@spotit';
-- INSERT INTO DEX_TECH (id) VALUES ((SELECT ID FROM DEX_ACTR where email = 'baizura.basar@spotit'));


INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE, FAX, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'),
  'ST0009', '23456', 0, 'NIK MUHD HASHIF', 'hashif.yusof@spotit', NULL, NULL, NULL, 1, 1,
        CURRENT_TIMESTAMP);
        UPDATE DEX_ACTR SET IDENTITY_NO = 'ID2662' WHERE CODE = 'ST0009';
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'hashif.yusof@spotit')
where EMAIL= 'hashif.yusof@spotit';


