package my.spotit.asset.common.business.service;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author team canang
 */
public interface StorageService {

    File getFile(String uuid);

    InputStream getInputStream(String uuid);

    OutputStream getOutputStream(String uuid);

}
