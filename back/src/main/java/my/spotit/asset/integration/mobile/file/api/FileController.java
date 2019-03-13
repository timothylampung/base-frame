package my.spotit.asset.integration.mobile.file.api;

import my.spotit.asset.asset.api.controller.AssetTransformer;
import my.spotit.asset.asset.api.vo.Asset;
import my.spotit.asset.asset.api.vo.AssetCode;
import my.spotit.asset.asset.api.vo.Location;
import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.common.api.vo.File;
import my.spotit.asset.common.business.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/mobile/file")
public class FileController {
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    private FileService fileService;
    private FileTransformer fileTransformer;

    @Autowired
    public FileController(FileService fileService, FileTransformer fileTransformer) {
        this.fileService = fileService;
        this.fileTransformer = fileTransformer;
    }

    @GetMapping(value = "/files")
    public ResponseEntity<List<File>> findAssets() {
        return new ResponseEntity<List<File>>(fileTransformer.toFileVos(
                fileService.findFiles("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }


    @GetMapping(value = "/download-file/{fileName}/")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileService.loadFileAsResource(fileName);
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(resource.getFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(bytes);
    }


}
