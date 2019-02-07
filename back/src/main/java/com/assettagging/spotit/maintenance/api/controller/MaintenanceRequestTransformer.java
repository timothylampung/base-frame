package com.assettagging.spotit.maintenance.api.controller;

import com.assettagging.spotit.core.api.FlowState;
import com.assettagging.spotit.core.api.MetaState;
import com.assettagging.spotit.core.api.controller.CoreTransformer;
import com.assettagging.spotit.identity.api.controller.IdentityTransformer;
import com.assettagging.spotit.identity.api.vo.Actor;
import com.assettagging.spotit.maintenance.api.vo.MaintenanceRequest;
import com.assettagging.spotit.maintenance.api.vo.MaintenanceRequestRecordSummary;
import com.assettagging.spotit.maintenance.api.vo.MaintenanceRequestTask;
import com.assettagging.spotit.maintenance.api.vo.MaintenanceRequestTaskSummary;
import com.assettagging.spotit.maintenance.business.service.MaintenanceRequestService;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.workflow.business.service.WorkflowService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.assettagging.spotit.DexConstants.REQUEST_ID;

@Component("maintenanceRequestTransformer")
public class MaintenanceRequestTransformer {

    private IdentityTransformer identityTransformer;
    private CoreTransformer coreTransformer;
    private WorkflowService workflowService;
    private MaintenanceRequestService maintenanceRequestService;

    @Autowired
    public MaintenanceRequestTransformer(IdentityTransformer identityTransformer, CoreTransformer coreTransformer, WorkflowService workflowService, MaintenanceRequestService maintenanceRequestService) {
        this.identityTransformer = identityTransformer;
        this.coreTransformer = coreTransformer;
        this.workflowService = workflowService;
        this.maintenanceRequestService = maintenanceRequestService;
    }

    public MaintenanceRequest toMaintenanceRequestVo(DexMaintenanceRequest e) {
        if (e == null) return null;

        MaintenanceRequest vo = new MaintenanceRequest();
        vo.setDescription(e.getDescription());
        vo.setDescription(e.getDescription());
        Actor requester = identityTransformer.toActor(e.getRequester());
        vo.setRequester(requester);
        coreTransformer.toMetadata(e, vo);
        vo.setId(e.getId());
        return vo;
    }

    public List<MaintenanceRequest> toMaintenanceRequestVos(List<DexMaintenanceRequest> e) {
        List<MaintenanceRequest> vos = e.stream().map(this::toMaintenanceRequestVo).collect(Collectors.toList());
        return vos;
    }

    public List<MaintenanceRequestTaskSummary> toMaintenanceRequestTaskSummaryVos(List<Task> e) {
        List<MaintenanceRequestTaskSummary> vos = e.stream()
                .map((e1) -> toMaintenanceRequestTaskSummaryVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public MaintenanceRequestTaskSummary toMaintenanceRequestTaskSummaryVo(Task t) {
        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        DexMaintenanceRequest m = maintenanceRequestService.findMaintenanceRequestById((Long) vars.get(REQUEST_ID));
        MaintenanceRequestTaskSummary vo = new MaintenanceRequestTaskSummary();
        vo.setId(m.getId());
        vo.setTaskId(t.getId());
        vo.setReferenceNo(m.getReferenceNo());
        vo.setSourceNo(m.getSourceNo());
        vo.setDescription(m.getDescription());
        vo.setTaskName(t.getName());
        vo.setAssignee(t.getAssignee());
        vo.setCandidate(vo.getCandidate());
        vo.setRequest(toMaintenanceRequestVo(m));
        vo.setFlowState(FlowState.get(m.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(m.getMetadata().getState().ordinal()));
        return vo;
    }


    public MaintenanceRequestRecordSummary toMaintenanceRequestRecordSummaryVo(DexMaintenanceRequest m) {
        MaintenanceRequestRecordSummary vo = new MaintenanceRequestRecordSummary();
        vo.setReferenceNo(m.getReferenceNo());
        vo.setSourceNo(m.getSourceNo());
        vo.setDescription(m.getDescription());
        vo.setFlowState(FlowState.get(m.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(m.getMetadata().getState().ordinal()));
        return vo;
    }


    public List<MaintenanceRequestRecordSummary> toMaintenanceRequestRecordSummaryVos(List<DexMaintenanceRequest> e) {

        List<MaintenanceRequestRecordSummary> vos = e.stream()
                .map((e1) -> toMaintenanceRequestRecordSummaryVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public MaintenanceRequestTask toMaintenanceRequestTaskVo(Task t) {

        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestById((Long) vars.get(REQUEST_ID));
        MaintenanceRequestTask vo = new MaintenanceRequestTask();
        vo.setId(maintenanceRequest.getId());
        vo.setDescription(maintenanceRequest.getDescription());
        vo.setReferenceNo(maintenanceRequest.getReferenceNo());
        vo.setRequest(toMaintenanceRequestVo(maintenanceRequest));
        vo.setAssignee(t.getAssignee());
        vo.setTaskId(t.getId());
        vo.setTaskName(t.getName());
        vo.setSourceNo(maintenanceRequest.getSourceNo());
        vo.setCandidate(vo.getCandidate());
        vo.setFlowState(FlowState.get(maintenanceRequest.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(maintenanceRequest.getMetadata().getState().ordinal()));

        return vo;
    }
}
