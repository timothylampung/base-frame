INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'ASST_005', 'SINKI', null, null, null, null, null, null, 1,
        (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));
INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'ASSET_CODE', 'ASSET_DESC', '2019-02-07 14:55:44.447000', 1, null,
        null, null, null, 1, (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));
INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'ASST_001', 'AIRCOND', null, null, null, null, null, null, 1,
        (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));
INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'ASST_002', 'KIPAS', null, null, null, null, null, null, 1,
        (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));
INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'ASST_003', 'LAMPU KALIMANTANG', null, null, null, null, null, null,
        1, (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));
INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'ASST_004', 'MEJA', null, null, null, null, null, null, 1,
        (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));
INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'ASSET_CODE', 'ASSET_DESC', '2019-02-07 15:04:01.624000', 1, null,
        null, null, null, 1, (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));
INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'ASST_11', 'KEPALA', '2019-02-20 01:44:40.320000', 1, null, null,
        null, null, 1, (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));
INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'CODE7957', 'DESCRIPTIONLAGHEAY', '2019-02-11 15:57:40.484000', 13,
        null, null, null, null, 1, (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));
INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'ASSET_CODE', 'ASSET_DESC', '2019-02-07 15:23:29.149000', 1, null,
        null, null, null, 1, (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));
INSERT INTO dex_asst (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st,
                      asset_code_id, location_id)
VALUES (nextval('SQ_DEX_ASST'), 'ASST_10', 'HATI', '2019-02-22 10:04:54.744000', 1, null, null,
        null, null, 0, (SELECT ID FROM dex_asst_code where code = 'AC_001'),
        (SELECT ID FROM dex_lctn where code = 'SM_001'));