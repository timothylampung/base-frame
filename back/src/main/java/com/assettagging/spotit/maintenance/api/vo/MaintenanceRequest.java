package com.assettagging.spotit.maintenance.api.vo;

import com.assettagging.spotit.core.api.Document;
import com.assettagging.spotit.core.api.MetaObject;
import com.assettagging.spotit.identity.api.vo.Actor;

public class MaintenanceRequest extends Document {
    private String description;
    private Actor requester;
    //TODO add location

    public MaintenanceRequest() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Actor getRequester() {
        return requester;
    }

    public void setRequester(Actor requester) {
        this.requester = requester;
    }
}
