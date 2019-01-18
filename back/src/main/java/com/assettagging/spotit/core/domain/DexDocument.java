package com.assettagging.spotit.core.domain;


/**
 * @author canang technologies
 */
public interface DexDocument extends DexFlowObject {

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    String getSourceNo();

    void setSourceNo(String sourceNo);

    String getAuditNo();

    void setAuditNo(String auditNo);

    String getDescription();

    void setDescription(String description);

    String getRemoveComment();

    void setRemoveComment(String removeComment);

    String getCancelComment();

    void setCancelComment(String cancelComment);
}
