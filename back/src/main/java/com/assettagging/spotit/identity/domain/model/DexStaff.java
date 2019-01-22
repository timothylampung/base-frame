package com.assettagging.spotit.identity.domain.model;


import com.assettagging.spotit.common.domain.model.DexPositionCode;

/**
 * @author  canang technologies
 */
public interface DexStaff extends DexActor {


    String getStaffCode();

    void setStaffCode(String staffCode);
}
