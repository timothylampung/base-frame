package my.spotit.asset.config;

import my.spotit.asset.workflow.business.integration.identity.FlowableGroupManager;
import my.spotit.asset.workflow.business.integration.identity.FlowableUserManager;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.IdmEngineConfigurator;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.spring.ProcessEngineFactoryBean;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import my.spotit.asset.workflow.business.integration.listener.EndProcessListener;
import my.spotit.asset.workflow.business.integration.listener.StartProcessListener;
import my.spotit.asset.workflow.business.integration.listener.TakeProcessListener;
import my.spotit.asset.workflow.business.integration.listener.TaskAssignedListener;
import my.spotit.asset.workflow.business.integration.listener.TaskCreatedListener;

/**
 * @author canang technologies
 */
@Configuration
public class WorkflowConfig {

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FlowableUserManager flowableUserManager;

    @Autowired
    private FlowableGroupManager activitiGroupManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public ProcessEngineConfiguration processEngineConfiguration() {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setEngineName("Workflow Engine");
        configuration.setDataSource(dataSource);
        configuration.setDatabaseType(env.getProperty("db"));
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setHistory("full");
        configuration.setIdmProcessEngineConfigurator(idmEngineConfigurator());

        configuration.setHistory("audit");

        return configuration;
    }


    @Bean
    public IdmEngineConfigurator idmEngineConfigurator() {
        IdmEngineConfigurator idmEngineConfigurator = new IdmEngineConfigurator();
        idmEngineConfigurator.setIdmEngineConfiguration(idmEngineConfiguration());

        return idmEngineConfigurator;
    }

    @Bean
    public IdmEngineConfiguration idmEngineConfiguration() {
        IdmEngineConfiguration idmEngineConfiguration = new IdmEngineConfiguration();
        idmEngineConfiguration.setDataSource(dataSource);
        idmEngineConfiguration.setUserEntityManager(flowableUserManager);
        idmEngineConfiguration.setGroupEntityManager(activitiGroupManager);

        return idmEngineConfiguration;
    }

    @Bean
    public ProcessEngine processEngine() throws Exception {
        ProcessEngineFactoryBean engineFactoryBean = new ProcessEngineFactoryBean();
        engineFactoryBean.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration());
        engineFactoryBean.setApplicationContext(applicationContext);   // set application context !!!!
        return engineFactoryBean.getObject();
    }

    @Bean
    public RepositoryService repositoryService() throws Exception {
        return processEngine().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() throws Exception {
        RuntimeService runtimeService = processEngine().getRuntimeService();
        return runtimeService;
    }

    @Bean
    public TaskService taskService() throws Exception {
        return processEngine().getTaskService();
    }

    @Bean
    public IdentityService identityService() throws Exception {
        IdentityService identityService = processEngine().getIdentityService();
        return identityService;
    }

    @Bean
    public HistoryService historyService() throws Exception {
        return processEngine().getHistoryService();
    }

    @Bean
    public TaskCreatedListener taskCreatedListener() {
        return new TaskCreatedListener();
    }

    @Bean
    public TaskAssignedListener taskAssignedListener() {
        return new TaskAssignedListener();
    }

    @Bean
    public TakeProcessListener takeProcessListener() {
        return new TakeProcessListener();
    }

    @Bean
    public StartProcessListener startProcessListener() {
        return new StartProcessListener();
    }

    @Bean
    public EndProcessListener endProcessListener() {
        return new EndProcessListener();
    }
}
