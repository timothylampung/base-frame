package com.assettagging.spotit.identity.domain.model;

import com.assettagging.spotit.common.domain.model.DexPositionCode;
import com.assettagging.spotit.common.domain.model.DexPositionCodeImpl;

import javax.persistence.*;


/**
 * @author canang technologies
 */
@Entity(name = "DexStaff")
@Table(name = "DEX_STAF")
public class DexStaffImpl extends DexActorImpl implements DexStaff {

    public DexStaffImpl() {
        super();
        setActorType(DexActorType.STAFF);
    }

    @ManyToOne(targetEntity = DexPositionCodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_CODE_ID")
    private DexPositionCode positionCode;

    @Override
    public DexPositionCode getPositionCode() {
        return positionCode;
    }

    @Override
    public void setPositionCode(DexPositionCode positionCode) {
        this.positionCode = positionCode;
    }

    public Class<?> getInterfaceClass() {
        return DexStaff.class;
    }
}
