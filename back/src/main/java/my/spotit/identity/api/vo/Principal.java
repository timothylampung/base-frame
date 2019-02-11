package my.spotit.identity.api.vo;


import my.spotit.core.api.MetaObject;

/**
 * @author canang technologies
 */
public class Principal extends MetaObject {
    private String name;
    private PrincipalType principalType;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public PrincipalType getPrincipalType() {
        return principalType;
    }

    public void setPrincipalType(PrincipalType principalType) {
        this.principalType = principalType;
    }
}
