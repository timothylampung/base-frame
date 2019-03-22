package my.spotit.asset.maintenance.api.vo;

import java.util.Date;

import my.spotit.asset.asset.api.vo.Asset;
import my.spotit.asset.asset.api.vo.Location;
import my.spotit.asset.common.api.vo.File;
import my.spotit.asset.core.api.Document;
import my.spotit.asset.identity.api.vo.Actor;

public class MaintenanceRequest extends Document {

    private String remark;
    private Date requestedDate;
    private Boolean delegated;
    private Actor requester;
    private Actor delegator;
    private Actor verifier;
    private Location location;
    private Asset asset;
    private File file;

    public MaintenanceRequest() {
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public Boolean getDelegated() {
        return delegated;
    }

    public void setDelegated(Boolean delegated) {
        this.delegated = delegated;
    }

    public Actor getRequester() {
        return requester;
    }

    public void setRequester(Actor requester) {
        this.requester = requester;
    }

    public Actor getDelegator() {
        return delegator;
    }

    public void setDelegator(Actor delegator) {
        this.delegator = delegator;
    }

    public Actor getVerifier() {
        return verifier;
    }

    public void setVerifier(Actor verifier) {
        this.verifier = verifier;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
