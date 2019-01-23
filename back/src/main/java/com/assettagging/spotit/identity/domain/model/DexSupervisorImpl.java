package com.assettagging.spotit.identity.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "DexSupervisor")
@Table(name = "DEX_SUPR")
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
