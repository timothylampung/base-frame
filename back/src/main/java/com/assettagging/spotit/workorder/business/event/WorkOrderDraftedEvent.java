package com.assettagging.spotit.workorder.business.event;


import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

/**
 * @author canang technologies
 */
public class WorkOrderDraftedEvent extends WorkOrderEvent {

    public WorkOrderDraftedEvent(DexWorkOrder order) {
        super(order);
    }

}
