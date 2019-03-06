//package my.spotit.asset.common.business.service;
//
//import my.spotit.asset.DexConstants;
//import my.spotit.asset.system.business.service.SystemService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.*;
//
///**
// * @author team canang
// */
//@Component("storageService")
//public class StorageServiceImpl implements StorageService {
//
//    private static final Logger LOG = LoggerFactory.getLogger(StorageServiceImpl.class);
//
//    private SystemService systemService;
//
//    public StorageServiceImpl(SystemService systemService) {
//        this.systemService = systemService;
//    }
//
//    @PostConstruct
//    public void init(){
///*
//        String key = CngConstants.STORAGE_PATH;
//        CngConfiguration storageKey = systemService.findConfigurationByKey(key);
//        if (storageKey != null) {
//            String rootPath = storageKey.getValue();
//            LOG.debug("rootPath: " + rootPath);
//            File file = new File(rootPath);
////            if(!file.isDirectory() && !file.exists())
////                throw new IllegalArgumentException();
//        } else {
//            LOG.error("Cannot find key: " + key);
//        }
//*/
//    }
//
//    public File getFile(String uuid) {
//        return new File(getRootPath() + uuid);
//    }
//
//    public InputStream getInputStream(String uuid) {
//        File f = new File(getRootPath() + uuid);
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(f);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return fis;
//    }
//
//    @Override
//    public OutputStream getOutputStream(String uuid) {
//        File f = new File(getRootPath() + uuid);
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(f);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return fos;
//    }
//
//    private String getRootPath() {
//        String rootPath = systemService.findConfigurationByKey(DexConstants.STORAGE_PATH).getValue();
//        if (rootPath.charAt(rootPath.length() - 1) != File.separatorChar)
//            rootPath = rootPath + File.separatorChar;
//        return rootPath;
//    }
//
//
//}