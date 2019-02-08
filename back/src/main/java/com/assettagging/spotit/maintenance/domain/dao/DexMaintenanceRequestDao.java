package com.assettagging.spotit.maintenance.domain.dao;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;

import java.util.List;

public interface DexMaintenanceRequestDao extends GenericDao<Long, DexMaintenanceRequest> {

    DexMaintenanceRequest findByReferenceNo(String referenceNo);

    List<DexMaintenanceRequest> find(String filter, Integer offset, Integer limit);

    List find(DexAsset asset, Integer offset, Integer limit);

    Integer count(String filter);

    Integer count(DexAsset asset);


//    void addItem(DexMaintenanceRequest request, DexMaintenanceRequestItem item, DexUser user);
//    void updateItem(DexMaintenanceRequest request, DexMaintenanceRequestItem item, DexUser user);
//    void deleteItem(DexMaintenanceRequest request, DexMaintenanceRequestItem item, DexUser user);

}
