package my.spotit.asset.common.business.service;

import my.spotit.asset.common.domain.model.DexFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {
    DexFile storeFile(MultipartFile file) ;

    DexFile getFile(Long fileId);
}
