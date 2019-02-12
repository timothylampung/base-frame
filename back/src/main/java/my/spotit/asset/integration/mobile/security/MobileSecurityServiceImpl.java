package my.spotit.asset.integration.mobile.security;

import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.onboarding.bussiness.service.DeviceService;
import my.spotit.asset.onboarding.domain.model.DexDevice;
import my.spotit.asset.security.business.service.SecurityService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mobileSecurity")
public class MobileSecurityServiceImpl implements MobileSecurityService {

    private AuthenticationManager authenticationManager;
    private IdentityService identityService;
    private DeviceService deviceService;
    private SecurityService securityService;

    public MobileSecurityServiceImpl(AuthenticationManager authenticationManager, IdentityService identityService,
                                     DeviceService deviceService, SecurityService securityService) {
        this.authenticationManager = authenticationManager;
        this.identityService = identityService;
        this.deviceService = deviceService;
        this.securityService = securityService;
    }

    @Override
    public DexUser authenticate(String username, String deviceId) throws Exception {
        DexUser user = identityService.findUserByUsername(username);

        if (!deviceService.deviceExist(deviceId, user)) {
            throw new Exception("Not Authorized");
        }

        DexDevice currDevice = deviceService.findDevices(deviceId, user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);

        return user;
    }
}
