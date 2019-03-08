package my.spotit.asset.common.business.service;

import my.spotit.asset.DexConstants;
import my.spotit.asset.common.domain.dao.DexFileDao;
import my.spotit.asset.common.domain.model.DexFile;
import my.spotit.asset.common.domain.model.DexFileImpl;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.business.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    private DexFileDao fileDao;
    private SecurityService securityService;
    private EntityManager entityManager;
    private Path path;
    private SystemService systemService;

    @Autowired
    public FileServiceImpl(DexFileDao fileDao, SecurityService securityService, EntityManager entityManager, SystemService systemService) {
        this.fileDao = fileDao;
        this.securityService = securityService;
        this.entityManager = entityManager;
        this.systemService = systemService;


        try {
            this.path = Paths.get(systemService.findConfigurationByKey(DexConstants.CONFIG_UPLOAD_PATH).getValue()).toAbsolutePath().normalize();
            if (this.path == null) throw new AssertionError();
            Files.createDirectories(this.path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public DexFile storeFile(MultipartFile multipartFile) {
        String fileName = "dex_" + StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            File file = new File(systemService.findConfigurationByKey(DexConstants.CONFIG_UPLOAD_PATH).getValue() + "/" + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.flush();
            fos.close();

            if (fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/common/download-file/")
                    .path(fileName)
                    .toUriString();

            DexFile dbFile = new DexFileImpl(fileName, multipartFile.getContentType(), fileDownloadUri);
            fileDao.save(dbFile, securityService.getCurrentUser());
            entityManager.flush();
            entityManager.refresh(dbFile);
            return dbFile;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public DexFile getFile(Long fileId) {
        return fileDao.findById(fileId);
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        Path filePath = this.path.resolve(fileName).normalize();
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return resource;
    }
}