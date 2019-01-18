package com.assettagging.spotit.common.api.controller;


import com.assettagging.spotit.common.api.vo.GradeCode;
import com.assettagging.spotit.common.api.vo.PositionCode;
import com.assettagging.spotit.common.domain.model.DexGradeCode;
import com.assettagging.spotit.common.domain.model.DexPositionCode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 */
@Component("commonTransformer")
public class CommonTransformer {

    public CommonTransformer() {
    }
    
    // =============================================================================================
    // POSITION_CODE
    // =============================================================================================

    public PositionCode toPositionCodeVo(DexPositionCode e) {
        if (null == e) return null;
        PositionCode vo = new PositionCode();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        return vo;
    }

    public List<PositionCode> toPositionCodeVos(List<DexPositionCode> e) {
        List<PositionCode> vos = e.stream().map((e1) -> toPositionCodeVo(e1)).collect(Collectors.toList());
        return vos;
    }

    // =============================================================================================
    // GRADE_CODE
    // =============================================================================================

    public GradeCode toGradeCodeVo(DexGradeCode e) {
        if (null == e) return null;
        GradeCode vo = new GradeCode();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        return vo;
    }

    public List<GradeCode> toGradeCodeVos(List<DexGradeCode> e) {
        List<GradeCode> vos = e.stream().map((e1) -> toGradeCodeVo(e1)).collect(Collectors.toList());
        return vos;
    }
}

