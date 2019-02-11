package my.spotit.asset.core.api;

/**
 * @author canang technologies
 */
public class FlowObject extends MetaObject {

    private FlowState flowState;

    public FlowState getFlowState() {
        return flowState;
    }

    public void setFlowState(FlowState flowState) {
        this.flowState = flowState;
    }
}
