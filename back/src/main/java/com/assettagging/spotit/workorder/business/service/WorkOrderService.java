package com.assettagging.spotit.workorder.business.service;

import com.assettagging.spotit.core.domain.DexFlowState;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import org.flowable.task.api.Task;

import java.util.List;

public interface WorkOrderService {

    //==============================================================================================
    // WORK ORDER
    //==============================================================================================

    //workOrder workflow
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

    DexWorkOrder findWorkOrderById (Long id);

    DexWorkOrder findWorkOrderByCode (String code);

    List<DexWorkOrder> findWorkOrders(String filter, Integer offset, Integer limit);

    List<DexActivity> findActivities(String filter, DexWorkOrder workOrder, Integer offset, Integer limit);

    Integer countWorkOrder();

    Integer countWorkOrder(String filter);

    void saveWorkOrder(DexWorkOrder WorkOrder);

    void updateWorkOrder(DexWorkOrder WorkOrder);

    void removeWorkOrder(DexWorkOrder WorkOrder);

    void serializeToWorkOrder(DexMaintenanceRequest request);

    DexActivity findActivityById (Long id);

    DexActivity findActivityByCode (String code);

    Integer countActivity();

    Integer countActivity(String filter);

    void saveActivity(DexActivity Activity);

    void updateActivity(DexActivity Activity);

    void removeActivity(DexActivity Activity);

}
