package my.spotit.asset.workorder.business.event;


import my.spotit.asset.workorder.domain.model.DexWorkOrder;

/**
 * @author canang technologies
 */
public class WorkOrderCancelledEvent extends WorkOrderEvent {

    public WorkOrderCancelledEvent(DexWorkOrder order) {
        super(order);
    }
}
