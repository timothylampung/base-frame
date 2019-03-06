package my.spotit.asset.common.api.controller;


import my.spotit.asset.common.api.vo.File;
import my.spotit.asset.common.api.vo.GradeCode;
import my.spotit.asset.common.api.vo.PositionCode;
import my.spotit.asset.common.domain.model.DexFile;
import my.spotit.asset.common.domain.model.DexGradeCode;
import my.spotit.asset.common.domain.model.DexPositionCode;
import my.spotit.asset.core.api.controller.CoreTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 *
 */
@Component("commonTransformer")
public class CommonTransformer {

    private CoreTransformer coreTransformer;

    @Autowired
    public CommonTransformer(CoreTransformer coreTransformer) {
        this.coreTransformer = coreTransformer;
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

    public File toFileVo(DexFile e) {
        if (null == e) return null;
        File vo = new File();
        vo.setId(e.getId());
        vo.setFileLocation(e.getFileLocation());
        vo.setFileName(e.getFileName());
        vo.setFileType(e.getFileType());
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<File> toFileVos(List<DexFile> e) {
        List<File> vos = e.stream().map((e1) -> toFileVo(e1)).collect(Collectors.toList());
        return vos;
    }


}

