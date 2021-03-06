package my.spotit.asset.system.api.controller;

import my.spotit.asset.system.api.vo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import my.spotit.asset.system.api.vo.Module;
import my.spotit.asset.system.api.vo.ReferenceNo;
import my.spotit.asset.system.api.vo.SubModule;
import my.spotit.asset.system.business.service.SystemService;
import my.spotit.asset.system.domain.model.DexConfiguration;
import my.spotit.asset.system.domain.model.DexConfigurationImpl;
import my.spotit.asset.system.domain.model.DexModule;
import my.spotit.asset.system.domain.model.DexSequenceGenerator;
import my.spotit.asset.system.domain.model.DexSequenceGeneratorImpl;
import my.spotit.asset.system.domain.model.DexSubModule;

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
        List<DexSequenceGenerator> referenceNos = systemService.findSequenceGenerators(0, 99);
        return new ResponseEntity<List<ReferenceNo>>(systemTransformer.toReferenceNoVos(referenceNos), HttpStatus.OK);
    }

    @PostMapping(value = "/referenceNo")
    public ResponseEntity<String> saveReferenceNo(@RequestBody ReferenceNo vo) {
        DexSequenceGenerator sequence = new DexSequenceGeneratorImpl();
        sequence.setCode(vo.getCode());
        sequence.setDescription(vo.getDescription());
        sequence.setPrefix(vo.getPrefix());
        sequence.setReferenceFormat(vo.getReferenceFormat());
        sequence.setSequenceFormat(vo.getSequenceFormat());
        sequence.setIncrementValue(vo.getIncrementValue());
        sequence.setCurrentValue(vo.getCurrentValue());
        systemService.saveSequenceGenerator(sequence);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // =============================================================================================
    // PRIVATE METHODS
    // =============================================================================================
}
