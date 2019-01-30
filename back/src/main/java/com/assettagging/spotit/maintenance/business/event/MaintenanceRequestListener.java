package com.assettagging.spotit.maintenance.business.event;

import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import org.springframework.context.ApplicationListener;

/**
 * @author canang technologies
 */
public class MaintenanceRequestListener implements ApplicationListener<MaintenanceRequestEvent> {

    @Override
    public void onApplicationEvent(MaintenanceRequestEvent event) {

        if(event instanceof MaintenanceRequestCancelledEvent){
            // do something
        }

        // do something
        if(event instanceof MaintenanceRequestCompletedEvent){
            DexWorkOrder order = (DexWorkOrder) event.getSource();

            // subtract unit from inventories
            // order.getInventories();
        }
    }
}
