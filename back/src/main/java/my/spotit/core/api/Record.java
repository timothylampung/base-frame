package my.spotit.core.api;

/**
 * @author canang technologies
 */
public class Record extends MetaObject {

    private Permission permission;
    private MetaState metaState;
    private FlowState flowState;

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public FlowState getFlowState() {
        return flowState;
    }

    public void setFlowState(FlowState flowState) {
        this.flowState = flowState;
    }
}
