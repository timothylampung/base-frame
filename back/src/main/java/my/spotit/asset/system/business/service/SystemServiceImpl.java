package my.spotit.asset.system.business.service;

import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.domain.dao.DexConfigurationDao;
import my.spotit.asset.system.domain.dao.DexModuleDao;
import my.spotit.asset.system.domain.dao.DexReferenceNoDao;
import my.spotit.asset.system.domain.dao.DexSubModuleDao;
import my.spotit.asset.system.domain.model.DexConfiguration;
import my.spotit.asset.system.domain.model.DexModule;
import my.spotit.asset.system.domain.model.DexSequenceGenerator;
import my.spotit.asset.system.domain.model.DexSubModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

/**
 */
@Transactional
@Service("systemService")
public class SystemServiceImpl implements SystemService {

    public static final DateFormat LONG_YEAR_FORMAT = new SimpleDateFormat("yyyy");
    public static final DateFormat SHORT_YEAR_FORMAT = new SimpleDateFormat("yy");
    public static final DateFormat LONG_MONTH_FORMAT = new SimpleDateFormat("MMM");
    public static final DateFormat SHORT_MONTH_FORMAT = new SimpleDateFormat("MM");
    public static final DateFormat LONG_DAY_FORMAT = new SimpleDateFormat("dd");
    public static final DateFormat SHORT_DAY_FORMAT = new SimpleDateFormat("dd");
    public static final DateFormat LONG_HOUR_FORMAT = new SimpleDateFormat("hh");
    public static final DateFormat SHORT_HOUR_FORMAT = new SimpleDateFormat("hh");

    private static final Logger LOG = LoggerFactory.getLogger(SystemServiceImpl.class);

    private EntityManager entityManager;
    private ApplicationContext applicationContext;
    private IdentityService identityService;
    private SecurityService securityService;
    private DexModuleDao moduleDao;
    private DexSubModuleDao subModuleDao;
    private DexConfigurationDao configurationDao;
    private DexReferenceNoDao referenceNoDao;

    @Autowired
    public SystemServiceImpl(EntityManager entityManager, ApplicationContext applicationContext,
                             IdentityService identityService, SecurityService securityService,
                             DexModuleDao moduleDao, DexSubModuleDao subModuleDao,
                             DexConfigurationDao configurationDao, DexReferenceNoDao referenceNoDao) {
        this.entityManager = entityManager;
        this.applicationContext = applicationContext;
        this.identityService = identityService;
        this.securityService = securityService;
        this.moduleDao = moduleDao;
        this.subModuleDao = subModuleDao;
        this.configurationDao = configurationDao;
        this.referenceNoDao = referenceNoDao;
    }

    //==============================================================================================
    // MODULE SUBMODULE
    //==============================================================================================

    @Override
    public DexModule findModuleById(Long id) {
        return moduleDao.findById(id);
    }

    @Override
    public DexSubModule findSubModuleById(Long id) {
        return subModuleDao.findById(id);
    }

    @Override
    public DexModule findModuleByCode(String code) {
        return moduleDao.findByCode(code);
    }

    @Override
    public DexSubModule findSubModuleByCode(String code) {
        return subModuleDao.findByCode(code);
    }

    @Override
    public List<DexModule> findModules(boolean authorized) {
//        return authorized ?
//                moduleDao.findAuthorized(identityService.findSids(securityService.getCurrentUser())) :
//                moduleDao.find();
        return null;
    }

    @Override
    public List<DexModule> findModules() {
        return moduleDao.find();
    }

    @Override
    public List<DexModule> findModules(Integer offset, Integer limit) {
        return moduleDao.find(offset, limit);
    }

    @Override
    public List<DexModule> findModules(boolean authorized, Integer offset, Integer limit) {
//        return authorized ?
//                moduleDao.findAuthorized(identityService.findSids(securityService.getCurrentUser()), offset, limit) :
//                moduleDao.find(offset, limit);
        return null;
    }

    @Override
    public List<DexSubModule> findSubModules() {
        return subModuleDao.find();
    }

    @Override
    public List<DexSubModule> findSubModules(boolean authorized) {
//        return authorized ?
//                subModuleDao.findAuthorized(identityService.findSids(securityService.getCurrentUser())) :
//                subModuleDao.find();
        return null;
    }

    @Override
    public List<DexSubModule> findSubModules(Integer offset, Integer limit) {
        return subModuleDao.find(offset, limit);
    }

    @Override
    public List<DexSubModule> findSubModules(DexModule module, Integer offset, Integer limit) {
        return subModuleDao.find(module, offset, limit);
    }

    @Override
    public Integer countModule() {
        return moduleDao.count();
    }

    @Override
    public Integer countSubModule() {
        return subModuleDao.count();
    }

    @Override
    public Integer countSubModule(DexModule module) {
        return subModuleDao.count(module);
    }

    @Override
    public void saveModule(DexModule module) {
        moduleDao.save(module, securityService.getCurrentUser());
    }

    @Override
    public void updateModule(DexModule module) {
        moduleDao.update(module, securityService.getCurrentUser());
    }

    @Override
    public void addSubModule(DexModule module, DexSubModule subModule) {
        moduleDao.addSubModule(module, subModule, securityService.getCurrentUser());
    }

    @Override
    public void updateSubModule(DexModule module, DexSubModule subModule) {
        moduleDao.updateSubModule(module, subModule, securityService.getCurrentUser());
    }

