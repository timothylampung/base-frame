package my.spotit.asset.notification.domain.model;


import my.spotit.asset.core.domain.DexDocument;
import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;

@Table(name = "DEX_NOTI")
@Entity(name = "DexNotification")
public class DexNotificationImpl implements DexNotification {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_NOTI")
    @SequenceGenerator(name = "SQ_DEX_NOTI", sequenceName = "SQ_DEX_NOTI", allocationSize = 1)
    private Long id;
    @Column(name = "RECIEVER_CODE")
    private String recieverEmail;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "CONTEXT")
    private DexNotificationContext context;
    @Column(name = "DOCUMENT")
    private DexDocument document;

    @Embedded
    private DexMetadata metadata;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getRecieverEmail() {
        return recieverEmail;
    }

    @Override
    public void setRecieverEmail(String recieverCode) {
        this.recieverEmail = recieverCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public DexNotificationContext getContext() {
        return context;
    }

    @Override
    public void setContext(DexNotificationContext context) {
        this.context = context;
    }

    @Override
    public DexDocument getDocument() {
        return document;
    }

    @Override
    public void setDocument(DexDocument document) {
        this.document = document;
    }

    @Override
    public DexMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexNotification.class;
    }
}
