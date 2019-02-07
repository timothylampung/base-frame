INSERT INTO DEX_SQNC_GNTR (id, code, current_value, description, increment_value, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, prefix, reference_format, sequence_format)
VALUES (nextval('SQ_DEX_SQNC_GNTR'), 'workOrder.referenceNo', 1, 'Work Order Reference No', 1, CURRENT_TIMESTAMP, 0, null, null, null, null, 1, 'WO', '{#a}-{#b}-{#j}', '000000');
INSERT INTO DEX_SQNC_GNTR (id, code, current_value, description, increment_value, c_ts, c_id, d_ts, d_id, m_ts, m_id, m_st, prefix, reference_format, sequence_format)
VALUES (nextval('SQ_DEX_SQNC_GNTR'), 'maintenanceRequest.referenceNo', 1, 'Maintenance Request Reference No', 1, CURRENT_TIMESTAMP, 0, null, null, null, null, 1, 'MR', '{#a}-{#b}-{#j}', '000000');

-- SQ_DEX_SQNC_GNTR

