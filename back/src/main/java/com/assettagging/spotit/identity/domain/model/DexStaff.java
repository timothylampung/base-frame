package com.assettagging.spotit.identity.domain.model;


import com.assettagging.spotit.common.domain.model.DexPositionCode;

/**
 * @author  canang technologies
 */
public interface DexStaff extends DexActor {

    DexPositionCode getPositionCode();

    void setPositionCode(DexPositionCode positionCode);


}
