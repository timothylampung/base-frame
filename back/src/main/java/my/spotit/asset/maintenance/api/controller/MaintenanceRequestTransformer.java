package my.spotit.asset.maintenance.api.controller;

import my.spotit.asset.asset.api.controller.AssetTransformer;
import my.spotit.asset.asset.api.vo.Asset;
import my.spotit.asset.common.api.controller.CommonTransformer;
import my.spotit.asset.core.api.FlowState;
import my.spotit.asset.core.api.MetaState;
import my.spotit.asset.core.api.controller.CoreTransformer;
import my.spotit.asset.identity.api.controller.IdentityTransformer;
import my.spotit.asset.identity.api.vo.Actor;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequest;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequestRecordSummary;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequestTask;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequestTaskSummary;
import my.spotit.asset.maintenance.business.service.MaintenanceRequestService;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.workflow.business.service.WorkflowService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import my.spotit.asset.DexConstants;

@Component("maintenanceRequestTransformer")
public class MaintenanceRequestTransformer {

    private IdentityTransformer identityTransformer;
    private CoreTransformer coreTransformer;
    private CommonTransformer commonTransformer;
    private WorkflowService workflowService;
    private MaintenanceRequestService maintenanceRequestService;
    private AssetTransformer assetTransformer;

    @Autowired
    public MaintenanceRequestTransformer(IdentityTransformer identityTransformer,
                                         CoreTransformer coreTransformer, CommonTransformer commonTransformer, AssetTransformer assetTransformer, WorkflowService workflowService,
                                         MaintenanceRequestService maintenanceRequestService) {
        this.identityTransformer = identityTransformer;
        this.coreTransformer = coreTransformer;
        this.commonTransformer = commonTransformer;
        this.workflowService = workflowService;
        this.maintenanceRequestService = maintenanceRequestService;
        this.assetTransformer = assetTransformer;

    }

    public MaintenanceRequest toMaintenanceRequestVo(DexMaintenanceRequest e) {
        if (e == null) return null;

        MaintenanceRequest vo = new MaintenanceRequest();
        vo.setId(e.getId());
        vo.setDescription(e.getDescription());
        vo.setRemark(e.getRemark());
        Asset asset = assetTransformer.toAssetVo(e.getAsset());
        vo.setAsset(asset);
        vo.setAsset(assetTransformer.toAssetVo(e.getAsset()));
        vo.setLocation(assetTransformer.toLocationVo(e.getLocation()));
        Actor delegator = identityTransformer.toActor(e.getDelegator());
        vo.setDelegator(delegator);
        Actor verifier = identityTransformer.toActor(e.getVerifier());
        vo.setVerifier(verifier);
        Actor requester = identityTransformer.toActor(e.getRequester());
        vo.setRequester(requester);
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setReferenceNo(e.getReferenceNo());
        vo.setFile(commonTransformer.toFileVo(e.getFile()));
        //TODO check props
        coreTransformer.toFlowdata(e, vo);
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
        DexMaintenanceRequest m = maintenanceRequestService.findMaintenanceRequestById((Long) vars.get(DexConstants.REQUEST_ID));
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
        //todo more props
        return vo;
    }

    public MaintenanceRequestRecordSummary toMaintenanceRequestRecordSummaryVo(DexMaintenanceRequest m) {
        MaintenanceRequestRecordSummary vo = new MaintenanceRequestRecordSummary();
        vo.setId(m.getId());
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
        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestById((Long) vars.get(DexConstants.REQUEST_ID));
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
