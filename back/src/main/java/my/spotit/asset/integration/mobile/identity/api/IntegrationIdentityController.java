package my.spotit.asset.integration.mobile.identity.api;

import my.spotit.asset.identity.api.controller.IdentityTransformer;
import my.spotit.asset.identity.api.vo.Actor;
import my.spotit.asset.identity.business.service.ActorService;
import my.spotit.asset.integration.mobile.security.MobileSecurityServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/mobile/identity")
public class IntegrationIdentityController {

    private ActorService actorService;
    private IdentityTransformer identityTransformer;
    private MobileSecurityServiceImpl securityService;

    public IntegrationIdentityController(ActorService actorService,
                                         IdentityTransformer identityTransformer,
                                         MobileSecurityServiceImpl securityService) {
        this.actorService = actorService;
        this.identityTransformer = identityTransformer;
        this.securityService = securityService;
    }

    @GetMapping(value = "/actors")
    public ResponseEntity<List<Actor>> findAllActors(String username, String deviceId) throws Exception {
        securityService.authenticate(username, deviceId);
        return ResponseEntity.ok(identityTransformer.toActors(actorService.findAllActors()));
    }



}
