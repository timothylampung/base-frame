INSERT INTO DEX_ASST (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, asset_code_id, location_id)
 VALUES (nextVal('SQ_DEX_ASST'), 'ASST_001', 'AIRCOND', null, null, null, null, null, null, null,
 (SELECT id from dex_asst_code where code = 'ASST_CODE_001'), (SELECT id from dex_lctn where code = 'SM_001'));
INSERT INTO DEX_ASST (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, asset_code_id, location_id)
VALUES (nextVal('SQ_DEX_ASST'), 'ASST_002', 'KIPAS', null, null, null, null, null, null, null,
(SELECT id from dex_asst_code where code = 'ASST_CODE_002'), (SELECT id from dex_lctn where code = 'SM_001'));
INSERT INTO DEX_ASST (id, code, description, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, asset_code_id, location_id)
VALUES (nextVal('SQ_DEX_ASST'), 'ASST_003', 'LAMPU KALIMANTANG', null, null, null, null, null, null, null,
(SELECT id from dex_asst_code where code = 'ASST_CODE_003'), (SELECT id from dex_lctn where code = 'SM_001'));
INSERT INTO DEX_ASST (id, code, description , c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, asset_code_id, location_id)
VALUES (nextVal('SQ_DEX_ASST'), 'ASST_004', 'MEJA', null, null, null, null, null, null, null,
(SELECT id from dex_asst_code where code = 'ASST_CODE_004'), (SELECT id from dex_lctn where code = 'SM_002'));
INSERT INTO DEX_ASST (id, code, description , c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, asset_code_id, location_id)
VALUES (nextVal('SQ_DEX_ASST'), 'ASST_005', 'SINKI', null, null, null, null, null, null, null,
(SELECT id from dex_asst_code where code = 'ASST_CODE_005'), (SELECT id from dex_lctn where code = 'SM_002'));