package my.spotit.core.api.controller;


import my.spotit.core.api.MetaObject;
import my.spotit.core.api.MetaState;
import my.spotit.core.domain.DexFlowObject;
import org.springframework.stereotype.Component;

import my.spotit.core.api.FlowObject;
import my.spotit.core.api.FlowState;
import my.spotit.core.domain.DexMetaObject;

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
