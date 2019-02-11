package my.spotit.asset.identity.api.vo;

/**
 * @author canang technologies
 */
public class Group extends Principal {

    private Integer memberCount;

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }
}
