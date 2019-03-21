INSERT INTO DEX_CNFG (ID, KEY_, VALUE_, DESCRIPTION, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_CNFG'), 'maintenance.rate', '12.00', 'Rate Per hour',1, 1, CURRENT_TIMESTAMP);
INSERT INTO DEX_CNFG (ID, KEY_, VALUE_, DESCRIPTION, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_DEX_CNFG'), 'upload.dir', './uploads', 'Upload Files Location',1, 1, CURRENT_TIMESTAMP);

