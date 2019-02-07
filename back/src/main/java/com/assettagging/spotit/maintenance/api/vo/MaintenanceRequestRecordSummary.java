package com.assettagging.spotit.maintenance.api.vo;


import com.assettagging.spotit.core.api.Record;

public class MaintenanceRequestRecordSummary extends Record {
    private String referenceNo;
    private String sourceNo;
    private String description;

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
