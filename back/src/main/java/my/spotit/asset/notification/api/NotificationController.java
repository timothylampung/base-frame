package my.spotit.asset.notification.api;


import my.spotit.asset.core.domain.DexDocument;
import my.spotit.asset.integration.mobile.asset.api.NotificationTransformer;
import my.spotit.asset.maintenance.business.service.MaintenanceRequestService;
import my.spotit.asset.notification.api.vo.Notification;
import my.spotit.asset.notification.api.vo.NotificationContext;
import my.spotit.asset.notification.api.vo.NotificationResult;
import my.spotit.asset.notification.business.NotificationService;
import my.spotit.asset.notification.domain.model.DexNotification;
import my.spotit.asset.notification.domain.model.DexNotificationContext;
import my.spotit.asset.notification.domain.model.DexNotificationImpl;
import my.spotit.asset.workorder.business.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotificationController {

    private WorkOrderService workOrderService;
    private MaintenanceRequestService maintenanceRequestService;
    private NotificationService notificationService;
    private NotificationTransformer notificationTransformer;

    @Autowired
    public NotificationController(WorkOrderService workOrderService, MaintenanceRequestService maintenanceRequestService, NotificationService notificationService, NotificationTransformer notificationTransformer) {
        this.workOrderService = workOrderService;
        this.maintenanceRequestService = maintenanceRequestService;
        this.notificationService = notificationService;
        this.notificationTransformer = notificationTransformer;
    }

    @MessageMapping("/send")
    @SendTo("/topic")
    public Notification notify(Notification notification) {
        DexDocument document = null;
        if (notification.getContext() == NotificationContext.MAINTENANCE_REQUEST) {
            document = maintenanceRequestService.findMaintenanceRequestByReferenceNo(notification.getDocument().getReferenceNo());
            notification.setMessage("You have new request");
        } else {
            document = workOrderService.findWorkOrderByReferenceNo(notification.getDocument().getReferenceNo());
            notification.setMessage("You have new workorder");
        }
        DexNotification nn = new DexNotificationImpl();
        nn.setContext(DexNotificationContext.get(notification.getContext().ordinal()));
        nn.setDocument(document);
        nn.setMessage(notification.getMessage());
        nn.setRecieverEmail(notification.getMessage());
        notificationService.broadCastNotificatification(nn);
        return notification;
    }


    @GetMapping(value = "/notifications/{email}")
    public ResponseEntity<NotificationResult> findNotificationsByEmail(@PathVariable String email) {
        List<DexNotification> notifications = notificationService.findNotificationsByRecieverEmail(email);
        Integer counts = notificationService.countNotification(email);
        return ResponseEntity.ok(new NotificationResult(counts, notificationTransformer.toNotificationVos(notifications)));
    }

    @GetMapping(value = "/notifications", params = {"id"})
    public void markAsRead(@RequestParam Long id) {
        DexNotification notification = notificationService.findNotificationById(id);
        notificationService.markNotificationAsRead(notification);
    }

}
