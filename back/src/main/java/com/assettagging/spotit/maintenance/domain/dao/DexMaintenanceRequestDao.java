package com.assettagging.spotit.maintenance.domain.dao;

import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface DexMaintenanceRequestDao extends GenericDao<Long, DexMaintenanceRequest> {

    List<DexMaintenanceRequest> findAllMaintenanceRequest();

    void addMaintenanceRequest(DexMaintenanceRequest maintenanceRequest, DexLocation location, DexActor requester, DexUser user);

    DexMaintenanceRequest findByCode(String code);

    List<DexMaintenanceRequest> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);

}
