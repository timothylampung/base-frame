package my.spotit.asset.maintenance.api.controller;

import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.common.business.service.CommonService;
import my.spotit.asset.core.api.ApplicationSuccess;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.maintenance.business.service.MaintenanceRequestService;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequestImpl;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.workflow.business.service.WorkflowService;

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

import my.spotit.asset.DexConstants;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequest;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequestRecordSummary;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequestRecordSummaryResult;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequestResult;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequestTask;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequestTaskSummaryResult;

@Transactional
@RestController
@RequestMapping("/api/maintenance-request/")
public class MaintenanceRequestController {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceRequestController.class);

    private MaintenanceRequestService maintenanceRequestService;
    private MaintenanceRequestTransformer maintenanceRequestTransformer;
    private SecurityService securityService;
    private CommonService commonService;
    private AssetService assetService;
    private IdentityService identityService;
    private WorkflowService workflowService;

    @Autowired
    public MaintenanceRequestController(MaintenanceRequestService maintenanceRequestService,
                                        MaintenanceRequestTransformer maintenanceRequestTransformer,
                                        SecurityService securityService, AssetService assetService,
                                        CommonService commonService, IdentityService identityService,
                                        WorkflowService workflowService) {
        this.maintenanceRequestService = maintenanceRequestService;
        this.maintenanceRequestTransformer = maintenanceRequestTransformer;
        this.securityService = securityService;
        this.assetService = assetService;
        this.commonService = commonService;
        this.identityService = identityService;
        this.workflowService = workflowService;
    }

    @GetMapping(value = "/maintenance-requests/count-assigned-task")
    public ResponseEntity<Integer> countAssignedMaintenanceRequestTask() {
        int count = maintenanceRequestService.countAssignedMaintenanceRequestTask("%");
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/maintenance-requests/count-pooled-task")
    public ResponseEntity<Integer> countPooledMaintenanceRequestTask() {
        int count = maintenanceRequestService.countPooledMaintenanceRequestTask("%");
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/maintenance-requests/assigned-tasks", params = {"filter"})
    public ResponseEntity<MaintenanceRequestTaskSummaryResult> findAssignedMaintenanceRequestTaskSummaries(@RequestParam(defaultValue = "%") String filter) {
        int count = maintenanceRequestService.countAssignedMaintenanceRequestTask(filter);
        List<Task> tasks = maintenanceRequestService.findAssignedMaintenanceRequestTasks(filter, 0, 999);
        return ResponseEntity.ok(
                new MaintenanceRequestTaskSummaryResult(
                        maintenanceRequestTransformer.toMaintenanceRequestTaskSummaryVos(tasks),
                        count
                ));
    }

    @GetMapping(value = "/maintenance-requests/pooled-tasks", params = {"filter"})
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
    @GetMapping(value = "/maintenance-requests/archived-records", params = {"filter", "page"})
    public ResponseEntity<MaintenanceRequestRecordSummaryResult> findPagedArchivedRequests(@RequestParam(defaultValue = "%") String filter,
                                                                                           @RequestParam Integer page) {
        Integer count = maintenanceRequestService.countMaintenanceRequest(filter);
        List<DexMaintenanceRequest> requests = maintenanceRequestService.findMaintenanceRequests(filter, (page - 1) * DexConstants.LIMIT, DexConstants.LIMIT);
        LOG.debug("findArchivedRequests: {}", requests.size());
        return ResponseEntity.ok(
                new MaintenanceRequestRecordSummaryResult(
                        maintenanceRequestTransformer.toMaintenanceRequestRecordSummaryVos(requests),
                        count
                ));
    }

    @GetMapping(value = "/maintenance-requests/archived-records")
    public ResponseEntity<List<MaintenanceRequestRecordSummary>> findArchivedRequests() {
        LOG.debug("findArchivedRequests");
        List<DexMaintenanceRequest> requests = maintenanceRequestService.findMaintenanceRequests("%", 0, 999);
        LOG.debug("findArchivedRequests: {}", requests.size());
        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestRecordSummaryVos(requests));
    }

    @PostMapping(value = "/maintenance-requests/start-task")
    public ResponseEntity<?> startMaintenanceRequestTask(@RequestBody MaintenanceRequest vo) throws Exception {
        DexAsset asset = assetService.findAssetByCode(vo.getAsset().getCode());
        DexActor requester = securityService.getCurrentUser().getActor();
        DexLocation location = assetService.findLocationByCode(vo.getLocation().getCode());

        DexMaintenanceRequest request = new DexMaintenanceRequestImpl();
        request.setLocation(location);
        request.setRequestedDate(new Date());
        request.setRequester(requester);
        request.setAsset(asset);
        request.setDescription(vo.getDescription());
        request.setRemark(vo.getRemark());
        maintenanceRequestService.startMaintenanceRequestTask(request);
        LOG.debug("end task");
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceRequestTransformer.toMaintenanceRequestVo(request));
    }

    @GetMapping(value = "/maintenance-requests/view-task/{taskId}")
    public ResponseEntity<MaintenanceRequestTask> viewMaintenanceRequestTaskById(@PathVariable String taskId) {
        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestTaskVo(maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId)));
    }

    @PostMapping(value = "/maintenance-requests/claim-task/")
    public void claimMaintenanceRequestTask(@RequestBody List<String> taskIds) {
        taskIds.forEach(taskId -> {
            Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
            workflowService.claimTask(task);
        });
    }

    @PostMapping(value = "/maintenance-requests/complete-task/{taskId}")
    public void completeMaintenanceRequestTask(@PathVariable String taskId) {
        Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
        workflowService.completeTask(task);
    }

    @PostMapping(value = "/maintenance-requests/release-task/{taskId}")
    public ResponseEntity<ApplicationSuccess> releaseMaintenanceRequestTask(@PathVariable String taskId) {
        Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
        workflowService.releaseTask(task);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/maintenance-requests/remove-task/{taskId}")
    public ResponseEntity<ApplicationSuccess> removeMaintenanceRequestTask(@PathVariable String taskId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("removeDecision", true);

        Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
        workflowService.completeTask(task, params);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/maintenance-requests", params = {"page"})
    public ResponseEntity<MaintenanceRequestResult> findPagedRequests(@RequestParam Integer page) {
        LOG.debug("findPagedRequests");
        Integer count = maintenanceRequestService.countMaintenanceRequest("%");
        List<MaintenanceRequest> requests = maintenanceRequestTransformer.toMaintenanceRequestVos(
                maintenanceRequestService.findMaintenanceRequests("%", ((page - 1) * DexConstants.LIMIT), DexConstants.LIMIT));
        return new ResponseEntity<MaintenanceRequestResult>(new MaintenanceRequestResult(requests, count), HttpStatus.OK);
    }

    @GetMapping(value = "/maintenance-requests/{referenceNo}")
    public ResponseEntity<MaintenanceRequest> findMaintenanceRequestByReferenceNo(@PathVariable String referenceNo) {
        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestVo(maintenanceRequest));
    }

    @PutMapping(value = "/maintenance-requests/{referenceNo}")
    public ResponseEntity<ApplicationSuccess> updateMaintenanceRequest(@PathVariable String referenceNo, @RequestBody MaintenanceRequest vo) {
        DexMaintenanceRequest request = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
        request.setSourceNo(vo.getSourceNo());
        request.setDescription(vo.getDescription());
        request.setDelegated(vo.getDelegated());
        request.setRemark(vo.getRemark());
        request.setRequestedDate(vo.getRequestedDate());
        request.setLocation(assetService.findLocationById(vo.getLocation().getId()));
        request.setRequester(identityService.findActorById(vo.getRequester().getId()));
        request.setDelegator(identityService.findActorById(vo.getDelegator().getId()));
        request.setVerifier(identityService.findActorById(vo.getVerifier().getId()));
        maintenanceRequestService.updateMaintenanceRequest(request);
        return ResponseEntity.ok().build();
    }

    // todo: save this if we have items
    //
//    @GetMapping(value = "/maintenance-requests/{referenceNo}/maintenanceRequest-items")
//    public ResponseEntity<List<MaintenanceRequestItem>> findMaintenanceRequestItems(@PathVariable String referenceNo) {
//        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
//        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestItemVos(
//                maintenanceRequestService.findMaintenanceRequestItems(maintenanceRequest)));
//    }
//
//    @PostMapping(value = "/maintenance-requests/{referenceNo}/maintenanceRequest-items")
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
//    @PutMapping(value = "/maintenance-requests/{referenceNo}/maintenanceRequest-items")
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
//    @DeleteMapping(value = "/maintenance-requests/{referenceNo}/maintenanceRequest-items/{id}")
//    public ResponseEntity<ApplicationSuccess> deleteMaintenanceRequestItem(@PathVariable Long id) {
//        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestById(id);
//        DexMaintenanceRequestItem item = maintenanceRequestService.findMaintenanceRequestItemById(id);
//        maintenanceRequestService.deleteMaintenanceRequestItem(maintenanceRequest, item);
//        return ResponseEntity.ok().build();
//    }
//

}
