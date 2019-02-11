package my.spotit.asset.workorder.business.service;

import my.spotit.asset.DexConstants;
import my.spotit.asset.common.business.service.CommonServiceImpl;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.business.service.SystemService;
import my.spotit.asset.workflow.business.service.WorkflowConstants;
import my.spotit.asset.workflow.business.service.WorkflowService;
import my.spotit.asset.workorder.business.event.WorkOrderCancelledEvent;
import my.spotit.asset.workorder.business.event.WorkOrderDraftedEvent;
import my.spotit.asset.workorder.domain.dao.DexActivityDao;
import my.spotit.asset.workorder.domain.dao.DexWorkOrderDao;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderImpl;

import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import static my.spotit.asset.DexConstants.WORK_ORDER_REFERENCE_NO;
import static my.spotit.asset.workflow.business.service.WorkflowConstants.DELIMITER;


@Transactional
@Service("workOrderService")
public class WorkOrderServiceImpl implements WorkOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

    private EntityManager entityManager;
    private SecurityService securityService;
    private SystemService systemService;
    private WorkflowService workflowService;
    private ApplicationContext applicationContext;
    private DexWorkOrderDao workOrderDao;
    private DexActivityDao activityDao;

    public WorkOrderServiceImpl(EntityManager entityManager, SecurityService securityService,
                                WorkflowService workflowService,
                                ApplicationContext applicationContext,
                                SystemService systemService,
                                DexWorkOrderDao workOrderDao,
                                DexActivityDao activityDao) {
        this.entityManager = entityManager;
        this.securityService = securityService;
        this.systemService = systemService;
        this.workflowService = workflowService;
        this.applicationContext = applicationContext;
        this.workOrderDao = workOrderDao;
        this.activityDao = activityDao;
    }

    //====================================================================================================
    // WORK ORDER
    //====================================================================================================

    @Override
    public String startWorkOrderTask(DexWorkOrder workOrder) throws Exception {
        LOG.debug(securityService.getCurrentUser().getName() + " is processing order");

        try {
            // generate reference no
            HashMap<String, Object> param = new HashMap<String, Object>();
            // param.put("period", commonService.findCurrentPeriod());
            String referenceNo = systemService.generateFormattedSequenceGenerator(WORK_ORDER_REFERENCE_NO, param);
            workOrder.setReferenceNo(referenceNo);

            // save invoice
            workOrderDao.save(workOrder, securityService.getCurrentUser());
            entityManager.flush();
            entityManager.refresh(workOrder);

            // trigger process event
            workflowService.processWorkflow(workOrder, toMap(workOrder));

            // trigger event
            applicationContext.publishEvent(new WorkOrderDraftedEvent(workOrder));

            return workOrder.getReferenceNo();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.debug("error occurred", e);
            throw new Exception(e);
        }

    }

    @Override
    public void cancelWorkOrder(DexWorkOrder workOrder) throws Exception {
        LOG.debug(securityService.getCurrentUser().getName() + " is canceling order");

        if (!DexFlowState.COMPLETED.equals(workOrder.getFlowdata().getState()))
            throw new Exception("Only completed WorkOrder can be cancelled");

        workOrder.getFlowdata().setState(DexFlowState.CANCELLED);
        workOrder.getFlowdata().setCancelledDate(new Timestamp(System.currentTimeMillis()));
        workOrder.getFlowdata().setCancelerId(securityService.getCurrentUser().getId());
        workOrderDao.update(workOrder, securityService.getCurrentUser());
        entityManager.flush();
        entityManager.refresh(workOrder);

        // event
        applicationContext.publishEvent(new WorkOrderCancelledEvent(workOrder));
    }


    @Override
    public DexWorkOrder findWorkOrderByTaskId(String taskId) {
        Task task = workflowService.findTask(taskId);
        Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
        return workOrderDao.findById((Long) map.get(DexConstants.ORDER_ID));
    }

    @Override
    public DexWorkOrder findWorkOrderByRecordId(Long recordId) {
        return workOrderDao.findById(recordId);
    }

    @Override
    public Task findWorkOrderTaskByTaskId(String taskId) {
        return workflowService.findTask(taskId);
    }

    @Override
    public List<Task> findAssignedWorkOrderTasks(String filter, Integer offset, Integer limit) {
        String taskName = DexWorkOrder.class.getCanonicalName() + DELIMITER;
        return workflowService.findAssignedTasks(filter, taskName, offset, limit);
    }

    @Override
    public List<Task> findPooledWorkOrderTasks(String filter, Integer offset, Integer limit) {
        String taskName = DexWorkOrder.class.getCanonicalName() + DELIMITER;
        return workflowService.findPooledTasks(filter, taskName, offset, limit);
    }

    @Override
    public Integer countAssignedWorkOrderTask(String filter) {
        String taskName = DexWorkOrder.class.getCanonicalName() + DELIMITER;
        return workflowService.countAssignedTask(filter, taskName);
    }

    @Override
    public Integer countAssignedWorkOrderTask(DexFlowState flowState) {
        String taskName = DexWorkOrder.class.getCanonicalName() + DELIMITER + flowState.name();
        return workflowService.countAssignedTask(taskName);
    }

    @Override
    public Integer countPooledWorkOrderTask(String filter) {
        String taskName = DexWorkOrder.class.getCanonicalName() + DELIMITER;
        return workflowService.countPooledTask(taskName);
    }

    @Override
    public Integer countPooledWorkOrderTask(DexFlowState flowState) {
        String taskName = DexWorkOrder.class.getCanonicalName() + DELIMITER;
        return workflowService.countPooledTask(taskName);
    }

    @Override
    public DexWorkOrder findWorkOrderById(Long id) {
        return workOrderDao.findById(id);
    }

    @Override
    public DexWorkOrder findWorkOrderByReferenceNo(String referenceNo) {
        return workOrderDao.findByReferenceNo(referenceNo);
    }

    @Override
    public DexActivity findActivityById(Long id) {
        return workOrderDao.findActivityById(id);
    }

    @Override
    public List<DexWorkOrder> findWorkOrders(String filter, Integer offset, Integer limit) {
        return workOrderDao.find(filter, offset, limit);
    }

    @Override
    public List<DexActivity> findActivities(String filter, DexWorkOrder workOrder, Integer offset, Integer limit) {
        return activityDao.find(filter, workOrder, offset, limit);
    }

    @Override
    public Integer countWorkOrder() {
        return workOrderDao.count();
    }

    @Override
    public Integer countWorkOrder(String filter) {
        return workOrderDao.count(filter);

    }

    @Override
    public Integer counActivity(DexWorkOrder workOrder) {
        return null;
    }

    @Override
    public void saveWorkOrder(DexWorkOrder WorkOrder) {
        workOrderDao.save(WorkOrder, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateWorkOrder(DexWorkOrder WorkOrder) {
        workOrderDao.update(WorkOrder, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void removeWorkOrder(DexWorkOrder WorkOrder) {
        workOrderDao.remove(WorkOrder, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void addActivity(DexWorkOrder workOrder, DexActivity activity) {
        workOrderDao.addActivity(workOrder, activity, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateActivity(DexWorkOrder workOrder, DexActivity activity) {
        workOrderDao.updateActivity(workOrder, activity, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void deleteActivity(DexWorkOrder workOrder, DexActivity activity) {
        workOrderDao.deleteActivity(workOrder, activity, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void serializeToWorkOrder(DexMaintenanceRequest request) {
        DexWorkOrder workOrder = new DexWorkOrderImpl();
        workOrder.setAssignee(request.getDelegator());
        workOrder.setAsset(request.getAsset());
        workOrder.setDescription(request.getDescription());
        workOrder.setLocation(request.getLocation());
        workOrder.setMaintenanceRequest(request);
        workOrder.setSupervisor(request.getVerifier());
        try {
            startWorkOrderTask(workOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =============================================================================================
    // PRIVATE METHODS
    // =============================================================================================

    private Map<String, Object> toMap(DexWorkOrder workOrder) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
        map.put(WorkflowConstants.REFERENCE_NO, workOrder.getReferenceNo());
        map.put(DexConstants.ORDER_ID, workOrder.getId());

        // by default set to false
        map.put(WorkflowConstants.QUERY_DECISION, false);
        map.put(WorkflowConstants.REMOVE_DECISION, false);
        map.put(WorkflowConstants.CANCEL_DECISION, false);
        return map;
    }
}
