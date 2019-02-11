package my.spotit.asset.maintenance.business.event;


import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;

/**
 * @author canang technologies
 */
public class MaintenanceRequestCompletedEvent extends MaintenanceRequestEvent {

    public MaintenanceRequestCompletedEvent(DexMaintenanceRequest request) {
        super(request);
    }
}
