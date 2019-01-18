package com.assettagging.spotit.security.business.integration;

import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import com.assettagging.spotit.identity.domain.model.DexPrincipalRole;
import com.assettagging.spotit.identity.domain.model.DexUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("userDetailService")
@Transactional
public class DexUserDetailService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(DexUserDetailService.class);

    @Autowired
    private DexUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        DexUser user = userDao.findByUsername(username);
        LOG.debug("loading user: {} ", username);

        if (user == null)
            throw new UsernameNotFoundException("No such user");

        List<GrantedAuthority> authorityList = loadGrantedAuthoritiesFor(user);
        return new DexUserDetails(user, authorityList);
    }

    private List<GrantedAuthority> loadGrantedAuthoritiesFor(DexUser user) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Set<DexPrincipalRole> roles = user.getRoles();
        for (DexPrincipalRole role : roles) {
            LOG.debug("role; " + role.getRole().name());
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        return grantedAuthorities;
    }
}
