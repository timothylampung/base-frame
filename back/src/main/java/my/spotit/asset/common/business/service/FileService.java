package my.spotit.asset.common.business.service;

import my.spotit.asset.common.domain.model.DexFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileService {
    DexFile storeFile(MultipartFile file) ;

    DexFile getFile(Long fileId);

    Resource loadFileAsResource(String fileName);

    List<DexFile> findFiles(String filter, int offset, int maxValue);
}
