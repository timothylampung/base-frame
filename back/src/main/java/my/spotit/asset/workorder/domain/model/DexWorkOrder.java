package my.spotit.asset.workorder.domain.model;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.common.domain.model.DexFile;
import my.spotit.asset.core.domain.DexDocument;
import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;

public interface DexWorkOrder extends DexDocument {

    DexActor getReporter();

    void setSupervisor(DexActor reporter);

    DexActor getAssignee();

    void setAssignee(DexActor assignee);

    DexMaintenanceRequest getMaintenanceRequest();

    void setMaintenanceRequest(DexMaintenanceRequest maintenanceRequestId);

    DexLocation getLocation();

    void setLocation(DexLocation locationId);

    DexAsset getAsset();

    void setAsset(DexAsset assetId);

    void setReporter(DexActor reporter);

    DexFile getFile();

    void setFile(DexFile file);
}
