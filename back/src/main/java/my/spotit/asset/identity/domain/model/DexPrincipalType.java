package my.spotit.asset.identity.domain.model;

/**
 * @author canang technologies
 */
public enum DexPrincipalType {

    USER, //0
    GROUP; //1

    public static DexPrincipalType get(int index) {
        return values()[index];
    }
 }
