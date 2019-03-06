package my.spotit.asset.integration.mobile.maintenenance.api;


import com.google.common.collect.Maps;
import my.spotit.asset.DexConstants;
import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.common.domain.model.DexFile;
import my.spotit.asset.core.api.ApplicationSuccess;
import my.spotit.asset.common.business.service.FileService;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.integration.mobile.security.MobileSecurityService;
import my.spotit.asset.maintenance.api.controller.MaintenanceRequestTransformer;
import my.spotit.asset.maintenance.api.vo.*;
import my.spotit.asset.maintenance.business.service.MaintenanceRequestService;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequestImpl;
import my.spotit.asset.workflow.business.service.WorkflowService;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@RestController
    @RequestMapping("/api/mobile/maintenance-request")
public class IntegrationMaintenanceController {
    private static final Logger LOG = LoggerFactory.getLogger(IntegrationMaintenanceController.class);
    private MaintenanceRequestService maintenanceRequestService;
    private AssetService assetService;
    private MaintenanceRequestTransformer maintenanceRequestTransformer;
    private WorkflowService workflowService;
    private IdentityService identityService;
    private MobileSecurityService mobileSecurityService;
    private FileService fileService;


    @Autowired
    public IntegrationMaintenanceController(MaintenanceRequestService maintenanceRequestService, MobileSecurityService mobileSecurityService,
                                            AssetService assetService, MaintenanceRequestTransformer maintenanceRequestTransformer, WorkflowService workflowService, IdentityService identityService, FileService fileService) {
        this.maintenanceRequestService = maintenanceRequestService;
        this.mobileSecurityService = mobileSecurityService;
        this.assetService = assetService;
        this.maintenanceRequestTransformer = maintenanceRequestTransformer;
        this.workflowService = workflowService;
        this.identityService = identityService;
        this.fileService = fileService;
    }

    @PostMapping(value = "/maintenance-requests/start-task")
    public ResponseEntity<MaintenanceRequest> startMaintenanceRequestTask(@RequestBody MaintenanceRequest vo) throws Exception {
        DexUser currentUser = mobileSecurityService.getCurrentUser();
        DexMaintenanceRequest maintenanceRequest = new DexMaintenanceRequestImpl();
        DexAsset asset = assetService.findAssetByCode(vo.getAsset().getCode());
        DexActor requester = currentUser.getActor();
        DexLocation location = assetService.findLocationByCode(vo.getLocation().getCode());
        maintenanceRequest.setRequester(requester);
        maintenanceRequest.setAsset(asset);
        maintenanceRequest.setDescription(vo.getDescription());
        maintenanceRequest.setRemark(vo.getRemark());
        maintenanceRequest.setLocation(location);
        maintenanceRequest.setRequestedDate(new Date());
        DexMaintenanceRequest savedMaintenanceReq = maintenanceRequestService.findMaintenanceRequestByReferenceNo(maintenanceRequestService.startMaintenanceRequestTask(maintenanceRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceRequestTransformer.toMaintenanceRequestVo(savedMaintenanceReq));
    }

    @PostMapping(value = "/maintenance-requests/{referenceNo}/upload")
    public ResponseEntity<DexFile> upload(@RequestParam("file") MultipartFile file, @PathVariable String referenceNo)  {
        DexMaintenanceRequest request = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
        DexFile dbFile = fileService.storeFile(file);
        request.setFile(dbFile);
        maintenanceRequestService.updateMaintenanceRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dbFile);
    }

