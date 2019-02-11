package my.spotit.system.api.controller;

import my.spotit.system.api.vo.Configuration;
import my.spotit.system.domain.model.DexConfiguration;
import my.spotit.system.domain.model.DexModule;
import my.spotit.system.domain.model.DexSequenceGenerator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import my.spotit.system.api.vo.Module;
import my.spotit.system.api.vo.ReferenceNo;
import my.spotit.system.api.vo.SubModule;
import my.spotit.system.domain.model.DexSubModule;

/**
 */
@Component("systemTransformer")
public class SystemTransformer {

    public SystemTransformer() {
    }

    // =============================================================================================
    // CONFIGURATION
    // =============================================================================================

    public List<Configuration> toConfigurationVos(List<DexConfiguration> e) {
        List<Configuration> vos = e.stream()
                .map((e1) -> toConfigurationVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public Configuration toConfigurationVo(DexConfiguration e) {
        Configuration vo = new Configuration();
        vo.setId(e.getId());
        vo.setKey(e.getKey());
        vo.setValue(e.getValue());
        vo.setDescription(e.getDescription());
        return vo;
    }

    public List<ReferenceNo> toReferenceNoVos(List<DexSequenceGenerator> e) {
        List<ReferenceNo> vos = e.stream()
                .map((e1) -> toReferenceNoVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public ReferenceNo toReferenceNoVo(DexSequenceGenerator e) {
        ReferenceNo vo = new ReferenceNo();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setPrefix(e.getPrefix());
        vo.setReferenceFormat(e.getReferenceFormat());
        vo.setSequenceFormat(e.getSequenceFormat());
        vo.setIncrementValue(e.getIncrementValue());
        vo.setCurrentValue(e.getCurrentValue());
        return vo;
    }

    public List<Module> toModuleVos(List<DexModule> e) {
        List<Module> vos = e.stream()
                .map((e1) -> toModuleVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public Module toModuleVo(DexModule e) {
        Module vo = new Module();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        return vo;
    }

    public List<SubModule> toSubModuleVos(List<DexSubModule> e) {
        List<SubModule> vos = e.stream()
                .map((e1) -> toSubModuleVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public SubModule toSubModuleVo(DexSubModule e) {
        SubModule vo = new SubModule();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        return vo;
    }
}
