package my.spotit.asset.core.api;

/**
 * @author canang technologies
 */
public enum MetaState {

    INACTIVE, // 0
    ACTIVE;   // 1

    public static MetaState get(int index) {
        return values()[index];
    }

}