    @GetMapping(value = "/maintenance-requests/count-assigned-task")
    public ResponseEntity<Integer> countAssignedMaintenanceRequestTask() throws Exception {
        int count = maintenanceRequestService.countAssignedMaintenanceRequestTask("%");
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/maintenance-requests/count-pooled-task")
    public ResponseEntity<Integer> countPooledMaintenanceRequestTask() throws Exception {

        int count = maintenanceRequestService.countPooledMaintenanceRequestTask("%");
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/maintenance-requests/assigned-tasks", params = {"filter"})
    public ResponseEntity<MaintenanceRequestTaskSummaryResult> findAssignedMaintenanceRequestTaskSummaries(@RequestParam(defaultValue = "%") String filter) throws Exception {

        int count = maintenanceRequestService.countAssignedMaintenanceRequestTask(filter);
        List<Task> tasks = maintenanceRequestService.findAssignedMaintenanceRequestTasks(filter, 0, 999);
        return ResponseEntity.ok(
                new MaintenanceRequestTaskSummaryResult(
                        maintenanceRequestTransformer.toMaintenanceRequestTaskSummaryVos(tasks),
                        count
                ));
    }

    @GetMapping(value = "/maintenance-requests/pooled-tasks", params = {"filter"})
    public ResponseEntity<MaintenanceRequestTaskSummaryResult> findPooledMaintenanceRequestTaskSummaries(@RequestParam(defaultValue = "%") String filter) throws Exception {

        int count = maintenanceRequestService.countPooledMaintenanceRequestTask(filter);
        List<Task> tasks = maintenanceRequestService.findPooledMaintenanceRequestTasks(filter, 0, 999);
        return ResponseEntity.ok(
                new MaintenanceRequestTaskSummaryResult(
                        maintenanceRequestTransformer.toMaintenanceRequestTaskSummaryVos(tasks),
                        count
                ));
    }

    @GetMapping(value = "/maintenance-requests/archived-records", params = {"filter", "page"})
    public ResponseEntity<MaintenanceRequestRecordSummaryResult> findPagedArchivedRequests(@RequestParam(defaultValue = "%") String filter,
                                                                                           @RequestParam Integer page) throws Exception {
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
    public ResponseEntity<List<MaintenanceRequestRecordSummary>> findArchivedRequests() throws Exception {
        LOG.debug("findArchivedRequests");
        List<DexMaintenanceRequest> requests = maintenanceRequestService.findMaintenanceRequests("%", 0, 999);
        LOG.debug("findArchivedRequests: {}", requests.size());
        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestRecordSummaryVos(requests));
    }

    @GetMapping(value = "/maintenance-requests/view-task/{taskId}")
    public ResponseEntity<MaintenanceRequestTask> viewMaintenanceRequestTaskById(@PathVariable String taskId) throws Exception {

        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestTaskVo(maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId)));
    }

    @PostMapping(value = "/maintenance-requests/claim-task/")
    public void claimMaintenanceRequestTask(@RequestBody List<String> taskIds) throws Exception {
        taskIds.forEach(taskId -> {
            Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
            workflowService.claimTask(task);
        });
    }

    @PostMapping(value = "/maintenance-requests/complete-task/{taskId}")
    public void completeMaintenanceRequestTask(@PathVariable String taskId) throws Exception {

        Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
        workflowService.completeTask(task);
    }

    @PostMapping(value = "/maintenance-requests/release-task/{taskId}")
    public ResponseEntity<ApplicationSuccess> releaseMaintenanceRequestTask(@PathVariable String taskId) throws Exception {

        Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
        workflowService.releaseTask(task);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/maintenance-requests/remove-task/{taskId}")
    public ResponseEntity<ApplicationSuccess> removeMaintenanceRequestTask(@PathVariable String taskId) throws Exception {

        Map<String, Object> params = Maps.newHashMap();
        params.put("removeDecision", true);
        Task task = maintenanceRequestService.findMaintenanceRequestTaskByTaskId(taskId);
        workflowService.completeTask(task, params);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/maintenance-requests", params = {"page"})
    public ResponseEntity<MaintenanceRequestResult> findPagedRequests(@RequestParam Integer page) throws Exception {

        LOG.debug("findPagedRequests");
        Integer count = maintenanceRequestService.countMaintenanceRequest("%");
        List<MaintenanceRequest> requests = maintenanceRequestTransformer.toMaintenanceRequestVos(
                maintenanceRequestService.findMaintenanceRequests("%", ((page - 1) * DexConstants.LIMIT), DexConstants.LIMIT));
        return new ResponseEntity<MaintenanceRequestResult>(new MaintenanceRequestResult(requests, count), HttpStatus.OK);
    }

    @GetMapping(value = "/maintenance-requests/{referenceNo}")
    public ResponseEntity<MaintenanceRequest> findMaintenanceRequestByReferenceNo(@PathVariable String referenceNo) throws Exception {

        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
        return ResponseEntity.ok(maintenanceRequestTransformer.toMaintenanceRequestVo(maintenanceRequest));
    }

    @PutMapping(value = "/maintenance-requests/{referenceNo}")
    public ResponseEntity<ApplicationSuccess> updateMaintenanceRequest(@PathVariable String referenceNo, @RequestBody MaintenanceRequest vo) throws Exception {

        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestByReferenceNo(referenceNo);
        maintenanceRequest.setSourceNo(vo.getSourceNo());
        maintenanceRequest.setDescription(vo.getDescription());
        maintenanceRequest.setDelegated(vo.getDelegated());
        maintenanceRequest.setRemark(vo.getRemark());
        maintenanceRequest.setRequestedDate(vo.getRequestedDate());
        maintenanceRequest.setLocation(assetService.findLocationById(vo.getLocation().getId()));
        maintenanceRequest.setRequester(identityService.findActorById(vo.getRequester().getId()));
        maintenanceRequest.setDelegator(identityService.findActorById(vo.getRequester().getId()));
        maintenanceRequest.setVerifier(identityService.findActorById(vo.getRequester().getId()));
        maintenanceRequestService.updateMaintenanceRequest(maintenanceRequest);
        return ResponseEntity.ok().build();
    }


}
