package com.assettagging.spotit.maintenance.domain.model;

import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.api.MetaObject;
import com.assettagging.spotit.core.domain.DexDocument;
import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.identity.domain.model.DexActor;

public interface DexMaintenanceRequest extends DexDocument {

    DexActor getRequester();

    void setRequester(DexActor requester);

    void setId(Long id);

    DexLocation getLocation();

    void setLocation(DexLocation location);
}
