package my.spotit.asset.integration.mobile.workorder.api;

import my.spotit.asset.asset.api.vo.WorkOrderActivity;
import my.spotit.asset.core.api.ApplicationSuccess;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.integration.mobile.security.MobileSecurityService;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.business.service.SystemService;
import my.spotit.asset.workflow.business.service.WorkflowService;
import my.spotit.asset.workorder.api.controller.WorkOrderTransformer;
import my.spotit.asset.workorder.api.vo.*;
import my.spotit.asset.workorder.business.service.WorkOrderService;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderComment;
import my.spotit.asset.workorder.domain.model.DexWorkOrderCommentImpl;
import my.spotit.asset.workorder.domain.model.DexWorkOrderImpl;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static my.spotit.asset.DexConstants.LIMIT;
import static my.spotit.asset.DexConstants.WORK_ORDER_REFERENCE_NO;

@Transactional
@RestController
@RequestMapping("/api/mobile/work-order")
public class IntegrationWorkOrderController {
    private static final Logger LOG = LoggerFactory.getLogger(IntegrationWorkOrderController.class);

    private WorkOrderService workOrderService;
    private MobileSecurityService securityService;
    private WorkflowService workflowService;
    private IdentityService identityService;
    private MobileSecurityService mobileSecurityService;
    private WorkOrderTransformer workOrderTransformer;
    private SystemService systemService;

    @Autowired
    public IntegrationWorkOrderController(WorkOrderService workOrderService, MobileSecurityService securityService, WorkflowService workflowService, IdentityService identityService, MobileSecurityService mobileSecurityService, WorkOrderTransformer workOrderTransformer, SystemService systemService) {
        this.workOrderService = workOrderService;
        this.securityService = securityService;
        this.workflowService = workflowService;
        this.identityService = identityService;
        this.mobileSecurityService = mobileSecurityService;
        this.workOrderTransformer = workOrderTransformer;
        this.systemService = systemService;
    }


