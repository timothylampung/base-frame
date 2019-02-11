package my.spotit.asset.asset.api.controller;

import my.spotit.asset.asset.api.vo.Activity;
import my.spotit.asset.asset.api.vo.Asset;
import my.spotit.asset.asset.api.vo.AssetCode;
import my.spotit.asset.asset.api.vo.Location;
import my.spotit.asset.workorder.api.vo.WorkOrder;
import my.spotit.asset.workorder.api.vo.WorkOrderRecordSummary;
import my.spotit.asset.workorder.api.vo.WorkOrderTask;
import my.spotit.asset.workorder.api.vo.WorkOrderTaskSummary;
import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexAssetCode;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.common.api.controller.CommonTransformer;
import my.spotit.asset.common.business.service.CommonService;
import my.spotit.asset.core.api.FlowState;
import my.spotit.asset.core.api.MetaState;
import my.spotit.asset.core.api.controller.CoreTransformer;
import my.spotit.asset.identity.api.controller.IdentityTransformer;
import my.spotit.asset.workflow.business.service.WorkflowService;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.flowable.job.service.impl.JobQueryProperty.JOB_ID;

@Component("assetTransformer")
public class AssetTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(AssetTransformer.class);

    private TaskService taskService;
    private AssetService assetService;
    private CommonService commonService;
    private WorkflowService workflowService;
    private IdentityTransformer identityTransformer;
    private CommonTransformer commonTransformer;
    private CoreTransformer coreTransformer;

    @Autowired
    public AssetTransformer(AssetService assetService, CommonService commonService,
                            TaskService taskService, WorkflowService workflowService,
                            IdentityTransformer identityTransformer,
                            CommonTransformer commonTransformer, CoreTransformer coreTransformer) {
        this.assetService = assetService;
        this.commonService = commonService;
        this.taskService = taskService;
        this.workflowService = workflowService;
        this.identityTransformer = identityTransformer;
        this.commonTransformer = commonTransformer;
        this.coreTransformer = coreTransformer;
    }

    // =============================================================================================
    // ASSET CODE
    // =============================================================================================

    public AssetCode toAssetCodeVo(DexAssetCode e) {
        if (e == null) return null;
        AssetCode vo = new AssetCode();
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        vo.setId(e.getId());
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<AssetCode> toAssetCodeVos(List<DexAssetCode> e) {
        return e.stream().map(this::toAssetCodeVo).collect(Collectors.toList());
    }

    // =============================================================================================
    // ASSET
    // =============================================================================================

    public Asset toAssetVo(DexAsset e) {
        if (null == e) return null;
        Asset vo = new Asset();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        vo.setLocation(toLocationVo(e.getLocation()));
        vo.setAssetCode(toAssetCodeVo(e.getAssetCode()));
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<Asset> toAssetVos(List<DexAsset> e) {
        return e.stream().map((e1) -> toAssetVo(e1)).collect(Collectors.toList());
    }

    // =============================================================================================
    // LOCATION
    // =============================================================================================

    public Location toLocationVo(DexLocation e) {
        if (null == e) return null;
        Location vo = new Location();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<Location> toLocationVos(List<DexLocation> e) {
        return e.stream().map((e1) -> toLocationVo(e1)).collect(Collectors.toList());
    }
}
