INSERT INTO DEX_SQNC_GNTR (ID, CODE, CURRENT_VALUE, DESCRIPTION, INCREMENT_VALUE, PREFIX, REFERENCE_FORMAT, SEQUENCE_FORMAT, M_ST, C_ID, C_TS)
VALUES (SQ_DEX_SQNC_GNTR.nextval, 'workOrder.referenceNo', 1, 'Work Order Reference No', 1, '',
        '{#a}-{#b}-{#j}', '000000', 1, 0,
        CURRENT_TIMESTAMP);