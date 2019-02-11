package my.spotit.asset.workorder.business.event;

import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import org.springframework.context.ApplicationListener;

/**
 * @author canang technologies
 */
public class WorkOrderListener implements ApplicationListener<WorkOrderEvent> {

    @Override
    public void onApplicationEvent(WorkOrderEvent event) {

        if(event instanceof WorkOrderCancelledEvent){
            // do something
        }

        // do something
        if(event instanceof WorkOrderCompletedEvent){
            DexWorkOrder order = (DexWorkOrder) event.getSource();

            // subtract unit from inventories
            // order.getInventories();
        }
    }
}
