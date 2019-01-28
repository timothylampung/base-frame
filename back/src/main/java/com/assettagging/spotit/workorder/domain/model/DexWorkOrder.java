package com.assettagging.spotit.workorder.domain.model;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;

public interface DexWorkOrder extends DexMetaObject {

    void setId(Long id);

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    DexMetadata getDexMetadata();

    void setDexMetadata(DexMetadata dexMetadata);

    DexActor getReporter();

    void setReporter(DexActor reporter);

    DexActor getAssignee();

    void setAssignee(DexActor assignee);

    DexMaintenanceRequest getMaintenanceRequestId();

    void setMaintenanceRequest(DexMaintenanceRequest maintenanceRequestId);

    DexLocation getLocationId();

    void setLocation(DexLocation locationId);

    DexAsset getAssetId();

    void setAsset(DexAsset assetId);
}
