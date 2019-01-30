package com.assettagging.spotit.maintenance.api.controller;

import com.assettagging.spotit.core.api.MetaState;
import com.assettagging.spotit.core.api.controller.CoreTransformer;
import com.assettagging.spotit.identity.api.controller.IdentityTransformer;
import com.assettagging.spotit.identity.api.vo.Actor;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.maintenance.api.vo.MaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("maintenanceRequestTransformer")
public class MaintenanceRequestTransformer {

    private IdentityTransformer identityTransformer;
    private CoreTransformer coreTransformer;

    @Autowired
    public MaintenanceRequestTransformer(IdentityTransformer identityTransformer, CoreTransformer coreTransformer) {
        this.identityTransformer = identityTransformer;
        this.coreTransformer = coreTransformer;
    }

    public MaintenanceRequest toMaintenanceRequestVo(DexMaintenanceRequest e) {
        if (e == null) return null;

        MaintenanceRequest vo = new MaintenanceRequest();
        vo.setDescription(e.getDescription());
        vo.setDescription(e.getDescription());
        Actor requester = identityTransformer.toActor(e.getRequester());
        vo.setRequester(requester);
        coreTransformer.toMetadata(e, vo);
        vo.setId(e.getId());
        return vo;
    }
}
