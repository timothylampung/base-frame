package com.assettagging.spotit.maintenance.api.controller;

import com.assettagging.spotit.asset.business.service.AssetService;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.api.ApplicationSuccess;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.maintenance.api.vo.*;
import com.assettagging.spotit.maintenance.business.service.MaintenanceRequestService;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequestImpl;
import com.assettagging.spotit.security.business.service.SecurityService;
import com.assettagging.spotit.workflow.business.service.WorkflowService;
import com.google.common.collect.Maps;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.assettagging.spotit.DexConstants.LIMIT;

@Transactional
@RestController
@RequestMapping("/api/maintenance/")
public class MaintenanceRequestController {
    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceRequestController.class);
    private MaintenanceRequestService maintenanceRequestService;
    private MaintenanceRequestTransformer maintenanceRequestTransformer;
    private SecurityService securityService;
    private AssetService assetService;
    private IdentityService identityService;
    private WorkflowService workflowService;

    @Autowired
    public MaintenanceRequestController(MaintenanceRequestService maintenanceRequestService, MaintenanceRequestTransformer maintenanceRequestTransformer, SecurityService securityService, AssetService assetService, IdentityService identityService, WorkflowService workflowService) {
        this.maintenanceRequestService = maintenanceRequestService;
        this.maintenanceRequestTransformer = maintenanceRequestTransformer;
        this.securityService = securityService;
        this.assetService = assetService;
        this.identityService = identityService;
        this.workflowService = workflowService;
    }



    @GetMapping(value = "/requests", params = {"page"})
    public ResponseEntity<MaintenanceRequestResult> findPagedRequests(@RequestParam Integer page) {
        LOG.debug("findPagedRequests");
        Integer count = maintenanceRequestService.countMaintenanceRequest("%");
        List<MaintenanceRequest> requests = maintenanceRequestTransformer.toMaintenanceRequestVos(
                maintenanceRequestService.findMaintenanceRequests("%", ((page - 1) * LIMIT), LIMIT));
        return new ResponseEntity<MaintenanceRequestResult>(new MaintenanceRequestResult(requests, count), HttpStatus.OK);
    }

    @GetMapping(value = "/requests/{referenceNo}")
    public ResponseEntity<MaintenanceRequest> findMaintenanceRequestByReferenceNo(@PathVariable String referenceNo) {
        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestVo(maintenanceRequest));
    }

    @GetMapping(value = "/requests/count-assigned-task")
    public ResponseEntity<Integer> countAssignedMaintenanceRequestTask() {
        int count = maintenanceRequestService.countAssignedMaintenanceRequestTask("%");
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/requests/count-pooled-task")
    public ResponseEntity<Integer> countPooledMaintenanceRequestTask() {
        int count = maintenanceRequestService.countPooledMaintenanceRequestTask("%");
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/requests/assigned-tasks", params = {"filter"})
    public ResponseEntity<MaintenanceRequestTaskSummaryResult> findAssignedMaintenanceRequestTaskSummaries(@RequestParam(defaultValue = "%") String filter) {
        int count = maintenanceRequestService.countAssignedMaintenanceRequestTask(filter);
        List<Task> tasks = maintenanceRequestService.findAssignedMaintenanceRequestTasks(filter, 0, 999);
        return ResponseEntity.ok(
                new MaintenanceRequestTaskSummaryResult(
                        maintenanceRequestTransformer.toMaintenanceRequestTaskSummaryVos(tasks),
                        count
                ));
    }

    @GetMapping(value = "/requests/pooled-tasks", params = {"filter"})
    public ResponseEntity<MaintenanceRequestTaskSummaryResult> findPooledMaintenanceRequestTaskSummaries(@RequestParam(defaultValue = "%") String filter) {
        int count = maintenanceRequestService.countPooledMaintenanceRequestTask(filter);
        List<Task> tasks = maintenanceRequestService.findPooledMaintenanceRequestTasks(filter, 0, 999);
        return ResponseEntity.ok(
                new MaintenanceRequestTaskSummaryResult(
                        maintenanceRequestTransformer.toMaintenanceRequestTaskSummaryVos(tasks),
                        count
                ));

    }

    // todo: via ACL
    @GetMapping(value = "/requests/archived-records", params = {"filter", "page"})
    public ResponseEntity<MaintenanceRequestRecordSummaryResult> findPagedArchivedRequests(@RequestParam(defaultValue = "%") String filter,
                                                                                           @RequestParam Integer page) {
        Integer count = maintenanceRequestService.countMaintenanceRequest(filter);
        List<DexMaintenanceRequest> requests = maintenanceRequestService.findMaintenanceRequests(filter, (page - 1) * LIMIT, LIMIT);
        LOG.debug("findArchivedRequests: {}", requests.size());
        return ResponseEntity.ok(
                new MaintenanceRequestRecordSummaryResult(
                        maintenanceRequestTransformer.toMaintenanceRequestRecordSummaryVos(requests),
                        count
                ));
    }

    @GetMapping(value = "/requests/archived-records")
    public ResponseEntity<List<MaintenanceRequestRecordSummary>> findArchivedRequests() {
        LOG.debug("findArchivedRequests");
        List<DexMaintenanceRequest> requests = maintenanceRequestService.findMaintenanceRequests("%", 0, 999);
        LOG.debug("findArchivedRequests: {}", requests.size());
        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestRecordSummaryVos(requests));
    }

    @PostMapping(value = "/requests/start-task")
    public ResponseEntity<?> startMaintenanceRequestTask(@RequestBody MaintenanceRequest vo) throws Exception {
        DexMaintenanceRequest maintenanceRequest = new DexMaintenanceRequestImpl();

        DexAsset asset = assetService.findAssetByCode(vo.getAsset().getCode());
        DexActor requester = securityService.getCurrentUser().getActor();
        DexLocation location = assetService.findLocationByCode(vo.getLocation().getCode());

        maintenanceRequest.setLocation(location);
        maintenanceRequest.setRequestedDate(new Date());
        maintenanceRequest.setRequester(requester);
        maintenanceRequest.setAsset(asset);
        maintenanceRequest.setDescription(vo.getDescription());
        maintenanceRequest.setRemark(vo.getRemark());

        maintenanceRequestService.startMaintenanceRequestTask(maintenanceRequest);
        LOG.debug("end task");
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceRequestTransformer.toMaintenanceRequestVo(maintenanceRequest));
    }

    @GetMapping(value = "/requests/view-task/{taskId}")
    public ResponseEntity<MaintenanceRequestTask> viewMaintenanceRequestTaskById(@PathVariable String taskId) {
        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestTaskVo(maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId)));
    }

    @PostMapping(value = "/requests/claim-task/")
    public void claimMaintenanceRequestTask(@RequestBody List<String> taskIds) {
        taskIds.forEach(taskId -> {
            Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
            workflowService.claimTask(task);
        });
    }

    @PostMapping(value = "/requests/complete-task/{taskId}")
    public void completeMaintenanceRequestTask(@PathVariable String taskId) {
        Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
        workflowService.completeTask(task);
    }

    @PostMapping(value = "/requests/release-task/{taskId}")
    public ResponseEntity<ApplicationSuccess> releaseMaintenanceRequestTask(@PathVariable String taskId) {
        Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
        workflowService.releaseTask(task);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/requests/remove-task/{taskId}")
    public ResponseEntity<ApplicationSuccess> removeMaintenanceRequestTask(@PathVariable String taskId) {

        Map<String, Object> params = Maps.newHashMap();
        params.put("removeDecision", true);

        Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
        workflowService.completeTask(task, params);
        return ResponseEntity.ok().build();
    }

//    @PutMapping(value = "/requests/{referenceNo}")
//    public ResponseEntity<ApplicationSuccess> updateMaintenanceRequest(@PathVariable String referenceNo, @RequestBody MaintenanceRequest vo) {
//        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
//        maintenanceRequest.setSourceNo(vo.getSourceNo());
//        maintenanceRequest.setDescription(vo.getDescription());
//        maintenanceRequest.setRequester(commonService.findCostCenterById(vo.getRequester().getId()));
//        maintenanceRequest.setPurchaser(commonService.findCostCenterById(vo.getPurchaser().getId()));
//        maintenanceRequest.setMaintenanceRequestType(DexMaintenanceRequestType.get(vo.getMaintenanceRequestType().ordinal()));
//        maintenanceRequest.setTotalAmount(vo.getTotalAmount());
//        maintenanceRequestService.updateMaintenanceRequest(maintenanceRequest);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping(value = "/requests/{referenceNo}/maintenanceRequest-items")
//    public ResponseEntity<List<MaintenanceRequestItem>> findMaintenanceRequestItems(@PathVariable String referenceNo) {
//        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
//        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestItemVos(
//                maintenanceRequestService.findMaintenanceRequestItems(maintenanceRequest)));
//    }
//
//    @PostMapping(value = "/requests/{referenceNo}/maintenanceRequest-items")
//    public ResponseEntity<MaintenanceRequestItem> addMaintenanceRequestItem(@PathVariable String referenceNo, @RequestBody MaintenanceRequestItem vo) {
//        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
//        DexMaintenanceRequestItem item = new DexMaintenanceRequestItemImpl();
//        item.setDescription(vo.getDescription());
//        item.setMaintenanceRequest(maintenanceRequest);
//        item.setEstimatedPrice(vo.getEstimatedPrice());
//        item.setEstimatedUnit(vo.getEstimatedUnit());
//        item.setEstimatedTotalAmount(vo.getEstimatedTotalAmount());
//        maintenanceRequestService.addMaintenanceRequestItem(maintenanceRequest, item);
//        return ResponseEntity.ok().body(maintenanceRequestTransformer.toMaintenanceRequestItemVo(item));
//    }
//
//    @PutMapping(value = "/requests/{referenceNo}/maintenanceRequest-items")
//    public ResponseEntity<MaintenanceRequestItem> updateMaintenanceRequestItem(@PathVariable String referenceNo, @RequestBody MaintenanceRequestItem vo) {
//        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
//        DexMaintenanceRequestItem item = maintenanceRequestService.findMaintenanceRequestItemById(vo.getId());
//        item.setDescription(vo.getDescription());
//        item.setMaintenanceRequest(maintenanceRequest);
//        item.setEstimatedPrice(vo.getEstimatedPrice());
//        item.setEstimatedUnit(vo.getEstimatedUnit());
//        item.setEstimatedTotalAmount(vo.getEstimatedTotalAmount());
//        maintenanceRequestService.updateMaintenanceRequestItem(maintenanceRequest, item);
//        return ResponseEntity.ok().body(maintenanceRequestTransformer.toMaintenanceRequestItemVo(item));
//    }
//
//    @DeleteMapping(value = "/requests/{referenceNo}/maintenanceRequest-items/{id}")
//    public ResponseEntity<ApplicationSuccess> deleteMaintenanceRequestItem(@PathVariable Long id) {
//        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestById(id);
//        DexMaintenanceRequestItem item = maintenanceRequestService.findMaintenanceRequestItemById(id);
//        maintenanceRequestService.deleteMaintenanceRequestItem(maintenanceRequest, item);
//        return ResponseEntity.ok().build();
//    }
//


}
