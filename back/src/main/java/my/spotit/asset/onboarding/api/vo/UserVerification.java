package my.spotit.asset.onboarding.api.vo;

public class UserVerification {

    private String username;
    private String realname;
    private String password;
    private String deviceId;
    private String deviceName;
    private String email;
    private String identityNo;

    public UserVerification() {
    }

    public UserVerification(String username, String realname, String password, String deviceId, String deviceName, String email, String identityNo) {
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.email = email;
        this.identityNo = identityNo;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
