package my.spotit.core.api;

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
