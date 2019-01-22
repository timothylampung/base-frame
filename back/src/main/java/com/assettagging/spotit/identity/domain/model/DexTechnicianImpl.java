package com.assettagging.spotit.identity.domain.model;

public class DexTechnicianImpl extends DexActorImpl implements DexTechnician{

    public DexTechnicianImpl() {
        super();
        setActorType(DexActorType.TECHNICIAN);
    }

    @Override
    public Class<?> getInterfaceClass() { return DexTechnician.class; }
}
