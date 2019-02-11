package my.spotit.core.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DT - DRAFTER
 * RQ - REQUESTER
 * QR - QUERIER
 * RG - REGISTERER
 * PR - PREPARER
 * CK - CHECKER
 * VF - VERIFIER
 * AV - APPROVER
 * CL - CANCELER
 * UP - UPPER_APPROVER
 * UQ - UPPER_QUERIER
 * UV - UPPER_VERIFIER
 * UC - UPPER_CHECKER
 * PS - PUBLISHER
 * RM - REMOVER
 * EV - EVALUATOR
 * SL - SELECTOR
 *
 * @author canang technologies
 * @since 22/5/2015.
 */
@Embeddable
public class DexFlowdata implements Serializable {

    @Column(name = "FD_ST")
    private DexFlowState state;

    @Column(name = "DT_ID")
    private Long drafterId;

    @Column(name = "RQ_ID")
    private Long requesterId;

    @Column(name = "RG_ID")
    private Long registererId;

    @Column(name = "CK_ID")
    private Long checkerId;

    @Column(name = "PR_ID")
    private Long preparerId;

    @Column(name = "VF_ID")
    private Long verifierId;

    @Column(name = "UV_ID")
    private Long upperVerifierId;

    @Column(name = "AV_ID")
    private Long approverId;

    @Column(name = "UP_ID")
    private Long upperApproverId;

    @Column(name = "CL_ID")
    private Long cancelerId;

    @Column(name = "PS_ID")
    private Long publisherId;

    @Column(name = "RM_ID")
    private Long removerId;

    @Column(name = "EV_ID")
    private Long evaluatorId;

    @Column(name = "SL_ID")
    private Long selectorId;

    @Column(name = "DT_TS")
    private Timestamp draftedDate;

    @Column(name = "RQ_TS")
    private Timestamp requestedDate;

    @Column(name = "PR_TS")
    private Timestamp preparedDate;

    @Column(name = "CK_TS")
    private Timestamp checkedDate;

    @Column(name = "RG_TS")
    private Timestamp registeredDate;

    @Column(name = "CL_TS")
    private Timestamp cancelledDate;

    @Column(name = "AV_TS")
    private Timestamp approvedDate;

    @Column(name = "UP_TS")
    private Timestamp upperApprovedDate;

    @Column(name = "VF_TS")
    private Timestamp verifiedDate;

    @Column(name = "UV_TS")
    private Timestamp upperVerifiedDate;

    @Column(name = "PS_TS")
    private Timestamp publishedDate;

    @Column(name = "RM_TS")
    private Timestamp removedDate;

    @Column(name = "EV_TS")
    private Timestamp evaluatedDate;

    @Column(name = "SL_TS")
    private Timestamp selectedDate;


    public Timestamp getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Timestamp approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public Long getCancelerId() {
        return cancelerId;
    }

    public void setCancelerId(Long cancelerId) {
        this.cancelerId = cancelerId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long posterId) {
        this.publisherId = posterId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getVerifierId() {
        return verifierId;
    }

    public void setVerifierId(Long verifierId) {
        this.verifierId = verifierId;
    }

    public Timestamp getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Timestamp requestedDate) {
        this.requestedDate = requestedDate;
    }

    public Timestamp getDraftedDate() {
        return draftedDate;
    }

    public void setDraftedDate(Timestamp drafteDDate) {
        this.draftedDate = drafteDDate;
    }

    public Long getDrafterId() {
        return drafterId;
    }

    public void setDrafterId(Long drafterId) {
        this.drafterId = drafterId;
    }

    public Timestamp getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(Timestamp checkedDate) {
        this.checkedDate = checkedDate;
    }

    public Long getRegistererId() {
        return registererId;
    }

    public void setRegistererId(Long registererId) {
        this.registererId = registererId;
    }

    public Timestamp getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Timestamp registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public Long getPreparerId() {
        return preparerId;
    }

    public void setPreparerId(Long preparerId) {
        this.preparerId = preparerId;
    }

    public Long getRemoverId() {
        return removerId;
    }

    public void setRemoverId(Long removerId) {
        this.removerId = removerId;
    }

    public Timestamp getRemovedDate() {
        return removedDate;
    }

    public void setRemovedDate(Timestamp removedDate) {
        this.removedDate = removedDate;
    }

    public Long getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(Long evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public Timestamp getEvaluatedDate() {
        return evaluatedDate;
    }

    public void setEvaluatedDate(Timestamp evaluatedDate) {
        this.evaluatedDate = evaluatedDate;
    }

    public Long getSelectorId() {
        return selectorId;
    }

    public void setSelectorId(Long selectorId) {
        this.selectorId = selectorId;
    }

    public Timestamp getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Timestamp selectedDate) {
        this.selectedDate = selectedDate;
    }

    public DexFlowState getState() {
        return state;
    }

    public void setState(DexFlowState state) {
        this.state = state;
    }

    public Timestamp getPreparedDate() {
        return preparedDate;
    }

    public void setPreparedDate(Timestamp preparedDate) {
        this.preparedDate = preparedDate;
    }

    public Timestamp getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(Timestamp cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public Timestamp getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Timestamp verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public Timestamp getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Timestamp publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Long getUpperVerifierId() {
        return upperVerifierId;
    }

    public void setUpperVerifierId(Long upperVerifierId) {
        this.upperVerifierId = upperVerifierId;
    }

    public Long getUpperApproverId() {
        return upperApproverId;
    }

    public void setUpperApproverId(Long upperApproverId) {
        this.upperApproverId = upperApproverId;
    }

    public Timestamp getUpperApprovedDate() {
        return upperApprovedDate;
    }

    public void setUpperApprovedDate(Timestamp upperApprovedDate) {
        this.upperApprovedDate = upperApprovedDate;
    }

    public Timestamp getUpperVerifiedDate() {
        return upperVerifiedDate;
    }

    public void setUpperVerifiedDate(Timestamp upperVerifiedDate) {
        this.upperVerifiedDate = upperVerifiedDate;
    }

    @Override
    public String toString() {
        return "SaFlowdata{" +
                "state=" + state +
                ", drafterId=" + drafterId +
                ", requesterId=" + requesterId +
                ", registererId=" + registererId +
                ", receiverId=" + checkerId +
                ", preparerId=" + preparerId +
                ", verifierId=" + verifierId +
                ", approverId=" + approverId +
                ", cancelerId=" + cancelerId +
                ", publisherId=" + publisherId +
                ", removerId=" + removerId +
                '}';
    }
}