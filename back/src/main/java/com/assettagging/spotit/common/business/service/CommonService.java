package com.assettagging.spotit.common.business.service;


import com.assettagging.spotit.common.domain.model.DexGradeCode;
import com.assettagging.spotit.common.domain.model.DexPositionCode;
import com.assettagging.spotit.common.domain.model.*;

import java.util.List;

/**
 * @author canang technologies
 */
public interface CommonService {

    //==============================================================================================
    // POSITION CODE
    //==============================================================================================

    DexPositionCode findPositionCodeById(Long id);

    DexPositionCode findPositionCodeByCode(String code);

    List<DexPositionCode> findPositionCodes(String filter, Integer offset, Integer limit);

    Integer countPositionCode();

    Integer countPositionCode(String filter);

    void savePositionCode(DexPositionCode PositionCode);

    void updatePositionCode(DexPositionCode PositionCode);

    void removePositionCode(DexPositionCode PositionCode);

    //==============================================================================================
    // POSITION CODE
    //==============================================================================================

    DexGradeCode findGradeCodeById(Long id);

    DexGradeCode findGradeCodeByCode(String code);

    List<DexGradeCode> findGradeCodes(String filter, Integer offset, Integer limit);

    Integer countGradeCode();

    Integer countGradeCode(String filter);

    void saveGradeCode(DexGradeCode GradeCode);

    void updateGradeCode(DexGradeCode GradeCode);

    void removeGradeCode(DexGradeCode GradeCode);
}
