package com.assettagging.spotit.maintenance.business.service;

import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface MaintenanceRequestService {

    //==============================================================================================
    // WORK ORDER
    //==============================================================================================

    DexMaintenanceRequest findMaintenanceRequestById (Long id);

    DexMaintenanceRequest findMaintenanceRequestByCode (String code);

    List<DexMaintenanceRequest> findMaintenanceRequests(String filter, Integer offset, Integer limit);

    Integer countMaintenanceRequest();

    Integer countMaintenanceRequest(String filter);

    void saveMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest);

    void updateMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest);

    void removeMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest);



//=====================
}
