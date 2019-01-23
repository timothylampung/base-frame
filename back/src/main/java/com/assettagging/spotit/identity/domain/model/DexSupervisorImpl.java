package com.assettagging.spotit.identity.domain.model;

import javax.persistence.Entity;

@Entity(name = "DexSupervisor")

public class DexSupervisorImpl extends DexActorImpl implements DexSupervisor{

    public DexSupervisorImpl() {
        super();
        setActorType(DexActorType.SUPERVISOR);
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexSupervisor.class;
    }
}
