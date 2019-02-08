
-- Prepare actor
-- SQ_DEX_ACTR
INSERT INTO dex_actr (id, actor_type, address1, address2, address3, code, email, fax, identity_no, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, mobile, name, phone, postcode) 
VALUES (nextval('SQ_DEX_ACTR'), 1, 'xtyaxvcgcx', 'alelcoiozy', 'seiteknvmo', 'TECH_1549007206147', 'timothy.lampung@gmail.com', 'luzgr', '950808136453', CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'slvhvxyszvlt', 'Timothy Lampung', '313215364928', 'dlrsa');
INSERT INTO dex_actr (id, actor_type, address1, address2, address3, code, email, fax, identity_no, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, mobile, name, phone, postcode) 
VALUES (nextval('SQ_DEX_ACTR'), 0, 'qdodgovkhf', 'txbdlzybyo', 'bmtfualkbj', 'SPVR_1549007207144', 'amirul.yunik@gmail.com', 'uwxtp', '238912399923', CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'clclatvyybct', 'Amirul Yunik', '482531584488', 'fbvah');
INSERT INTO dex_actr (id, actor_type, address1, address2, address3, code, email, fax, identity_no, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, mobile, name, phone, postcode) 
VALUES (nextval('SQ_DEX_ACTR'), 0, 'sjnnhjfkjr', 'gezcsdhano', 'cyvbletuoe', 'FM_1549007208043', 'zamir.zaharul@gmail.com', 'xasip', '348938398934', CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'abscgczjawab', 'Zamir Zaharul', '784243701030', 'upltp');
INSERT INTO dex_actr (id, actor_type, address1, address2, address3, code, email, fax, identity_no, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, mobile, name, phone, postcode)
VALUES (nextval('SQ_DEX_ACTR'), 0, 'sjnnhjfkjr', 'gezcsdhano', 'cyvbletuoe', 'FACI_1549007208043', 'maula@gmail.com', 'xasip', '348938398934', CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'abscgczjawab', 'maula', '784243701030', 'upltp');


INSERT INTO dex_tech (id) VALUES ((SELECT ID FROM dex_actr WHERE NAME = 'Timothy Lampung'));
INSERT INTO dex_supr (id) VALUES ((SELECT ID FROM dex_actr WHERE NAME = 'Amirul Yunik'));
INSERT INTO dex_faci (id) VALUES ((SELECT ID FROM dex_actr WHERE NAME = 'Zamir Zaharul'));
INSERT INTO dex_staf (id) VALUES ((SELECT ID FROM dex_actr WHERE NAME = 'maula'));


-- prepare principal SQ_DEX_PCPL
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, true, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'root', 0);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, true, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'admin', 0);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, true, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'system', 0);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, true, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'GRP_USR', 1);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, true, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'GRP_SPVR', 1);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, true, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'GRP_STAF', 1);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, true, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'GRP_FACI', 1);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, true, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'GRP_TECH', 1);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, true, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'GRP_ADM', 1);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, false, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'timothy.lampung', 0);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, false, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'amirul.yunik', 0);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, false, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'zamir.zaharul', 0);
INSERT INTO dex_pcpl (id, enabled, locked, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, name, principal_type) VALUES (nextval('SQ_DEX_PCPL'), true, false, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 'maula', 0);

-- prepare group
INSERT INTO dex_grop (id) VALUES ((SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_USR'));
INSERT INTO dex_grop (id) VALUES ((SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_SPVR'));
INSERT INTO dex_grop (id) VALUES ((SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_TECH'));
INSERT INTO dex_grop (id) VALUES ((SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_FACI'));
INSERT INTO dex_grop (id) VALUES ((SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_STAF'));
INSERT INTO dex_grop (id) VALUES ((SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_ADM'));



-- grouping SQ_DEX_GROP_MMBR
INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), CURRENT_TIMESTAMP, 1, null, null, null, null, 1, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_ADM'), (SELECT ID FROM dex_pcpl WHERE NAME = 'root'));
INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), CURRENT_TIMESTAMP, 1, null, null, null, null, 1, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_ADM'), (SELECT ID FROM dex_pcpl WHERE NAME = 'system'));

INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), CURRENT_TIMESTAMP, 1, null, null, null, null, 1, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_USR'), (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_ADM'));
INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), CURRENT_TIMESTAMP, 1, null, null, null, null, 1, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_USR'), (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_TECH'));
INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), CURRENT_TIMESTAMP, 1, null, null, null, null, 1, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_USR'), (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_SPVR'));
INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), CURRENT_TIMESTAMP, 1, null, null, null, null, 1, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_USR'), (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_FACI'));
INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), CURRENT_TIMESTAMP, 1, null, null, null, null, 1, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_USR'), (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_STAF'));

INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), null, null, null, null, null, null, null, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_TECH'), (SELECT ID FROM dex_pcpl WHERE NAME = 'timothy.lampung'));
INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), null, null, null, null, null, null, null, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_SPVR'), (SELECT ID FROM dex_pcpl WHERE NAME = 'amirul.yunik'));
INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), null, null, null, null, null, null, null, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_FACI'), (SELECT ID FROM dex_pcpl WHERE NAME = 'zamir.zaharul'));
INSERT INTO dex_grop_mmbr (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, group_id, principal_id)
VALUES (nextval('SQ_DEX_GROP_MMBR'), null, null, null, null, null, null, null, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_STAF'), (SELECT ID FROM dex_pcpl WHERE NAME = 'maula'));


-- prepare user

INSERT INTO dex_user (email, password, real_name, id, actor_id) VALUES ('root@terang.asia', 'abc123', 'root', (SELECT ID FROM dex_pcpl WHERE NAME = 'root'), null);
INSERT INTO dex_user (email, password, real_name, id, actor_id) VALUES ('admin@terang.asia', 'abc123', 'admin', (SELECT ID FROM dex_pcpl WHERE NAME = 'admin'), null);
INSERT INTO dex_user (email, password, real_name, id, actor_id) VALUES ('system@terang.asia', 'abc123', 'system', (SELECT ID FROM dex_pcpl WHERE NAME = 'system'), null);
INSERT INTO dex_user (email, password, real_name, id, actor_id) VALUES ('timothy.lampung@gmail.com', 'abc123', 'Timothy Lampung', (SELECT ID FROM dex_pcpl WHERE NAME = 'timothy.lampung'), (SELECT ID FROM dex_actr WHERE NAME = 'timothy.lampung'));
INSERT INTO dex_user (email, password, real_name, id, actor_id) VALUES ('amirul.yunik@gmail.com', 'abc123', 'Amirul Yunik', (SELECT ID FROM dex_pcpl WHERE NAME = 'amirul.yunik'), (SELECT ID FROM dex_actr WHERE NAME = 'amirul.yunik'));
INSERT INTO dex_user (email, password, real_name, id, actor_id) VALUES ('zamir.zaharul@gmail.com', 'abc123', 'Zamir Zaharul', (SELECT ID FROM dex_pcpl WHERE NAME = 'zamir.zaharul'), (SELECT ID FROM dex_actr WHERE NAME = 'zamir.zaharul'));
INSERT INTO dex_user (email, password, real_name, id, actor_id) VALUES ('zamir.zaharul@gmail.com', 'abc123', 'Maula', (SELECT ID FROM dex_pcpl WHERE NAME = 'maula'), (SELECT ID FROM dex_actr WHERE NAME = 'maula'));


--     ROLE_ADMINISTRATOR, // 0
--     ROLE_USER, // 0
--     ROLE_FACILITY_MANAGER,          // 1
--     ROLE_STAFF,          // 2
--     ROLE_TECHNICIAN, //3
--     ROLE_SUPERVISOR;         // 4

-- prepare roles
INSERT INTO dex_pcpl_role (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, role_type, principal_id) VALUES (1, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 0, (SELECT ID FROM dex_pcpl WHERE NAME = 'root'));
INSERT INTO dex_pcpl_role (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, role_type, principal_id) VALUES (2, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 0, (SELECT ID FROM dex_pcpl WHERE NAME = 'admin'));
INSERT INTO dex_pcpl_role (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, role_type, principal_id) VALUES (3, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 0, (SELECT ID FROM dex_pcpl WHERE NAME = 'system'));
INSERT INTO dex_pcpl_role (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, role_type, principal_id) VALUES (4, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 1, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_USR'));
INSERT INTO dex_pcpl_role (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, role_type, principal_id) VALUES (5, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 2, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_USR'));
INSERT INTO dex_pcpl_role (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, role_type, principal_id) VALUES (6, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 0, (SELECT ID FROM dex_pcpl WHERE NAME = 'GRP_ADM'));
INSERT INTO dex_pcpl_role (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, role_type, principal_id) VALUES (7, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 3, (SELECT ID FROM dex_pcpl WHERE NAME = 'timothy.lampung'));
INSERT INTO dex_pcpl_role (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, role_type, principal_id) VALUES (8, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 4, (SELECT ID FROM dex_pcpl WHERE NAME = 'amirul.yunik'));
INSERT INTO dex_pcpl_role (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, role_type, principal_id) VALUES (9, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 1, (SELECT ID FROM dex_pcpl WHERE NAME = 'zamir.zaharul'));
INSERT INTO dex_pcpl_role (id, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, role_type, principal_id) VALUES (10, CURRENT_TIMESTAMP, 1, null, null, null, null, 1, 2, (SELECT ID FROM dex_pcpl WHERE NAME = 'maula'));