package my.spotit.asset.workorder.business.event;


import my.spotit.asset.workorder.domain.model.DexWorkOrder;

/**
 * @author canang technologies
 */
public class WorkOrderCompletedEvent extends WorkOrderEvent {

    public WorkOrderCompletedEvent(DexWorkOrder order) {
        super(order);
    }
}
