package my.spotit.asset.onboarding.api;


import my.spotit.asset.identity.api.controller.IdentityTransformer;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.onboarding.api.vo.OnBoardingResponse;
import my.spotit.asset.onboarding.api.vo.UserVerification;
import my.spotit.asset.onboarding.bussiness.service.DeviceService;
import my.spotit.asset.onboarding.domain.model.DeviceStatus;
import my.spotit.asset.onboarding.domain.model.DexDevice;
import my.spotit.asset.onboarding.domain.model.DexDeviceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping(path = "/api/onboarding")
public class OnBoardingController {
    private static final Logger LOG = LoggerFactory.getLogger(OnBoardingController.class);


    private IdentityService identityService;
    private IdentityTransformer identityTransformer;
    private UserDetailsService userDetailsService;
    private DeviceService deviceService;

    @Autowired
    public OnBoardingController(IdentityService identityService, IdentityTransformer identityTransformer, UserDetailsService userDetailsService, DeviceService deviceService) {
        this.identityService = identityService;
        this.identityTransformer = identityTransformer;
        this.userDetailsService = userDetailsService;
        this.deviceService = deviceService;
    }

    @PostMapping(path = "/verify-account")
    public ResponseEntity<OnBoardingResponse> verifyAccount(@RequestBody UserVerification userVerification) {
        UserDetails userDetail = userDetailsService.loadUserByUsername(userVerification.getUsername());
        if (userDetail == null) {
            LOG.debug("Checking username");
            return ResponseEntity.ok(new OnBoardingResponse(false, "Please check your password and username"));
        } else if (!userDetail.getPassword().equals(userVerification.getPassword())) {
            LOG.debug("Checking Password");
            return ResponseEntity.ok(new OnBoardingResponse(false, "Please check your password and username"));
        } else {

            if (!deviceService.deviceExist(userVerification.getDeviceId())) {
                LOG.debug("Registering New Device");
                DexDevice device = new DexDeviceImpl();
                device.setDeviceId(userVerification.getDeviceId());
                device.setDeviceName(userVerification.getDeviceName());
                device.setUser(identityService.findUserByUsername(userDetail.getUsername()));
                device.setDeviceStatus(DeviceStatus.VERIFIED);
                deviceService.registerNewDevice(device);
                return ResponseEntity.ok(new OnBoardingResponse(true, "on boarded"));
            }

            LOG.debug("On Boarded");
            return ResponseEntity.ok(new OnBoardingResponse(true, "on boarded"));
        }

    }

}
