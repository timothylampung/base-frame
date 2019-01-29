package com.assettagging.spotit.maintenance.business.service;

import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;

import java.util.List;

public interface MaintenanceService {

    List<DexMaintenanceRequest> findAllMaintenanceRequest();

    void submitMaintenanceRequest(DexMaintenanceRequest maintenanceRequest, DexLocation location);

}
