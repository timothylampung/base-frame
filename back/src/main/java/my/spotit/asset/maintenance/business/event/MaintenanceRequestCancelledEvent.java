package my.spotit.asset.maintenance.business.event;


import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;

/**
 * @author canang technologies
 */
public class MaintenanceRequestCancelledEvent extends MaintenanceRequestEvent {

    public MaintenanceRequestCancelledEvent(DexMaintenanceRequest request) {
        super(request);
    }
}
