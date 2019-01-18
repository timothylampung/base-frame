INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'root', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'root', 'root@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'root'), 0, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'admin', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'admin', 'admin@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'admin'), 0, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'system', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'system', 'system@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'system'), 0, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'nazifah.rosli', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'Nazifah Rosli', 'nazifah.rosli@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'),
 (SELECT ID FROM DEX_PCPL WHERE NAME = 'nazifah.rosli'), 1, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'faridzwan.zainal', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'Faridzwan Zainal', 'faridzwan.zainal@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'faridzwan.zainal'), 2, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'huda.zulkifli', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'Huda Zulkifli', 'huda.zulkifli@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'huda.zulkifli'), 2, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'hafizi.hamid', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'Hafizi Hamid', 'hafizi.hamid@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'hafizi.hamid'), 2, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'fazilah.rosman', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'Fazilah Rosman', 'fazilah.rosman@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'fazilah.rosman'), 2, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'khairulnizam.latif', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'Khairulnizam Latif', 'khairulnizam.latif@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'khairulnizam.latif'), 2, 1, 1, CURRENT_TIMESTAMP);


INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'fahim.yusof', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'Fahim Yusof', 'fahim.yusof@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'fahim.yusof'), 2, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'ismat.hamdan', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'Ismat Hamdan', 'ismat.hamdan@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'ismat.hamdan'), 2, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_PCPL'), 'baizura.basar', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_DEX_PCPL'), 'Baizura Basar', 'baizura.basar@spotit', 'abc123');
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'baizura.basar'), 2, 1, 1, CURRENT_TIMESTAMP);


-- GROUP

-- GROUP_USER
INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL'), 'GRP_USR', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP (ID) VALUES (currval('SQ_DEX_PCPL'));
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'), 1, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL'), 'GRP_VNDR', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP (ID) VALUES (currval('SQ_DEX_PCPL'));
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'), 2, 1, 1, CURRENT_TIMESTAMP);


-- GROUP ADMIN
INSERT INTO DEX_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL'), 'GRP_ADM', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP (ID) VALUES (currval('SQ_DEX_PCPL'));
INSERT INTO DEX_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_PCPL_ROLE'), (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_ADM'), 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_ADM'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'root'), 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_ADM'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'system'), 1, 1, CURRENT_TIMESTAMP);

INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_ADM'), 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'faridzwan.zainal'), 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'huda.zulkifli'), 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'hafizi.hamid'), 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'fazilah.rosman'), 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'khairulnizam.latif'), 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'fahim.yusof'), 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'ismat.hamdan'), 1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_DEX_GROP_MMBR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM DEX_PCPL WHERE NAME = 'baizura.basar'), 1, 1, CURRENT_TIMESTAMP);
