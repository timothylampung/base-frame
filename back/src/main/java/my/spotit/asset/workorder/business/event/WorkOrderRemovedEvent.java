package my.spotit.asset.workorder.business.event;


import my.spotit.asset.workorder.domain.model.DexWorkOrder;

/**
 * @author canang technologies
 */
public class WorkOrderRemovedEvent extends WorkOrderEvent {

    public WorkOrderRemovedEvent(DexWorkOrder order) {
        super(order);
    }
}
