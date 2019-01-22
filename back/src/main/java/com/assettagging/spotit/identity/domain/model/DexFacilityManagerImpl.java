package com.assettagging.spotit.identity.domain.model;

public class DexFacilityManagerImpl extends DexActorImpl implements DexFacilityManager {

    public DexFacilityManagerImpl() {
        super();
        setActorType(DexActorType.FACILITY_MANAGER);
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexFacilityManager.class;
    }
}
