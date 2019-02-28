package my.spotit.asset.workorder.business.service;

import my.spotit.asset.DexConstants;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.business.service.SystemService;
import my.spotit.asset.workflow.business.service.WorkflowConstants;
import my.spotit.asset.workflow.business.service.WorkflowService;
import my.spotit.asset.workorder.business.event.WorkOrderCancelledEvent;
import my.spotit.asset.workorder.business.event.WorkOrderDraftedEvent;
import my.spotit.asset.workorder.domain.dao.DexWorkOrderDao;
import my.spotit.asset.workorder.domain.model.DexWorkOrderActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderComment;
import my.spotit.asset.workorder.domain.model.DexWorkOrderImpl;
import my.spotit.asset.workorder.domain.model.DexWorkOrderLog;
import my.spotit.asset.workorder.domain.model.DexWorkOrderLogImpl;

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

    private static final Logger LOG = LoggerFactory.getLogger(WorkOrderServiceImpl.class);

    private EntityManager entityManager;
    private SecurityService securityService;
    private SystemService systemService;
    private WorkflowService workflowService;
    private ApplicationContext applicationContext;
    private DexWorkOrderDao workOrderDao;

    public WorkOrderServiceImpl(EntityManager entityManager, SecurityService securityService,
                                WorkflowService workflowService,
                                ApplicationContext applicationContext,
                                SystemService systemService,
                                DexWorkOrderDao workOrderDao) {
        this.entityManager = entityManager;
        this.securityService = securityService;
        this.systemService = systemService;
        this.workflowService = workflowService;
        this.applicationContext = applicationContext;
        this.workOrderDao = workOrderDao;
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
    public DexWorkOrderActivity findActivityById(Long id) {
        return workOrderDao.findActivityById(id);
    }

    @Override
    public List<DexWorkOrder> findWorkOrders(String filter, Integer offset, Integer limit) {
        return workOrderDao.find(filter, offset, limit);
    }

    @Override
    public List<DexWorkOrderActivity> findWorkOrderActivities(String filter, DexWorkOrder workOrder, Integer offset, Integer limit) {
        return workOrderDao.findActivities(filter, workOrder, offset, limit);
    }

    @Override
    public List<DexWorkOrderLog> findWorkOrderLogs(String filter, DexWorkOrder workOrder, Integer offset, Integer limit) {
        return workOrderDao.findLogs(filter, workOrder, offset, limit);
    }

    @Override
    public List<DexWorkOrderComment> findWorkOrderComments(String filter, DexWorkOrder workOrder, Integer offset, Integer limit) {
        return workOrderDao.findComments(filter, workOrder, offset, limit);
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
    public Integer countActivity(DexWorkOrder workOrder) {
        return workOrderDao.countActivity(workOrder);
    }

    @Override
    public Integer countWorkOrderLog(DexWorkOrder workOrder) {
        return workOrderDao.countLog(workOrder);
    }

    @Override
    public Integer countWorkOrderComment(DexWorkOrder workOrder) {
        return workOrderDao.countComment(workOrder);
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
    public void addWorkOrderActivity(DexWorkOrder workOrder, DexWorkOrderActivity activity) {
        workOrderDao.addActivity(workOrder, activity, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateWorkOrderActivity(DexWorkOrder workOrder, DexWorkOrderActivity activity) {
        workOrderDao.updateActivity(workOrder, activity, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void deleteWorkOrderActivity(DexWorkOrder workOrder, DexWorkOrderActivity activity) {
        workOrderDao.deleteActivity(workOrder, activity, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void addWorkOrderLog(DexWorkOrder workOrder, DexWorkOrderLog log) {
        workOrderDao.addLog(workOrder, log, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateWorkOrderLog(DexWorkOrder workOrder, DexWorkOrderLog log) {
        workOrderDao.updateLog(workOrder, log, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void deleteWorkOrderLog(DexWorkOrder workOrder, DexWorkOrderLog log) {
        workOrderDao.deleteLog(workOrder, log, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void addWorkOrderComment(DexWorkOrder workOrder, DexWorkOrderComment comment) {
        workOrderDao.addComment(workOrder, comment, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateWorkOrderComment(DexWorkOrder workOrder, DexWorkOrderComment comment) {
        workOrderDao.updateComment(workOrder, comment, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void deleteWorkOrderComment(DexWorkOrder workOrder, DexWorkOrderComment comment) {
        workOrderDao.deleteComment(workOrder, comment, securityService.getCurrentUser());
        entityManager.flush();
    }

    // business

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

    @Override
    public void startLog(DexWorkOrder order) {
        if (workOrderDao.hasUnendedLog(order)) {
            DexWorkOrderLog unendedLog = workOrderDao.findUnendedLog(order);
            unendedLog.setStopTime(new Timestamp(System.currentTimeMillis()));
            workOrderDao.updateLog(order, unendedLog, securityService.getCurrentUser());
            entityManager.flush();
        }

        DexWorkOrderLog orderLog = new DexWorkOrderLogImpl();
        orderLog.setStartTime(new Timestamp(System.currentTimeMillis()));
        orderLog.setLogger(securityService.getCurrentUser());
        workOrderDao.addLog(order, orderLog, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void stopLog(DexWorkOrder order) {
        if (workOrderDao.hasUnendedLog(order)) {
            DexWorkOrderLog unendedLog = workOrderDao.findUnendedLog(order);
            unendedLog.setStopTime(new Timestamp(System.currentTimeMillis()));
            workOrderDao.updateLog(order, unendedLog, securityService.getCurrentUser());
            entityManager.flush();
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
