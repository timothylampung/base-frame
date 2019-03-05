package my.spotit.asset.workorder.api.controller;

import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import my.spotit.asset.DexConstants;
import my.spotit.asset.asset.api.vo.WorkOrderActivity;
import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.common.api.controller.CommonTransformer;
import my.spotit.asset.common.business.service.CommonService;
import my.spotit.asset.core.api.FlowState;
import my.spotit.asset.core.api.MetaState;
import my.spotit.asset.core.api.controller.CoreTransformer;
import my.spotit.asset.identity.api.controller.IdentityTransformer;
import my.spotit.asset.workflow.business.service.WorkflowService;
import my.spotit.asset.workorder.api.vo.WorkOrder;
import my.spotit.asset.workorder.api.vo.WorkOrderComment;
import my.spotit.asset.workorder.api.vo.WorkOrderLog;
import my.spotit.asset.workorder.api.vo.WorkOrderRecordSummary;
import my.spotit.asset.workorder.api.vo.WorkOrderTask;
import my.spotit.asset.workorder.api.vo.WorkOrderTaskSummary;
import my.spotit.asset.workorder.business.service.WorkOrderService;
import my.spotit.asset.workorder.domain.model.DexWorkOrderActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderComment;
import my.spotit.asset.workorder.domain.model.DexWorkOrderLog;

@Component("workOrderTransformer")
public class WorkOrderTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(WorkOrderTransformer.class);

    private TaskService taskService;
    private AssetService assetService;
    private WorkOrderService workOrderService;
    private CommonService commonService;
    private WorkflowService workflowService;
    private IdentityTransformer identityTransformer;
    private CommonTransformer commonTransformer;
    private CoreTransformer coreTransformer;

    @Autowired
    public WorkOrderTransformer(AssetService assetService, CommonService commonService,
                                TaskService taskService, WorkflowService workflowService,
                                WorkOrderService workOrderService,
                                IdentityTransformer identityTransformer,
                                CommonTransformer commonTransformer, CoreTransformer coreTransformer) {
        this.assetService = assetService;
        this.commonService = commonService;
        this.workOrderService = workOrderService;
        this.taskService = taskService;
        this.workflowService = workflowService;
        this.identityTransformer = identityTransformer;
        this.commonTransformer = commonTransformer;
        this.coreTransformer = coreTransformer;
    }

    //==============================================================================================
    // WORK ORDER
    //==============================================================================================

    public WorkOrder toWorkOrderVo(DexWorkOrder e) {
        if (null == e) return null;
        WorkOrder vo = new WorkOrder();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setDescription(e.getDescription());
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        return vo;
    }

    public WorkOrder toSimpleWorkOrderVo(DexWorkOrder e) {
        if (null == e) return null;
        WorkOrder vo = new WorkOrder();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setDescription(e.getDescription());
        return vo;
    }

    public WorkOrderActivity toWorkOrderActivityVo(DexWorkOrderActivity e) {
        if (null == e) return null;
        WorkOrderActivity vo = new WorkOrderActivity();
        vo.setId(e.getId());
        vo.setDescription(e.getDescription());
        return vo;
    }

    public WorkOrderLog toWorkOrderLogVo(DexWorkOrderLog e) {
        if (null == e) return null;
        WorkOrderLog vo = new WorkOrderLog();
        vo.setId(e.getId());
        vo.setStartTime(e.getStartTime());
        vo.setStopTime(e.getStopTime());
        vo.setLogger(identityTransformer.toUserVo(e.getLogger()));
        return vo;
    }

    public WorkOrderComment toWorkOrderCommentVo(DexWorkOrderComment e) {
        if (null == e) return null;
        WorkOrderComment vo = new WorkOrderComment();
        vo.setId(e.getId());
        vo.setBody(e.getBody());
        vo.setPoster(identityTransformer.toUserVo(e.getPoster()));
        return vo;
    }

    public WorkOrderRecordSummary toWorkOrderRecordSummaryVo(DexWorkOrder m) {
        WorkOrderRecordSummary vo = new WorkOrderRecordSummary();
        vo.setReferenceNo(m.getReferenceNo());
        vo.setSourceNo(m.getSourceNo());
        vo.setDescription(m.getDescription());
        vo.setFlowState(FlowState.get(m.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(m.getMetadata().getState().ordinal()));
        return vo;
    }

    public WorkOrderTask toWorkOrderTaskVo(Task t) {
        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        DexWorkOrder job = workOrderService.findWorkOrderById((Long) vars.get(DexConstants.ORDER_ID));

        WorkOrderTask vo = new WorkOrderTask();
        vo.setId(job.getId());
        vo.setTaskId(t.getId());
        vo.setReferenceNo(job.getReferenceNo());
        vo.setTaskName(t.getName());
        vo.setSourceNo(job.getSourceNo());
        vo.setDescription(job.getDescription());
        vo.setCandidate(vo.getCandidate());
        vo.setAssignee(vo.getAssignee());
        vo.setWorkOrder(toWorkOrderVo(job));

        vo.setFlowState(FlowState.get(job.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(job.getMetadata().getState().ordinal()));
        return vo;
    }

    public WorkOrderTaskSummary toWorkOrderTaskSummaryVo(Task t) {
        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        DexWorkOrder m = workOrderService.findWorkOrderById((Long) vars.get(DexConstants.ORDER_ID));

        WorkOrderTaskSummary vo = new WorkOrderTaskSummary();
        vo.setId(m.getId());
        vo.setTaskId(t.getId());
        vo.setReferenceNo(m.getReferenceNo());
        vo.setSourceNo(m.getSourceNo());
        vo.setDescription(m.getDescription());
        vo.setTaskName(t.getName());
        vo.setAssignee(vo.getAssignee());
        vo.setCandidate(vo.getCandidate());
        vo.setFlowState(FlowState.get(m.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(m.getMetadata().getState().ordinal()));
        vo.setWorkOrder(toSimpleWorkOrderVo(m));
        return vo;
    }

    public List<WorkOrder> toWorkOrderVos(List<DexWorkOrder> e) {
        List<WorkOrder> vos = e.stream()
                .map((e1) -> toWorkOrderVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public List<WorkOrderActivity> toWorkOrderActivityVos(List<DexWorkOrderActivity> e) {
        List<WorkOrderActivity> vos = e.stream()
                .map((e1) -> toWorkOrderActivityVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public List<WorkOrderLog> toWorkOrderLogVos(List<DexWorkOrderLog> e) {
        List<WorkOrderLog> vos = e.stream()
                .map((e1) -> toWorkOrderLogVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public List<WorkOrderComment> toWorkOrderCommentVos(List<DexWorkOrderComment> e) {
        List<WorkOrderComment> vos = e.stream()
                .map((e1) -> toWorkOrderCommentVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public List<WorkOrderRecordSummary> toWorkOrderSummaryVos(List<DexWorkOrder> e) {
        List<WorkOrderRecordSummary> vos = e.stream()
                .map((e1) -> toWorkOrderRecordSummaryVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public List<WorkOrderTaskSummary> toWorkOrderTaskSummaryVos(List<Task> e) {
        List<WorkOrderTaskSummary> vos = e.stream()
                .map((e1) -> toWorkOrderTaskSummaryVo(e1))
                .collect(Collectors.toList());
        return vos;
    }
}
