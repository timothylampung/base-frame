package my.spotit.asset.maintenance.business.service;

import my.spotit.asset.DexConstants;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.maintenance.business.event.MaintenanceRequestCancelledEvent;
import my.spotit.asset.maintenance.business.event.MaintenanceRequestDraftedEvent;
import my.spotit.asset.maintenance.domain.dao.DexMaintenanceRequestDao;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.business.service.SystemService;
import my.spotit.asset.workflow.business.service.WorkflowConstants;
import my.spotit.asset.workflow.business.service.WorkflowService;

import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.spotit.asset.DexConstants.MAINTENANCE_REQUEST_REFERENCE_NO;
import static my.spotit.asset.workflow.business.service.WorkflowConstants.DELIMITER;


@Transactional
@Service("maintenanceOrderService")
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceRequestServiceImpl.class);

    private EntityManager entityManager;
    private SecurityService securityService;
    private DexMaintenanceRequestDao maintenanceRequestDao;
    private WorkflowService workflowService;
    private ApplicationContext applicationContext;
    private SystemService systemService;

    @Autowired
    public MaintenanceRequestServiceImpl(EntityManager entityManager, SecurityService securityService, DexMaintenanceRequestDao maintenanceRequestDao, WorkflowService workflowService, ApplicationContext applicationContext, SystemService systemService) {
        this.entityManager = entityManager;
        this.securityService = securityService;
        this.maintenanceRequestDao = maintenanceRequestDao;
        this.workflowService = workflowService;
        this.applicationContext = applicationContext;
        this.systemService = systemService;
    }

    //MaintenanceRequest



    @Override
    public DexMaintenanceRequest findMaintenanceRequestById(Long id) {
        return maintenanceRequestDao.findById(id);
    }

    @Override
    public DexMaintenanceRequest findMaintenanceRequestByCode(String code) {
        return maintenanceRequestDao.findByReferenceNo(code);
    }

    @Override
    public List<DexMaintenanceRequest> findMaintenanceRequests(String filter, Integer offset, Integer limit) {
        return maintenanceRequestDao.find(filter, offset, limit);
    }

    @Override
    public Integer countMaintenanceRequest() {
        return maintenanceRequestDao.count();
    }

    @Override
    public Integer countMaintenanceRequest(String filter) {
        return maintenanceRequestDao.count(filter);
    }

    @Override
    public void saveMaintenanceRequest(DexMaintenanceRequest maintenanceRequest, DexLocation location) {
        DexUser currentUser = securityService.getCurrentUser();
        DexActor requester = currentUser.getActor();
        maintenanceRequest.setLocation(location);
        maintenanceRequest.setRequester(requester);

        maintenanceRequestDao.save(maintenanceRequest, currentUser);
        entityManager.flush();
    }

    @Override
    public void updateMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest) {
        maintenanceRequestDao.update(MaintenanceRequest, securityService.getCurrentUser());
        entityManager.flush();


    }

    @Override
    public void removeMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest) {
        maintenanceRequestDao.remove(MaintenanceRequest, securityService.getCurrentUser());
        entityManager.flush();
    }

