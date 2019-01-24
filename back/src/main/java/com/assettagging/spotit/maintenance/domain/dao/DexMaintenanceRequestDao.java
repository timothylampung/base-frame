package com.assettagging.spotit.maintenance.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;

import java.util.List;

public interface DexMaintenanceRequestDao extends GenericDao<Long, DexMaintenanceRequest> {

    List<DexMaintenanceRequest> findAllMaintenanceRequest();
}
