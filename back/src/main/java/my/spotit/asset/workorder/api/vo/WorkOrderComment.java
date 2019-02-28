package my.spotit.asset.workorder.api.vo;

import java.sql.Timestamp;

import my.spotit.asset.core.api.MetaObject;
import my.spotit.asset.identity.api.vo.User;

/**
 *
 * @author canang technologies
 */
public class WorkOrderComment extends MetaObject
{
    private String body;
    private User poster;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }
}
