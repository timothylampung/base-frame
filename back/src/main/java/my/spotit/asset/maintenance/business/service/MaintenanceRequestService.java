package my.spotit.asset.maintenance.business.service;

import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import org.flowable.task.api.Task;

import java.util.List;

public interface MaintenanceRequestService {

    //==============================================================================================
    // WORK ORDER
    //==============================================================================================

    DexMaintenanceRequest findMaintenanceRequestById (Long id);

    DexMaintenanceRequest findMaintenanceRequestByCode (String code);

    List<DexMaintenanceRequest> findMaintenanceRequests(String filter, Integer offset, Integer limit);

    Integer countMaintenanceRequest();

    Integer countMaintenanceRequest(String filter);

    void saveMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest, DexLocation location);

    void updateMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest);

    void removeMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest);


    //maintenanceRequest workflow
    String startMaintenanceRequestTask(DexMaintenanceRequest maintenanceRequest) throws Exception;

    void cancelMaintenanceRequest(DexMaintenanceRequest maintenanceRequest) throws Exception;

    DexMaintenanceRequest findMaintenanceRequestByTaskId(String taskId);

    DexMaintenanceRequest findMaintenanceRequestByRecordId(Long recordId);

    Task findMaintenanceRequestTaskByTaskId(String taskId);

    List<Task> findAssignedMaintenanceRequestTasks(String filter, Integer offset, Integer limit);

    List<Task> findPooledMaintenanceRequestTasks(String filter, Integer offset, Integer limit);

    Integer countAssignedMaintenanceRequestTask(String filter);

    Integer countAssignedMaintenanceRequestTask(DexFlowState flowState);

    Integer countPooledMaintenanceRequestTask(String filter);

    Integer countPooledMaintenanceRequestTask(DexFlowState flowState);

    DexMaintenanceRequest findMaintenanceRequestByReferenceNo(String referenceNo);


//=====================
}