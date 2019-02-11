package my.spotit.asset.workorder.business.event;

import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import org.springframework.context.ApplicationEvent;


/**
 * @author canang technologies
 */
public class WorkOrderEvent extends ApplicationEvent {

    public WorkOrderEvent(DexWorkOrder order) {
        super(order);
    }
}
