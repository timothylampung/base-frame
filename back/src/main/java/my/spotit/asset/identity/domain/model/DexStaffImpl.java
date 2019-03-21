package my.spotit.asset.identity.domain.model;

import my.spotit.asset.common.domain.model.DexPositionCode;
import my.spotit.asset.common.domain.model.DexPositionCodeImpl;

import javax.persistence.*;


/**
 * @author canang technologies
 */
@Entity(name = "DexStaff")
@Table(name = "DEX_STAF")
public class DexStaffImpl extends DexActorImpl implements DexStaff {

    @ManyToOne(targetEntity = DexPositionCodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_CODE", nullable = true)
    private DexPositionCode positionCode;

    public DexStaffImpl() {
        super();
        setActorType(DexActorType.STAFF);
    }


    public Class<?> getInterfaceClass() {
        return DexStaff.class;
    }

    @Override
    public DexPositionCode getPositionCode() {
        return positionCode;
    }

    @Override
    public void setPositionCode(DexPositionCode positionCode) {
        this.positionCode = positionCode;
    }
}