//    Maintenance Request workflow

    @Override
    public String startMaintenanceRequestTask(DexMaintenanceRequest maintenanceRequest) throws Exception {
        LOG.debug(securityService.getCurrentUser().getName() + " is processing order");

        try {
            // generate reference no
            String referenceNo = systemService.generateReferenceNo(MAINTENANCE_REQUEST_REFERENCE_NO);
            maintenanceRequest.setReferenceNo(referenceNo);

            // save invoice
            maintenanceRequestDao.save(maintenanceRequest, securityService.getCurrentUser());
            entityManager.flush();
            entityManager.refresh(maintenanceRequest);

            // trigger process event
            workflowService.processWorkflow(maintenanceRequest, toMap(maintenanceRequest));

            // trigger event
            applicationContext.publishEvent(new MaintenanceRequestDraftedEvent(maintenanceRequest));

            return maintenanceRequest.getReferenceNo();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.debug("error occurred", e);
            throw new Exception(e);
        }
    }

    @Override
    public void cancelMaintenanceRequest(DexMaintenanceRequest maintenanceRequest) throws Exception {
        LOG.debug(securityService.getCurrentUser().getName() + " is canceling order");

        if (!DexFlowState.COMPLETED.equals(maintenanceRequest.getFlowdata().getState()))
            throw new Exception("Only completed MaintenanceRequest can be cancelled");
        maintenanceRequest.getFlowdata().setState(DexFlowState.CANCELLED);

        maintenanceRequest.getFlowdata().setCancelledDate(new Timestamp(System.currentTimeMillis()));
        maintenanceRequest.getFlowdata().setCancelerId(securityService.getCurrentUser().getId());
        maintenanceRequestDao.update(maintenanceRequest, securityService.getCurrentUser());
        entityManager.flush();
        entityManager.refresh(maintenanceRequest);

        // event
        applicationContext.publishEvent(new MaintenanceRequestCancelledEvent(maintenanceRequest));
    }

    @Override
    public DexMaintenanceRequest findMaintenanceRequestByTaskId(String taskId) {
        Task task = workflowService.findTask(taskId);
        Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
        return maintenanceRequestDao.findById((Long) map.get(DexConstants.REQUEST_ID));
    }

    @Override
    public DexMaintenanceRequest findMaintenanceRequestByRecordId(Long recordId) {
        return maintenanceRequestDao.findById(recordId);
    }

    @Override
    public Task findMaintenanceRequestTaskByTaskId(String taskId) {
        return workflowService.findTask(taskId);

    }

    @Override
    public List<Task> findAssignedMaintenanceRequestTasks(String filter, Integer offset, Integer limit) {
        String taskName = DexMaintenanceRequest.class.getCanonicalName() + DELIMITER;
        return workflowService.findAssignedTasks(filter, taskName, offset, limit);
    }

    @Override
    public List<Task> findPooledMaintenanceRequestTasks(String filter, Integer offset, Integer limit) {
        String taskName = DexMaintenanceRequest.class.getCanonicalName() + DELIMITER;
        return workflowService.findPooledTasks("%", taskName, offset, limit);
    }

    @Override
    public Integer countAssignedMaintenanceRequestTask(String filter) {
        String taskName = DexMaintenanceRequest.class.getCanonicalName() + DELIMITER;
        return workflowService.countAssignedTask(filter, taskName);
    }

    @Override
    public Integer countAssignedMaintenanceRequestTask(DexFlowState flowState) {
        String taskName = DexMaintenanceRequest.class.getCanonicalName() + DELIMITER + flowState.name();
        return workflowService.countAssignedTask(taskName);
    }

    @Override
    public Integer countPooledMaintenanceRequestTask(String filter) {
        String taskName = DexMaintenanceRequest.class.getCanonicalName() + DELIMITER;
        return workflowService.countPooledTask(taskName);
    }

    @Override
    public Integer countPooledMaintenanceRequestTask(DexFlowState flowState) {
        String taskName = DexMaintenanceRequest.class.getCanonicalName() + DELIMITER;
        return workflowService.countPooledTask(taskName);
    }

    @Override
    public DexMaintenanceRequest findMaintenanceRequestByReferenceNo(String referenceNo) {
        return maintenanceRequestDao.findByReferenceNo(referenceNo);
    }

    private Map<String, Object> toMap(DexMaintenanceRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
        map.put(WorkflowConstants.REFERENCE_NO, request.getReferenceNo());
        map.put(DexConstants.REQUEST_ID, request.getId());
        // by default set to false
        map.put(WorkflowConstants.QUERY_DECISION, false);
        map.put(WorkflowConstants.REMOVE_DECISION, false);
        map.put(WorkflowConstants.CANCEL_DECISION, false);
        return map;
    }
}
