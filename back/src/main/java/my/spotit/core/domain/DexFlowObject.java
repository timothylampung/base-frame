package my.spotit.core.domain;

/**
 * @author canang technologies
 * @since 4/2/2016.
 */
public interface DexFlowObject extends DexMetaObject {
    /**
     * get flow data
     *
     * @return
     */
    DexFlowdata getFlowdata();

    /**
     * set flow data
     *
     * @param flowdata
     */
    void setFlowdata(DexFlowdata flowdata);

}
