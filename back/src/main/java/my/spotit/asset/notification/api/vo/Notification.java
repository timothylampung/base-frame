package my.spotit.asset.notification.api.vo;

import my.spotit.asset.core.api.Document;
import my.spotit.asset.core.api.MetaObject;

public class Notification extends MetaObject {

    private String recieverEmail;
    private String message;
    private NotificationContext context;
    private Document document;

    public String getRecieverEmail() {
        return recieverEmail;
    }

    public void setRecieverEmail(String recieverEmail) {
        this.recieverEmail = recieverEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationContext getContext() {
        return context;
    }

    public void setContext(NotificationContext context) {
        this.context = context;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
