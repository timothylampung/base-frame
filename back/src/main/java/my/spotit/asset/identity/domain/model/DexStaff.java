package my.spotit.asset.identity.domain.model;


import my.spotit.asset.common.domain.model.DexPositionCode;

/**
 * @author  canang technologies
 */
public interface DexStaff extends DexActor {

    DexPositionCode getPositionCode();

    void setPositionCode(DexPositionCode positionCode);
}
