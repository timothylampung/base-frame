package my.spotit.asset.workorder.api.vo;

import my.spotit.asset.asset.api.vo.Asset;
import my.spotit.asset.asset.api.vo.Location;
import my.spotit.asset.common.api.vo.File;
import my.spotit.asset.core.api.Document;
import my.spotit.asset.identity.api.vo.Actor;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequest;

import java.sql.Date;

public class WorkOrder extends Document {

    private String description;
    private String removeComment;
    private Date requestedDate;
    private Boolean delegated;
    private Actor reporter;
    private Actor assignee;
    private Location location;
    private Asset asset;
    private File file;
    private MaintenanceRequest maintenanceRequest;


    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getRemoveComment() {
        return removeComment;
    }

    @Override
    public void setRemoveComment(String removeComment) {
        this.removeComment = removeComment;
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

    public Actor getReporter() {
        return reporter;
    }

    public void setReporter(Actor reporter) {
        this.reporter = reporter;
    }

    public void setSupervisor(Actor reporter) {
        this.reporter = reporter;
    }

    public Actor getAssignee() {
        return assignee;
    }

    public void setAssignee(Actor assignee) {
        this.assignee = assignee;
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

    public MaintenanceRequest getMaintenanceRequest() {
        return maintenanceRequest;
    }

    public void setMaintenanceRequest(MaintenanceRequest maintenanceRequest) {
        this.maintenanceRequest = maintenanceRequest;
    }
}
