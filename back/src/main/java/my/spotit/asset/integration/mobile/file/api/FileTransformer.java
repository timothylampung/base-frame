package my.spotit.asset.integration.mobile.file.api;

import my.spotit.asset.common.api.controller.CommonTransformer;
import my.spotit.asset.common.api.vo.File;
import my.spotit.asset.common.domain.model.DexFile;
import my.spotit.asset.core.api.Document;
import my.spotit.asset.core.api.controller.CoreTransformer;
import my.spotit.asset.core.domain.DexDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileTransformer {
    private CommonTransformer commonTransformer;
    private CoreTransformer coreTransformer;

    @Autowired
    public FileTransformer(CommonTransformer commonTransformer, CoreTransformer coreTransformer) {
        this.commonTransformer = commonTransformer;
        this.coreTransformer = coreTransformer;
    }

    public File toFileVo(DexFile e) {
        File vo = new File();
        if (e == null) return null;
        vo.setId(e.getId());
        vo.setFileType(e.getFileType());
        vo.setFileName(e.getFileName());
        vo.setFileLocation(e.getFileLocation());
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<File> toFileVos(List<DexFile> e) {
        return e.stream().map(this::toFileVo).collect(Collectors.toList());
    }
}
