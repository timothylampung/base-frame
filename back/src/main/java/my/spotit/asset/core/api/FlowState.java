package my.spotit.asset.core.api;

/**
 * @author canang technologies
 */
public enum FlowState {
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
    PUBLISHED,              // 13
    REJECTED,               // 14
    REMOVED,                // 15
    COMPLETED,              // 16
    ARCHIVED;               // 17

    public static FlowState get(int index) {
        return values()[index];
    }

}
