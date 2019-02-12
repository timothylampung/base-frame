package my.spotit.asset.integration.mobile.security;

import my.spotit.asset.identity.domain.model.DexUser;

public interface MobileSecurityService {
    DexUser authenticate(String username, String deviceId) throws Exception;
}
