package my.spotit.asset.common.api.vo;

import my.spotit.asset.core.api.MetaObject;

public class File extends MetaObject {
    private String fileName;
    private String fileType;
    private String fileLocation;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
