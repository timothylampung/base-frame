package my.spotit.asset.identity.api.vo;

/**
 * @author canang technologies
 */
public class User extends Principal {

    private String realName;
    private String email;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
