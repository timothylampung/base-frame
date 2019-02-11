package my.spotit.asset.system.business.service;


import java.util.List;
import java.util.Map;

import my.spotit.asset.system.domain.model.DexConfiguration;
import my.spotit.asset.system.domain.model.DexModule;
import my.spotit.asset.system.domain.model.DexSequenceGenerator;
import my.spotit.asset.system.domain.model.DexSubModule;

/**
 */
public interface SystemService {

    //==============================================================================================
    // MODULE SUB MODULE
    //==============================================================================================

    DexModule findModuleById(Long id);

    DexSubModule findSubModuleById(Long id);

    DexModule findModuleByCode(String code);

    DexSubModule findSubModuleByCode(String code);

    List<DexModule> findModules();

    List<DexModule> findModules(boolean authorized);

    List<DexModule> findModules(Integer offset, Integer limit);

    List<DexModule> findModules(boolean authorized, Integer offset, Integer limit);

    List<DexSubModule> findSubModules();

    List<DexSubModule> findSubModules(boolean authorized);

    List<DexSubModule> findSubModules(Integer offset, Integer limit);

    List<DexSubModule> findSubModules(DexModule module, Integer offset, Integer limit);

    Integer countModule();

    Integer countSubModule();

    Integer countSubModule(DexModule module);

    void saveModule(DexModule module);

    void updateModule(DexModule module);

    void addSubModule(DexModule module, DexSubModule subModule);

    void updateSubModule(DexModule module, DexSubModule subModule);

    //==============================================================================================
    // REFERENCE NO
    //==============================================================================================

    DexSequenceGenerator findReferenceNoById(Long id);

    DexSequenceGenerator findReferenceNoByCode(String code);

    List<DexSequenceGenerator> findReferenceNos(Integer offset, Integer limit);

    List<DexSequenceGenerator> findReferenceNos(String filter, Integer offset, Integer limit);

    Integer countReferenceNo();

    Integer countReferenceNo(String filter);

    void saveReferenceNo(DexSequenceGenerator referenceNo);

    void updateReferenceNo(DexSequenceGenerator referenceNo);

    void removeReferenceNo(DexSequenceGenerator referenceNo);

    String generateReferenceNo(String code);

    String generateFormattedReferenceNo(String code, Map<String, Object> map);

    //==============================================================================================
    // CONFIGURATION
    //==============================================================================================

    DexConfiguration findConfigurationById(Long id);

    DexConfiguration findConfigurationByKey(String key);

    List<DexConfiguration> findConfigurationsByPrefix(String prefix);

    List<DexConfiguration> findConfigurations();

    List<DexConfiguration> findConfigurations(Integer offset, Integer limit);

    List<DexConfiguration> findConfigurations(String filter);

    List<DexConfiguration> findConfigurations(String filter, Integer offset, Integer limit);

    Integer countConfiguration();

    Integer countConfiguration(String filter);

    void saveConfiguration(DexConfiguration config);

    void updateConfiguration(DexConfiguration config);

    void removeConfiguration(DexConfiguration config);
}
