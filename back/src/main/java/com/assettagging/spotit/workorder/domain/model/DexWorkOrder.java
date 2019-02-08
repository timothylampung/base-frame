package com.assettagging.spotit.workorder.domain.model;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.domain.DexDocument;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;

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
}
