package com.assettagging.spotit.workorder.business.event;


import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

/**
 * @author canang technologies
 */
public class WorkOrderCancelledEvent extends WorkOrderEvent {

    public WorkOrderCancelledEvent(DexWorkOrder order) {
        super(order);
    }
}
