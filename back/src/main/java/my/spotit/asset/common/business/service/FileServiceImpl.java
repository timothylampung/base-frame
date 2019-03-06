package my.spotit.asset.common.business.service;

import my.spotit.asset.common.domain.dao.DexFileDao;
import my.spotit.asset.common.domain.model.DexFile;
import my.spotit.asset.common.domain.model.DexFileImpl;
import my.spotit.asset.security.business.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    private DexFileDao fileDao;
    private SecurityService securityService;
    private EntityManager entityManager;

    @Autowired
    public FileServiceImpl(DexFileDao fileDao, SecurityService securityService, EntityManager entityManager) {
        this.fileDao = fileDao;
        this.securityService = securityService;
        this.entityManager = entityManager;
    }


    @Override
    public DexFile storeFile(MultipartFile multipartFile) {
        String fileName = "dex_"+StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.flush();
            fos.close();

            if (fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }
            DexFile dbFile = new DexFileImpl(fileName, multipartFile.getContentType(), file.getPath());
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
}