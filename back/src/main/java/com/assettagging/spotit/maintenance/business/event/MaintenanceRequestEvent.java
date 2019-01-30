package com.assettagging.spotit.maintenance.business.event;

import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import org.springframework.context.ApplicationEvent;


/**
 * @author canang technologies
 */
public class MaintenanceRequestEvent extends ApplicationEvent {

    public MaintenanceRequestEvent(DexMaintenanceRequest request) {
        super(request);
    }
}
