package my.spotit.asset.notification.api;

import my.spotit.asset.common.api.controller.CommonTransformer;
import my.spotit.asset.core.api.Document;
import my.spotit.asset.core.api.controller.CoreTransformer;
import my.spotit.asset.core.domain.DexDocument;
import my.spotit.asset.notification.api.vo.Notification;
import my.spotit.asset.notification.api.vo.NotificationContext;
import my.spotit.asset.notification.domain.model.DexNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationTransformer {

    private CommonTransformer commonTransformer;
    private CoreTransformer coreTransformer;

    @Autowired
    public NotificationTransformer(CommonTransformer commonTransformer, CoreTransformer coreTransformer) {
        this.commonTransformer = commonTransformer;
        this.coreTransformer = coreTransformer;
    }

    public Notification toNotificationVo(DexNotification e) {
        Notification vo = new Notification();
        if (e == null) return null;
        vo.setMessage(e.getMessage());
        vo.setContext(NotificationContext.get(e.getContext().ordinal()));
        vo.setRecieverEmail(e.getRecieverEmail());
        Document doc = new Document();
        DexDocument document = e.getDocument();
        doc.setReferenceNo(document.getReferenceNo());
        doc.setDescription(document.getDescription());
        coreTransformer.toFlowdata(document, doc);
        coreTransformer.toMetadata(e, vo);
        return vo;
    }


    public List<Notification> toNotificationVos(List<DexNotification> e) {
        return e.stream().map(this::toNotificationVo).collect(Collectors.toList());
    }
}
