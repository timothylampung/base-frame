package my.spotit.asset.workorder.business.event;


import my.spotit.asset.workorder.domain.model.DexWorkOrder;

/**
 * @author canang technologies
 */
public class WorkOrderDraftedEvent extends WorkOrderEvent {

    public WorkOrderDraftedEvent(DexWorkOrder order) {
        super(order);
    }

}
