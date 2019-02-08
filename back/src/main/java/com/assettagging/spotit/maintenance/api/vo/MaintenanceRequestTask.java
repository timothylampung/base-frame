package com.assettagging.spotit.maintenance.api.vo;

import com.assettagging.spotit.core.api.Task;

public class MaintenanceRequestTask extends Task {

    private MaintenanceRequest request;

    public MaintenanceRequest getRequest() {
        return request;
    }

    public void setRequest(MaintenanceRequest request) {
        this.request = request;
    }
}
