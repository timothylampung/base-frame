package my.spotit.asset.maintenance.business.service;

import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;

import org.flowable.task.api.Task;

import java.util.List;

public interface MaintenanceRequestService {

    //==============================================================================================
    // MAINTENANCE REQUEST
    //==============================================================================================

    // workflow
    String startMaintenanceRequestTask(DexMaintenanceRequest request) throws Exception;

    void cancelMaintenanceRequest(DexMaintenanceRequest request) throws Exception;

    DexMaintenanceRequest findMaintenanceRequestByTaskId(String taskId);

    DexMaintenanceRequest findMaintenanceRequestByRecordId(Long recordId);

    Task findMaintenanceRequestTaskByTaskId(String taskId);

    List<Task> findAssignedMaintenanceRequestTasks(String filter, Integer offset, Integer limit);

    List<Task> findPooledMaintenanceRequestTasks(String filter, Integer offset, Integer limit);

    Integer countAssignedMaintenanceRequestTask(String filter);

    Integer countAssignedMaintenanceRequestTask(DexFlowState flowState);

    Integer countPooledMaintenanceRequestTask(String filter);

    Integer countPooledMaintenanceRequestTask(DexFlowState flowState);

    // finder

    DexMaintenanceRequest findMaintenanceRequestById(Long id);

    DexMaintenanceRequest findMaintenanceRequestByReferenceNo(String referenceNo);

    List<DexMaintenanceRequest> findMaintenanceRequests(String filter, Integer offset, Integer limit);

    Integer countMaintenanceRequest();

    Integer countMaintenanceRequest(String filter);

    void saveMaintenanceRequest(DexMaintenanceRequest request, DexLocation location);

    void updateMaintenanceRequest(DexMaintenanceRequest request);

    void removeMaintenanceRequest(DexMaintenanceRequest request);


}
