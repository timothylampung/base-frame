package com.assettagging.spotit.maintenance.domain.model;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.api.MetaObject;
import com.assettagging.spotit.core.domain.DexDocument;
import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexStaff;

import java.util.Date;

public interface DexMaintenanceRequest extends DexDocument {

    String getRemark();

    void setRemark(String remark);

    Date getRequestedDate();

    void setRequestedDate(Date requestedDate);

    Boolean isDelegated();

    void setDelegated(Boolean delegated);

    DexActor getDelegator();

    void setDelegator(DexActor actor);

    DexActor getVerifier();

    void setVerifier(DexActor actor);

    DexActor getRequester();

    void setRequester(DexActor requester);

    DexLocation getLocation();

    void setLocation(DexLocation location);

    DexAsset getAsset();

    void setAsset(DexAsset asset);
}
