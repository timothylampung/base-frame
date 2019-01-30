package com.assettagging.spotit.maintenance.business.event;


import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

/**
 * @author canang technologies
 */
public class MaintenanceRequestRemovedEvent extends MaintenanceRequestEvent {

    public MaintenanceRequestRemovedEvent(DexMaintenanceRequest request) {
        super(request);
    }
}
