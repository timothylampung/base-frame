package com.assettagging.spotit.identity.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "DexFacilityManager")
@Table(name = "DEX_FACI")
public class DexFacilityManagerImpl extends DexActorImpl implements DexFacilityManager {

    public DexFacilityManagerImpl() {
        super();
        setActorType(DexActorType.FACILITY_MANAGER);
    }

    @Override
    public Class<?> getInterfaceClass() { return DexFacilityManager.class; }
}
