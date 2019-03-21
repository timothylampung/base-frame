package my.spotit.asset.common.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

public interface DexFile extends DexMetaObject {
    String getFileName();

    void setFileName(String fileName);

    String getFileType();

    void setFileType(String fileType);

    String getFileLocation();

    void setFileLocation(String fileLocation);
}
