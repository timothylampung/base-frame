package my.spotit.asset.maintenance.business.event;

import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;

import org.springframework.context.ApplicationEvent;


/**
 * @author canang technologies
 */
public class MaintenanceRequestEvent extends ApplicationEvent {

    public MaintenanceRequestEvent(DexMaintenanceRequest request) {
        super(request);
    }
}
