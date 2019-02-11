package my.spotit.asset.maintenance.api.vo;

import my.spotit.asset.core.api.Task;

public class MaintenanceRequestTaskSummary extends Task {

    private MaintenanceRequest request;

    public MaintenanceRequest getRequest() {
        return request;
    }

    public void setRequest(MaintenanceRequest request) {
        this.request = request;
    }
}
