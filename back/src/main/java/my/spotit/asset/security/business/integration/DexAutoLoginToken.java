package my.spotit.asset.security.business.integration;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 */
public class DexAutoLoginToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;

    public DexAutoLoginToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(true);
    }

    public DexAutoLoginToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}