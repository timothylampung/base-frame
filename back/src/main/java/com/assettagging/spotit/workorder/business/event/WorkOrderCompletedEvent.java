package com.assettagging.spotit.workorder.business.event;


import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

/**
 * @author canang technologies
 */
public class WorkOrderCompletedEvent extends WorkOrderEvent {

    public WorkOrderCompletedEvent(DexWorkOrder order) {
        super(order);
    }
}