    @GetMapping(value = "/work-orders/count-assigned-task")
    public ResponseEntity<?> countAssignedWorkOrderTask() {
        int count = workOrderService.countAssignedWorkOrderTask("%");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id", "order:assigned");
        map.put("taskCount", count);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping(value = "/work-orders/count-pooled-task")
    public ResponseEntity<?> countPooledWorkOrderTasks() {
        int count = workOrderService.countPooledWorkOrderTask("%");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id", "order:pooled");
        map.put("taskCount", count);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping(value = "/work-orders/archived-records", params = {"filter", "page"})
    public ResponseEntity<WorkOrderRecordSummaryResult> findPagedArchivedWorkOrders(@RequestParam(defaultValue = "%") String filter,
                                                                                    @RequestParam Integer page) {
        Integer count = workOrderService.countWorkOrder(filter);
        List<DexWorkOrder> orders = workOrderService.findWorkOrders(filter, (page - 1) * LIMIT, LIMIT);
        LOG.debug("findArchivedWorkOrders: {}", orders.size());
        List<WorkOrderRecordSummary> data = workOrderTransformer.toWorkOrderSummaryVos(orders);
        return ResponseEntity.ok(new WorkOrderRecordSummaryResult(data, count));
    }

    @GetMapping(value = "/work-orders/assigned-tasks", params = {"filter"})
    public ResponseEntity<WorkOrderTaskSummaryResult> findAssignedWorkOrderTaskSummaries(@RequestParam(defaultValue = "%") String filter) {
        int count = workOrderService.countAssignedWorkOrderTask(filter);
        List<Task> tasks = workOrderService.findAssignedWorkOrderTasks(filter, 0, 999);
        List<WorkOrderTaskSummary> data = workOrderTransformer.toWorkOrderTaskSummaryVos(tasks);
        return ResponseEntity.ok(new WorkOrderTaskSummaryResult(data, count));
    }

    @GetMapping(value = "/work-orders/pooled-tasks", params = {"filter"})
    public ResponseEntity<WorkOrderTaskSummaryResult> findPooledWorkOrderTaskSummaries(@RequestParam(defaultValue = "%") String filter) {
        int count = workOrderService.countPooledWorkOrderTask(filter);
        List<Task> tasks = workOrderService.findPooledWorkOrderTasks(filter, 0, 999);
        List<WorkOrderTaskSummary> data = workOrderTransformer.toWorkOrderTaskSummaryVos(tasks);
        return ResponseEntity.ok(new WorkOrderTaskSummaryResult(data, count));
    }

    @PostMapping(value = "/work-orders/start-task")
    public ResponseEntity<ApplicationSuccess> startWorkOrderTask(@RequestBody WorkOrder vo) {
        LOG.debug("start task");
        try {
            DexWorkOrder order = new DexWorkOrderImpl();
            order.setReferenceNo(systemService.generateSequenceGenerator(WORK_ORDER_REFERENCE_NO));
            order.setSourceNo(vo.getDescription());
            order.setDescription(vo.getDescription());
            // todo: more props
            workOrderService.startWorkOrderTask(order);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("error processing order");
        }
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess(), HttpStatus.OK);
    }

    @GetMapping(value = "/work-orders/view-task/{taskId}")
    public ResponseEntity<WorkOrderTask> viewWorkOrderWorkOrderById(@PathVariable String taskId) {
        return new ResponseEntity<WorkOrderTask>(
                workOrderTransformer.toWorkOrderTaskVo(workOrderService.findWorkOrderTaskByTaskId(taskId)),
                HttpStatus.OK);
    }

    @PostMapping(value = "/work-orders/claim-task")
    public void claimWorkOrderTask(@RequestBody List<String> taskIds) {
        taskIds.forEach(taskId -> {
            Task task = workOrderService.findWorkOrderTaskByTaskId(taskId);
            workflowService.claimTask(task);
        });
    }

    @PostMapping(value = "/work-orders/complete-task/{taskId}")
    public void completeWorkOrderTask(@PathVariable String taskId) {
        Task task = workOrderService.findWorkOrderTaskByTaskId(taskId);
        workflowService.completeTask(task);
    }

    @PostMapping(value = "/work-orders/release-task/{taskId}")
    public ResponseEntity<ApplicationSuccess> releaseWorkOrderTask(@PathVariable String taskId) {
        Task task = workOrderService.findWorkOrderTaskByTaskId(taskId);
        workflowService.releaseTask(task);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess(), HttpStatus.OK);
    }

    @GetMapping(value = "/work-orders", params = {"filter", "page"})
    public ResponseEntity<WorkOrderResult> findPagedWorkOrders(@RequestParam(defaultValue = "%") String filter, @RequestParam Integer page) {
        LOG.debug("findPagedWorkOrders: {}", page);
        Integer count = workOrderService.countWorkOrder(filter);
        List<WorkOrder> order = workOrderTransformer.toWorkOrderVos(workOrderService.findWorkOrders(filter, LIMIT * (page - 1), LIMIT));
        return new ResponseEntity<WorkOrderResult>(new WorkOrderResult(order, count), HttpStatus.OK);
    }

