package my.spotit.identity.api.vo;

/**
 * @author canang technologies
 */
public enum ActorType {

    STAFF, //0
    FACILITY_MANAGER,
    SUPERVISOR,
    TECHNICIAN;

    public static ActorType get(int index) {
        return values()[index];
    }

}
