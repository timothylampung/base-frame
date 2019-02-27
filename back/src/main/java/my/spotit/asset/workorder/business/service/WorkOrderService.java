package my.spotit.asset.workorder.business.service;

import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderLog;

import org.flowable.task.api.Task;

import java.util.List;

public interface WorkOrderService {

    //==============================================================================================
    // WORK ORDER
    //==============================================================================================

    // workflow
    String startWorkOrderTask(DexWorkOrder workOrder) throws Exception;

    void cancelWorkOrder(DexWorkOrder workOrder) throws Exception;

    DexWorkOrder findWorkOrderByTaskId(String taskId);

    DexWorkOrder findWorkOrderByRecordId(Long recordId);

    Task findWorkOrderTaskByTaskId(String taskId);

    List<Task> findAssignedWorkOrderTasks(String filter, Integer offset, Integer limit);

    List<Task> findPooledWorkOrderTasks(String filter, Integer offset, Integer limit);

    Integer countAssignedWorkOrderTask(String filter);

    Integer countAssignedWorkOrderTask(DexFlowState flowState);

    Integer countPooledWorkOrderTask(String filter);

    Integer countPooledWorkOrderTask(DexFlowState flowState);


    // finders

    DexWorkOrder findWorkOrderById(Long id);

    DexWorkOrder findWorkOrderByReferenceNo(String referenceNo);

    DexActivity findActivityById(Long id);

    List<DexWorkOrder> findWorkOrders(String filter, Integer offset, Integer limit);

    List<DexActivity> findActivities(String filter, DexWorkOrder workOrder, Integer offset, Integer limit);

    List<DexWorkOrderLog> findWorkOrderLogs(String filter, DexWorkOrder workOrder, Integer offset, Integer limit);

    Integer countWorkOrder();

    Integer countWorkOrder(String filter);

    Integer counActivity(DexWorkOrder workOrder);

    Integer counWorkOrderLog(DexWorkOrder workOrder);

    void saveWorkOrder(DexWorkOrder WorkOrder);

    void updateWorkOrder(DexWorkOrder WorkOrder);

    void removeWorkOrder(DexWorkOrder WorkOrder);

    void addActivity(DexWorkOrder workOrder, DexActivity activity);

    void updateActivity(DexWorkOrder workOrder, DexActivity activity);

    void deleteActivity(DexWorkOrder workOrder, DexActivity activity);

    void addWorkOrderLog(DexWorkOrder workOrder, DexWorkOrderLog log);

    void updateWorkOrderLog(DexWorkOrder workOrder, DexWorkOrderLog log);

    void deleteWorkOrderLog(DexWorkOrder workOrder, DexWorkOrderLog log);


    // business

    void serializeToWorkOrder(DexMaintenanceRequest request);

    void startLog(DexWorkOrder order);

    void stopLog(DexWorkOrder order);
}
