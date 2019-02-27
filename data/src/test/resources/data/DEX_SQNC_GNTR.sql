INSERT INTO dex_sqnc_gntr (id, code, current_value, description, increment_value, prefix, reference_format, sequence_format, c_ts, c_id, m_st)
VALUES (nextval('sq_dex_sqnc_gntr'), 'workOrder.referenceNo', 5, 'Work Order Reference No', 1, 'WO', '{#a}-{#b}-{#j}', '000000', current_timestamp, 1, 1);
INSERT INTO dex_sqnc_gntr (id, code, current_value, description, increment_value, prefix, reference_format, sequence_format, c_ts, c_id, m_st)
VALUES (nextval('SQ_DEX_SQNC_GNTR'), 'maintenanceRequest.referenceNo', 5, 'Maintenance Request Reference No', 1, 'MR', '{#a}-{#b}-{#j}', '000000', current_timestamp, 1, 1);
