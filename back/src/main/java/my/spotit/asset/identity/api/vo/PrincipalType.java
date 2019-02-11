package my.spotit.asset.identity.api.vo;

/**
 * Created by User on 21/01/2018.
 */
public enum PrincipalType {
    USER,
    GROUP;

    public static PrincipalType get(int ordinal){
        return values()[ordinal];
    }
}
