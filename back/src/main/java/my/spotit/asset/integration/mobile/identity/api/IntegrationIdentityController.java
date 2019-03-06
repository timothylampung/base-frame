package my.spotit.asset.integration.mobile.identity.api;

import my.spotit.asset.identity.api.controller.IdentityTransformer;
import my.spotit.asset.identity.api.vo.User;
import my.spotit.asset.identity.business.service.ActorService;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.integration.mobile.identity.api.vo.MobileStaff;
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
    private IdentityService identityService;

    public IntegrationIdentityController(ActorService actorService,
                                         IdentityTransformer identityTransformer, IdentityService identityService) {
        this.actorService = actorService;
        this.identityTransformer = identityTransformer;
        this.identityService = identityService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<MobileStaff>> findAllStaffs() {
        List<DexUser> users = identityService.findStaffMobile();
        return ResponseEntity.ok(identityTransformer.toMobileUserVos(users));
    }
}
