package my.spotit.asset.common.api.controller;


import my.spotit.asset.common.api.vo.File;
import my.spotit.asset.common.domain.model.DexFile;
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

