package com.assettagging.spotit.workorder.business.event;


import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

/**
 * @author canang technologies
 */
public class WorkOrderRemovedEvent extends WorkOrderEvent {

    public WorkOrderRemovedEvent(DexWorkOrder order) {
        super(order);
    }
}