    @GetMapping(value = "/work-orders/{id}")
    public ResponseEntity<WorkOrder> findWorkOrderById(@PathVariable long id) {
        return new ResponseEntity<WorkOrder>(workOrderTransformer.toWorkOrderVo(
                workOrderService.findWorkOrderById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/work-orders/{referenceNo}")
    public ResponseEntity<WorkOrder> findWorkOrderByReferenceNo(@PathVariable String referenceNo) {
        WorkOrder transformed = workOrderTransformer.toWorkOrderVo(
                workOrderService.findWorkOrderByReferenceNo(referenceNo));
        return new ResponseEntity<WorkOrder>(transformed, HttpStatus.OK);
    }

    @GetMapping(value = "/work-orders")
    public ResponseEntity<List<WorkOrder>> findWorkOrders() {
        return new ResponseEntity<List<WorkOrder>>(workOrderTransformer.toWorkOrderVos(
                workOrderService.findWorkOrders("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @PostMapping(value = "/work-orders")
    public ResponseEntity<String> saveWorkOrder(@RequestBody WorkOrder vo) {
        DexWorkOrder order = new DexWorkOrderImpl();
        order.setReferenceNo(vo.getReferenceNo());
        workOrderService.saveWorkOrder(order);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/work-orders/{referenceNo}")
    public ResponseEntity<String> updateWorkOrder(@PathVariable String referenceNo, @RequestBody WorkOrder vo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(vo.getReferenceNo());
        workOrderService.updateWorkOrder(order);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/work-orders/{referenceNo}")
    public ResponseEntity<String> removeWorkOrder(@PathVariable String referenceNo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(referenceNo);
        workOrderService.removeWorkOrder(order);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    //==============================================================================================
    // WORK ORDER ACTIVITY
    //==============================================================================================

    @GetMapping(value = "/work-orders/{referenceNo}/work-order-activities")
    public ResponseEntity<List<WorkOrderActivity>> findWorkOrderActivities(@PathVariable String referenceNo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(referenceNo);
        List<WorkOrderActivity> activities = workOrderTransformer.toWorkOrderActivityVos(workOrderService.findWorkOrderActivities("%", order, 0, 9999)); // todo: page
        return new ResponseEntity<List<WorkOrderActivity>>(activities, HttpStatus.OK);
    }

    //==============================================================================================
    // WORK ORDER LOG
    //==============================================================================================

    @GetMapping(value = "/work-orders/{referenceNo}/work-order-logs")
    public ResponseEntity<List<WorkOrderLog>> findWorkOrderLogs(@PathVariable String referenceNo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(referenceNo);
        List<WorkOrderLog> workOrderLogs = workOrderTransformer.toWorkOrderLogVos(workOrderService.findWorkOrderLogs("%", order, 0, 9999)); // todo: page
        return new ResponseEntity<List<WorkOrderLog>>(workOrderLogs, HttpStatus.OK);
    }

    @PostMapping(value = "/work-orders/{referenceNo}/start-work-order-log")
    public ResponseEntity<ApplicationSuccess> startLog(@PathVariable String referenceNo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(referenceNo);
        workOrderService.startWorkOrderLog(order);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess(), HttpStatus.OK);
    }

    @PostMapping(value = "/work-orders/{referenceNo}/stop-work-order-log")
    public ResponseEntity<ApplicationSuccess> stopLog(@PathVariable String referenceNo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(referenceNo);
        workOrderService.stopWorkOrderLog(order);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess(), HttpStatus.OK);
    }
    //==============================================================================================
    // WORK ORDER COMMENT
    //==============================================================================================

    @GetMapping(value = "/work-orders/{referenceNo}/work-order-comments")
    public ResponseEntity<List<WorkOrderComment>> findWorkOrderComments(@PathVariable String referenceNo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(referenceNo);
        List<WorkOrderComment> workOrderComments = workOrderTransformer.toWorkOrderCommentVos(workOrderService.findWorkOrderComments("%", order, 0, 9999));
        return new ResponseEntity<List<WorkOrderComment>>(workOrderComments, HttpStatus.OK);
    }

    @PostMapping(value = "/work-orders/{referenceNo}/work-order-comments")
    public ResponseEntity<ApplicationSuccess> addComment(@PathVariable String referenceNo, @RequestBody WorkOrderComment vo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(referenceNo);
        DexWorkOrderComment comment = new DexWorkOrderCommentImpl();
        comment.setBody(vo.getBody());
        comment.setPoster(securityService.getCurrentUser());
        workOrderService.addWorkOrderComment(order, comment);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess(), HttpStatus.OK);
    }


}
