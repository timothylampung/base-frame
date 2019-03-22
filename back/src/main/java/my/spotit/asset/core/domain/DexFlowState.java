package my.spotit.asset.core.domain;

/**
 * @author canang technologies
 * @since 20/4/2015.
 */
public enum DexFlowState {
    NEW,                    // 1
    DRAFTED,                // 1
    REQUESTED,              // 2
    REGISTERED,             // 3
    PREPARED,               // 4
    VERIFIED,               // 5
    UPPER_VERIFIED,         // 6
    CHECKED,                // 7
    APPROVED,               // 8
    UPPER_APPROVED,         // 9
    SELECTED,               // 10
    EVALUATED,              // 11
    CANCELLED,              // 12
    MAINTAINED,              // 13
    REJECTED,               // 14
    REMOVED,                // 15
    COMPLETED,              // 16
    OFFERED,                 // 17
    ARCHIVED;               // 18

    DexFlowState() {
    }
}
