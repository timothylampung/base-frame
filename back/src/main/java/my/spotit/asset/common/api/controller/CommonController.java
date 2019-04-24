package my.spotit.asset.common.api.controller;

import my.spotit.asset.DexConstants;
import my.spotit.asset.common.business.service.FileService;
import my.spotit.asset.system.business.service.SystemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 *
 */
@Transactional
@RestController
@RequestMapping("/api/common")
public class CommonController {

    private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    private SystemService systemService;
    private CommonTransformer commonTransformer;
    private AuthenticationManager authenticationManager;
    private FileService fileService;


    @Autowired
    public CommonController(SystemService systemService,
                            CommonTransformer commonTransformer,
                            AuthenticationManager authenticationManager, FileService fileService) {
        this.systemService = systemService;
        this.commonTransformer = commonTransformer;
        this.authenticationManager = authenticationManager;
        this.fileService = fileService;
    }


    @GetMapping(value = "/download-file/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
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
