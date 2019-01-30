package com.assettagging.spotit.system.api.controller;

import com.assettagging.spotit.system.api.vo.Configuration;
import com.assettagging.spotit.system.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.assettagging.spotit.system.api.vo.Module;
import com.assettagging.spotit.system.api.vo.ReferenceNo;
import com.assettagging.spotit.system.api.vo.SubModule;
import com.assettagging.spotit.system.business.service.SystemService;

/**
 */
@RestController
@Transactional
@RequestMapping("/api/system")
public class SystemController {

    private SystemService systemService;
    private SystemTransformer systemTransformer;
    private AuthenticationManager authenticationManager;

    @Autowired
    public SystemController(SystemService systemService, SystemTransformer systemTransformer,
                            AuthenticationManager authenticationManager) {
        this.systemService = systemService;
        this.systemTransformer = systemTransformer;
        this.authenticationManager = authenticationManager;
    }

    // =============================================================================================
    // MODULE SUBMODULE
    // =============================================================================================

    @GetMapping(value = "/modules")
    public ResponseEntity<List<Module>> findModules() {
        List<DexModule> modules = systemService.findModules();
        return new ResponseEntity<List<Module>>(systemTransformer.toModuleVos(modules), HttpStatus.OK);
    }

    @GetMapping(value = "/module/{code}/submodules")
    public ResponseEntity<List<SubModule>> findSubmodules(@PathVariable String moduleCode) {
        DexModule module = systemService.findModuleByCode(moduleCode);
        List<DexSubModule> subModules = systemService.findSubModules(module, 0, 99);
        return new ResponseEntity<List<SubModule>>(systemTransformer.toSubModuleVos(subModules), HttpStatus.OK);
    }

    // =============================================================================================
    // CONFIGURATION
    // =============================================================================================

    @GetMapping(value = "/configurations")
    public ResponseEntity<List<Configuration>> findConfigurations() {
        List<DexConfiguration> configurations = systemService.findConfigurations();
        return new ResponseEntity<List<Configuration>>(systemTransformer.toConfigurationVos(configurations), HttpStatus.OK);
    }

    @PostMapping(value = "/configuration")
    public ResponseEntity<String> saveConfiguration(@RequestBody Configuration vo) {
        DexConfiguration configuration = new DexConfigurationImpl();
        configuration.setKey(vo.getKey());
        configuration.setDescription(vo.getDescription());
        configuration.setValue(vo.getValue());
        systemService.saveConfiguration(configuration);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // =============================================================================================
    // REFERENCE NO
    // =============================================================================================

    @GetMapping(value = "/referenceNos")
    public ResponseEntity<List<ReferenceNo>> findReferenceNos() {
        List<DexSequenceGenerator> referenceNos = systemService.findReferenceNos(0, 99);
        return new ResponseEntity<List<ReferenceNo>>(systemTransformer.toReferenceNoVos(referenceNos), HttpStatus.OK);
    }

    @PostMapping(value = "/referenceNo")
    public ResponseEntity<String> saveReferenceNo(@RequestBody ReferenceNo vo) {
        DexSequenceGenerator referenceNo = new DexSequenceGeneratorImpl();
        referenceNo.setCode(vo.getCode());
        referenceNo.setDescription(vo.getDescription());
        referenceNo.setPrefix(vo.getPrefix());
        referenceNo.setReferenceFormat(vo.getReferenceFormat());
        referenceNo.setSequenceFormat(vo.getSequenceFormat());
        referenceNo.setIncrementValue(vo.getIncrementValue());
        referenceNo.setCurrentValue(vo.getCurrentValue());
        systemService.saveReferenceNo(referenceNo);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // =============================================================================================
    // PRIVATE METHODS
    // =============================================================================================
}
