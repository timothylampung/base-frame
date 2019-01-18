package com.assettagging.spotit.security.business.integration;

import com.assettagging.spotit.identity.domain.dao.DexPrincipalDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 */
@Service("autoLoginAuthenticationProvider")
public class DexAutoLoginAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DexAutoLoginAuthenticationProvider.class);

    @Autowired
    private DexPrincipalDao principalDao;

    @Autowired
    @Qualifier("userDetailService")
    private UserDetailsService userDetailService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        UserDetails userDetail = userDetailService.loadUserByUsername(username);
        if (null == userDetail)
            throw new BadCredentialsException("Bad credentials");
        return new DexAutoLoginToken(userDetail, null, userDetail.getAuthorities());
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (DexAutoLoginToken.class.isAssignableFrom(authentication));
    }
}

