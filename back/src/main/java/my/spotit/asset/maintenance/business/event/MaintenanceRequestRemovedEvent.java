package my.spotit.asset.maintenance.business.event;


import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;

/**
 * @author canang technologies
 */
public class MaintenanceRequestRemovedEvent extends MaintenanceRequestEvent {

    public MaintenanceRequestRemovedEvent(DexMaintenanceRequest request) {
        super(request);
    }
}
