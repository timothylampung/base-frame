package com.assettagging.spotit.identity.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "DexTechnician")
@Table(name = "DEX_TECH")
public class DexTechnicianImpl extends DexActorImpl implements DexTechnician{



    public DexTechnicianImpl() {
        super();
        setActorType(DexActorType.TECHNICIAN);
    }

    @Override
    public Class<?> getInterfaceClass() { return DexTechnician.class; }
}
