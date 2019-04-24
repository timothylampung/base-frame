package my.spotit.asset.notification.api;


import my.spotit.asset.core.domain.DexDocument;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.notification.api.vo.Notification;
import my.spotit.asset.notification.api.vo.NotificationContext;
import my.spotit.asset.notification.api.vo.NotificationResult;
import my.spotit.asset.notification.business.NotificationService;
import my.spotit.asset.notification.domain.model.DexNotification;
import my.spotit.asset.notification.domain.model.DexNotificationContext;
import my.spotit.asset.notification.domain.model.DexNotificationImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotificationController {

    private NotificationService notificationService;
    private NotificationTransformer notificationTransformer;
    private AuthenticationManager authenticationManager;
    private IdentityService identityService;


    @Autowired
    public NotificationController(NotificationService notificationService, NotificationTransformer notificationTransformer, AuthenticationManager credentialManager, IdentityService identityService) {
        this.notificationService = notificationService;
        this.notificationTransformer = notificationTransformer;
        this.authenticationManager = credentialManager;
        this.identityService = identityService;
    }

    @MessageMapping("/notification")
    @SendTo("/topic/notify")
    public Notification notify(Notification notification) {

        String email = notification.getSenderEmail();
        DexUser userByEmail = identityService.findUserByEmail(email);
        autoLogin(userByEmail.getUsername(), userByEmail.getPassword());

        DexDocument document = null;
        String referenceNo = null;

        if (notification.getDocument() != null) {
            referenceNo = notification.getDocument().getReferenceNo();
        }


        DexNotification nn = new DexNotificationImpl();
        nn.setContext(DexNotificationContext.get(notification.getContext().ordinal()));
        nn.setDocument(document);
        nn.setMessage(notification.getMessage());
        nn.setRecieverEmail(notification.getMessage());
        notificationService.broadCastNotificatification(nn);
        return notification;
    }

    private void autoLogin(String principal, String credentials) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, credentials);
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

}
