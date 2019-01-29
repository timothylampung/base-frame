package com.assettagging.spotit.workorder.business.event;

import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import org.springframework.context.ApplicationEvent;


/**
 * @author canang technologies
 */
public class WorkOrderEvent extends ApplicationEvent {

    public WorkOrderEvent(DexWorkOrder order) {
        super(order);
    }
}
