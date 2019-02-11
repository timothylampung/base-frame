package my.spotit.asset.maintenance.business.event;

import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * @author canang technologies
 */
public class MaintenanceRequestListener implements ApplicationListener<MaintenanceRequestEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceRequestListener.class);

    @Override
    public void onApplicationEvent(MaintenanceRequestEvent event) {
        LOG.debug("Listening to Maintenance Request Event --- {}", event);

        if(event instanceof MaintenanceRequestCancelledEvent){
            // do something

        }


        if(event instanceof MaintenanceRequestDraftedEvent){
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