    //==============================================================================================
    // REFERENCE NO
    //==============================================================================================

    @Override
    public DexSequenceGenerator findReferenceNoById(Long id) {
        return referenceNoDao.findById(id);
    }

    @Override
    public DexSequenceGenerator findReferenceNoByCode(String code) {
        return referenceNoDao.findByCode(code);
    }

    @Override
    public List<DexSequenceGenerator> findReferenceNos(Integer offset, Integer limit) {
        return referenceNoDao.find(offset, limit);
    }

    @Override
    public List<DexSequenceGenerator> findReferenceNos(String filter, Integer offset, Integer limit) {
        return referenceNoDao.find(filter, offset, limit);
    }

    @Override
    public Integer countReferenceNo() {
        return referenceNoDao.count();
    }

    @Override
    public Integer countReferenceNo(String filter) {
        return referenceNoDao.count(filter);
    }

    @Override
    public void saveReferenceNo(DexSequenceGenerator referenceNo) {
        referenceNoDao.save(referenceNo, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateReferenceNo(DexSequenceGenerator referenceNo) {
        referenceNoDao.update(referenceNo, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void removeReferenceNo(DexSequenceGenerator referenceNo) {
        referenceNoDao.remove(referenceNo, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public String generateReferenceNo(String code) {
        String generatedRefNo = null;
        synchronized (this) {
            DexSequenceGenerator referenceNo = referenceNoDao.findByCode(code);
            Integer oldValue = referenceNo.getCurrentValue();
            Integer newValue = referenceNo.getCurrentValue() + referenceNo.getIncrementValue();

            // update
            referenceNo.setCurrentValue(newValue);
            referenceNoDao.save(referenceNo, securityService.getCurrentUser());
            entityManager.flush();
            NumberFormat numberFormat = new DecimalFormat(referenceNo.getSequenceFormat());

            // format
            generatedRefNo = referenceNo.getPrefix() + numberFormat.format(oldValue);
        }
        return generatedRefNo;
    }

    public String generateFormattedReferenceNo(String code, Map<String, Object> map) {
        synchronized (this) {
            DexSequenceGenerator referenceNo = referenceNoDao.findByCode(code);

            // get old and new value
            Integer oldValue = referenceNo.getCurrentValue();
            Integer newValue = referenceNo.getCurrentValue() + referenceNo.getIncrementValue();

            // update
            referenceNo.setCurrentValue(newValue);
            referenceNoDao.save(referenceNo, securityService.getCurrentUser());
            entityManager.flush();

            Date now = new Date();
            NumberFormat numberFormat = new DecimalFormat(referenceNo.getSequenceFormat());
            SpelParserConfiguration configuration = new SpelParserConfiguration(true, true);
            StandardEvaluationContext context = new StandardEvaluationContext(configuration);
            ParserContext templateContext = new TemplateParserContext("{", "}");
            context.setVariable("a", referenceNo.getPrefix());
            context.setVariable("b", LONG_YEAR_FORMAT.format(now));
            context.setVariable("c", SHORT_YEAR_FORMAT.format(now));
            context.setVariable("d", LONG_MONTH_FORMAT.format(now));
            context.setVariable("e", SHORT_MONTH_FORMAT.format(now));
            context.setVariable("f", LONG_DAY_FORMAT.format(now));
            context.setVariable("g", SHORT_DAY_FORMAT.format(now));
            context.setVariable("h", LONG_HOUR_FORMAT.format(now));
            context.setVariable("i", SHORT_HOUR_FORMAT.format(now));
            context.setVariable("j", numberFormat.format(oldValue));

            for (String key : map.keySet()) {
                context.setVariable(key, map.get(key));
            }

            SpelExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(referenceNo.getReferenceFormat(), templateContext);
            return expression.getValue(context, String.class);
        }
    }

    //==============================================================================================
    // CONFIGURATION
    //==============================================================================================

    @Override
    public DexConfiguration findConfigurationById(Long id) {
        return configurationDao.findById(id);
    }

    @Override
    public DexConfiguration findConfigurationByKey(String key) {
        return configurationDao.findByKey(key);
    }

    @Override
    public List<DexConfiguration> findConfigurationsByPrefix(String prefix) {
        return configurationDao.findByPrefix(prefix);
    }

    @Override
    public List<DexConfiguration> findConfigurations() {
        return configurationDao.find();
    }

    @Override
    public List<DexConfiguration> findConfigurations(Integer offset, Integer limit) {
        return configurationDao.find(offset, limit);
    }

    @Override
    public List<DexConfiguration> findConfigurations(String filter) {
        return configurationDao.find(filter);
    }

    @Override
    public List<DexConfiguration> findConfigurations(String filter, Integer offset, Integer limit) {
        return configurationDao.find(filter, offset, limit);
    }


    @Override
    public Integer countConfiguration() {
        return configurationDao.count();
    }

    @Override
    public Integer countConfiguration(String filter) {
        return configurationDao.count(filter);
    }

    @Override
    public void saveConfiguration(DexConfiguration config) {
        configurationDao.save(config, securityService.getCurrentUser());
    }

    @Override
    public void updateConfiguration(DexConfiguration config) {
        configurationDao.update(config, securityService.getCurrentUser());
    }

    @Override
    public void removeConfiguration(DexConfiguration config) {
        configurationDao.remove(config, securityService.getCurrentUser());
    }
}
