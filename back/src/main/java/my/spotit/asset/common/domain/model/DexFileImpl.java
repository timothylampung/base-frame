package my.spotit.asset.common.domain.model;

import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;

@Entity
@Table(name = "DEX_FILE")
public class DexFileImpl implements DexFile{

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_FILES")
    @SequenceGenerator(name = "SQ_DEX_FILES", sequenceName = "SQ_DEX_FILES", allocationSize = 1)
    private Long id;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "FILE_TYPE")
    private String fileType;
    @Column(name = "FILE_LOCATION")
    private String fileLocation;
    @Embedded
    private DexMetadata metadata;

    public DexFileImpl() {
    }

    public DexFileImpl(String fileName, String fileType, String fileLocation) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileLocation = fileLocation;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getFileType() {
        return fileType;
    }

    @Override
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String getFileLocation() {
        return fileLocation;
    }

    @Override
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Override
    public DexMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexFile.class;
    }
}