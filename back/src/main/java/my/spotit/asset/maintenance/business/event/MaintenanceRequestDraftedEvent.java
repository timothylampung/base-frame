package my.spotit.asset.maintenance.business.event;


import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;

/**
 * @author canang technologies
 */
public class MaintenanceRequestDraftedEvent extends MaintenanceRequestEvent {


    public MaintenanceRequestDraftedEvent(DexMaintenanceRequest request) {
        super(request);
    }
}
