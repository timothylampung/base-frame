package my.spotit.asset.core.api;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author canang technologies
 */
public class FlowObject extends MetaObject {

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private FlowState flowState;

    public FlowState getFlowState() {
        return flowState;
    }

    public void setFlowState(FlowState flowState) {
        this.flowState = flowState;
    }
}
