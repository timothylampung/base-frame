package my.spotit.asset.workorder.api.controller;

import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import my.spotit.asset.asset.api.vo.Activity;
import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.core.api.ApplicationSuccess;
import my.spotit.asset.system.business.service.SystemService;
import my.spotit.asset.workflow.business.service.WorkflowService;
import my.spotit.asset.workorder.api.vo.WorkOrder;
import my.spotit.asset.workorder.api.vo.WorkOrderLog;
import my.spotit.asset.workorder.api.vo.WorkOrderRecordSummaryResult;
import my.spotit.asset.workorder.api.vo.WorkOrderResult;
import my.spotit.asset.workorder.api.vo.WorkOrderTask;
import my.spotit.asset.workorder.api.vo.WorkOrderTaskSummaryResult;
import my.spotit.asset.workorder.business.service.WorkOrderService;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexActivityImpl;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderImpl;
import my.spotit.asset.workorder.domain.model.DexWorkOrderLog;
import my.spotit.asset.workorder.domain.model.DexWorkOrderLogImpl;

import static my.spotit.asset.DexConstants.LIMIT;
import static my.spotit.asset.DexConstants.WORK_ORDER_REFERENCE_NO;

@Transactional
@RestController
@RequestMapping("/api/work-order")
public class WorkOrderController {

    private static final Logger LOG = LoggerFactory.getLogger(WorkOrderController.class);

    private AssetService assetService;
    private WorkOrderService workOrderService;
    private SystemService systemService;
    private WorkflowService workflowService;
    private WorkOrderTransformer workOrderTransformer;
    private AuthenticationManager authenticationManager;

    @Autowired
    public WorkOrderController(AssetService assetService, SystemService systemService,
                               WorkflowService workflowService,
                               WorkOrderService workOrderService,
                               WorkOrderTransformer workOrderTransformer,
                               AuthenticationManager authenticationManager) {
        this.assetService = assetService;
        this.systemService = systemService;
        this.workOrderService = workOrderService;
        this.workflowService = workflowService;
        this.workOrderTransformer = workOrderTransformer;
        this.authenticationManager = authenticationManager;
    }

    //==============================================================================================
    // WORK ORDER
    //==============================================================================================

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
        return ResponseEntity.ok(
                new WorkOrderRecordSummaryResult(
                        workOrderTransformer.toWorkOrderSummaryVos(orders),
                        count
                ));
    }

    @GetMapping(value = "/work-orders/assigned-tasks", params = {"filter"})
    public ResponseEntity<WorkOrderTaskSummaryResult> findAssignedWorkOrderTaskSummaries(@RequestParam(defaultValue = "%") String filter) {
        int count = workOrderService.countAssignedWorkOrderTask(filter);
        List<Task> tasks = workOrderService.findAssignedWorkOrderTasks(filter, 0, 999);
        return ResponseEntity.ok(
                new WorkOrderTaskSummaryResult(
                        workOrderTransformer.toWorkOrderTaskSummaryVos(tasks),
                        count
                ));
    }

    @GetMapping(value = "/work-orders/pooled-tasks", params = {"filter"})
    public ResponseEntity<WorkOrderTaskSummaryResult> findPooledWorkOrderTaskSummaries(@RequestParam(defaultValue = "%") String filter) {
        int count = workOrderService.countPooledWorkOrderTask(filter);
        List<Task> tasks = workOrderService.findPooledWorkOrderTasks(filter, 0, 999);
        return ResponseEntity.ok(
                new WorkOrderTaskSummaryResult(
                        workOrderTransformer.toWorkOrderTaskSummaryVos(tasks),
                        count
                ));

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

    @GetMapping(value = "/work-orders/{referenceNo}/activities")
    public ResponseEntity<List<Activity>> findActivities(@PathVariable String referenceNo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(referenceNo);
        List<Activity> activities = workOrderTransformer.toActivityVos(workOrderService.findActivities("%", order, 0, 9999)); // todo: page
        return new ResponseEntity<List<Activity>>(activities, HttpStatus.OK);
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

    @PostMapping(value = "/work-orders/{referenceNo}/start-log")
    public ResponseEntity<String> startLog(@PathVariable String referenceNo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(referenceNo);
        workOrderService.startLog(order);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PostMapping(value = "/work-orders/{referenceNo}/stop-log")
    public ResponseEntity<String> stopLog(@PathVariable String referenceNo) {
        DexWorkOrder order = workOrderService.findWorkOrderByReferenceNo(referenceNo);
        workOrderService.stopLog(order);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}




