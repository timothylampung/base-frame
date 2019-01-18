package com.assettagging.spotit.core.api;

/**
 * @author canang technologies
 */
public class Document extends FlowObject {

    private String referenceNo;
    private String sourceNo;
    private String auditNo;
    private String description;
    private String cancelComment;
    private String removeComment;

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

    public String getAuditNo() {
        return auditNo;
    }

    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCancelComment() {
        return cancelComment;
    }

    public void setCancelComment(String cancelComment) {
        this.cancelComment = cancelComment;
    }

    public String getRemoveComment() {
        return removeComment;
    }

    public void setRemoveComment(String removeComment) {
        this.removeComment = removeComment;
    }
}
