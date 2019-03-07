-- =================================================================================================
-- STAFF
-- =================================================================================================
INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE,
                      FAX, ADDRESS1, ADDRESS2, POSTCODE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'), 'staff1', 'staff1', 0, 'Staff 1',
        'staff1@spotit.my', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_STAF (ID, POSITION_CODE) VALUES (currval('SQ_DEX_ACTR'),(SELECT ID FROM dex_pstn_code where description = 'TECHNICIAN'));
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'staff1@spotit.my')where EMAIL = 'staff1@spotit.my';

INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE,
                      FAX, ADDRESS1, ADDRESS2, POSTCODE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'), 'staff2', 'staff2', 0, 'Staff 1',
        'staff2@spotit.my', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_STAF (ID, POSITION_CODE) VALUES (currval('SQ_DEX_ACTR'),(SELECT ID FROM dex_pstn_code where description = 'TECHNICIAN'));
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'staff2@spotit.my')where EMAIL = 'staff2@spotit.my';

-- =================================================================================================
-- FM
-- =================================================================================================
INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE,
                      FAX, ADDRESS1, ADDRESS2, POSTCODE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'), 'fm1', 'fm1', 0, 'Fm 1',
        'fm1@spotit.my', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_STAF (ID, POSITION_CODE) VALUES (currval('SQ_DEX_ACTR'),(SELECT ID FROM dex_pstn_code where description = 'FACILITY MANAGER'));
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'fm1@spotit.my')where EMAIL = 'fm1@spotit.my';

INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE,
                      FAX, ADDRESS1, ADDRESS2, POSTCODE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'), 'fm2', 'fm2', 0, 'Fm 1',
        'fm2@spotit.my', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_STAF (ID, POSITION_CODE) VALUES (currval('SQ_DEX_ACTR'),(SELECT ID FROM dex_pstn_code where description = 'FACILITY MANAGER'));
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'fm2@spotit.my')where EMAIL = 'fm2@spotit.my';

-- =================================================================================================
-- SUPERVISOR
-- =================================================================================================
INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE,
                      FAX, ADDRESS1, ADDRESS2, POSTCODE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'), 'supervisor1', 'supervisor1', 0, 'Supervisor 1',
        'supervisor1@spotit.my', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_STAF (ID, POSITION_CODE) VALUES (currval('SQ_DEX_ACTR'),(SELECT ID FROM dex_pstn_code where description = 'SUPERVISOR'));
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'supervisor1@spotit.my')where EMAIL = 'supervisor1@spotit.my';

INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE,
                      FAX, ADDRESS1, ADDRESS2, POSTCODE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'), 'supervisor2', 'supervisor2', 0, 'Supervisor 1',
        'supervisor2@spotit.my', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_STAF (ID, POSITION_CODE) VALUES (currval('SQ_DEX_ACTR'),(SELECT ID FROM dex_pstn_code where description = 'SUPERVISOR'));
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'supervisor2@spotit.my')where EMAIL = 'supervisor2@spotit.my';

-- =================================================================================================
-- TECH
-- =================================================================================================
INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE,
                      FAX, ADDRESS1, ADDRESS2, POSTCODE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'), 'tech1', 'tech1', 0, 'Tech 1',
        'tech1@spotit.my', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_STAF (ID, POSITION_CODE) VALUES (currval('SQ_DEX_ACTR'),(SELECT ID FROM dex_pstn_code where description = 'TECHNICIAN'));
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'tech1@spotit.my')where EMAIL = 'tech1@spotit.my';

INSERT INTO DEX_ACTR (ID, CODE, IDENTITY_NO, ACTOR_TYPE, NAME, EMAIL, PHONE, MOBILE,
                      FAX, ADDRESS1, ADDRESS2, POSTCODE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_ACTR'), 'tech2', 'tech2', 0, 'Tech 1',
        'tech2@spotit.my', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_STAF (ID, POSITION_CODE) VALUES (currval('SQ_DEX_ACTR'),(SELECT ID FROM dex_pstn_code where description = 'TECHNICIAN'));
UPDATE DEX_USER set ACTOR_ID = (SELECT ID from DEX_ACTR where email = 'tech2@spotit.my')where EMAIL = 'tech2@spotit.my';