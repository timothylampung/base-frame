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
@Service("maintenanceRequestService")
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceRequestServiceImpl.class);

    private EntityManager entityManager;
    private SecurityService securityService;
    private DexMaintenanceRequestDao maintenanceRequestDao;
    private WorkflowService workflowService;
    private ApplicationContext applicationContext;
    private SystemService systemService;

    @Autowired
    public MaintenanceRequestServiceImpl(EntityManager entityManager, SecurityService securityService,
                                         DexMaintenanceRequestDao maintenanceRequestDao,
                                         WorkflowService workflowService, ApplicationContext
                                                 applicationContext, SystemService systemService) {
        this.entityManager = entityManager;
        this.securityService = securityService;
        this.maintenanceRequestDao = maintenanceRequestDao;
        this.workflowService = workflowService;
        this.applicationContext = applicationContext;
        this.systemService = systemService;
    }

    // workflow
    @Override
    public String startMaintenanceRequestTask(DexMaintenanceRequest request) throws Exception {
        LOG.debug(securityService.getCurrentUser().getName() + " is processing order");

        try {
            // generate reference no
            String referenceNo = systemService.generateSequenceGenerator(MAINTENANCE_REQUEST_REFERENCE_NO);
            request.setReferenceNo(referenceNo);

            // save invoice
            maintenanceRequestDao.save(request, securityService.getCurrentUser());
            entityManager.flush();
            entityManager.refresh(request);
            // trigger process event
            workflowService.processWorkflow(request, toMap(request));
            // trigger event
            applicationContext.publishEvent(new MaintenanceRequestDraftedEvent(request));

            return request.getReferenceNo();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.debug("error occurred", e);
            throw new Exception(e);
        }
    }

    @Override
    public void cancelMaintenanceRequest(DexMaintenanceRequest request) throws Exception {
        LOG.debug(securityService.getCurrentUser().getName() + " is canceling order");

        if (!DexFlowState.COMPLETED.equals(request.getFlowdata().getState()))
            throw new Exception("Only completed request can be cancelled");
        request.getFlowdata().setState(DexFlowState.CANCELLED);

        request.getFlowdata().setCancelledDate(new Timestamp(System.currentTimeMillis()));
        request.getFlowdata().setCancelerId(securityService.getCurrentUser().getId());
        maintenanceRequestDao.update(request, securityService.getCurrentUser());
        entityManager.flush();
        entityManager.refresh(request);

        // event
        applicationContext.publishEvent(new MaintenanceRequestCancelledEvent(request));
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

    @Override
    public DexMaintenanceRequest findMaintenanceRequestById(Long id) {
        return maintenanceRequestDao.findById(id);
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
    public void saveMaintenanceRequest(DexMaintenanceRequest request, DexLocation location) {
        DexUser currentUser = securityService.getCurrentUser();
        DexActor requester = currentUser.getActor();
        request.setLocation(location);
        request.setRequester(requester);

        maintenanceRequestDao.save(request, currentUser);
        entityManager.flush();
    }

    @Override
    public void updateMaintenanceRequest(DexMaintenanceRequest request) {
        maintenanceRequestDao.update(request, securityService.getCurrentUser());
        entityManager.flush();


    }

    @Override
    public void removeMaintenanceRequest(DexMaintenanceRequest request) {
        maintenanceRequestDao.remove(request, securityService.getCurrentUser());
        entityManager.flush();
    }

    // =============================================================================================
    // PRIVATE METHODS
    // =============================================================================================

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
