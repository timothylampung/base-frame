package com.assettagging.spotit.core.api.controller;


import com.assettagging.spotit.core.api.MetaObject;
import com.assettagging.spotit.core.api.MetaState;
import com.assettagging.spotit.core.domain.DexFlowObject;
import org.springframework.stereotype.Component;

import com.assettagging.spotit.core.api.FlowObject;
import com.assettagging.spotit.core.api.FlowState;
import com.assettagging.spotit.core.domain.DexMetaObject;

/**
 */
@Component("coreTransformer")
public class CoreTransformer {

    public void toMetadata(DexMetaObject m, MetaObject vo){
        vo.setCreatedDate(m.getMetadata().getCreatedDate());
        vo.setDeletedDate(m.getMetadata().getDeletedDate());
        vo.setModifiedDate(m.getMetadata().getModifiedDate());
        vo.setMetaState(MetaState.get(m.getMetadata().getState().ordinal()));
    }

    public void toFlowdata(DexFlowObject m, FlowObject vo){
        toMetadata(m,vo);
        vo.setFlowState(FlowState.get(m.getFlowdata().getState().ordinal()));
    }
}
