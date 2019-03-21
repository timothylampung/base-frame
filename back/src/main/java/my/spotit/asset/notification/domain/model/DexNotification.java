package my.spotit.asset.notification.domain.model;

import my.spotit.asset.core.domain.DexDocument;
import my.spotit.asset.core.domain.DexMetaObject;

public interface DexNotification extends DexMetaObject {

    String getRecieverEmail();

    void setRecieverEmail(String recieverCode);

    String getMessage();

    void setMessage(String message);

    DexNotificationContext getContext();

    void setContext(DexNotificationContext context);

    DexDocument getDocument();

    void setDocument(DexDocument document);
}
