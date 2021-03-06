package my.spotit.asset.helper;

import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.model.DexGroup;
import my.spotit.asset.identity.domain.model.DexPrincipal;
import my.spotit.asset.identity.domain.model.DexPrincipalRole;
import my.spotit.asset.identity.domain.model.DexPrincipalType;
import my.spotit.asset.identity.domain.model.DexRoleType;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.security.business.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;



@Service
public class IdentityServiceHelper {
    private static final Logger log = LoggerFactory.getLogger(my.spotit.asset.helper.IdentityServiceHelper.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private SecurityService securityService;

    public void changeUser(String username) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

    public DexUser getCurrentUser() {
        return securityService.getCurrentUser();
    }

    public DexUser changeUserByGroup(String groupName) {
        log.debug("Loading user for {}", groupName);
        DexGroup group = identityService.findGroupByName(groupName);
        List<DexPrincipal> members = identityService.findGroupMembers(group);
        for (DexPrincipal member : members) {
            if (member.getPrincipalType().equals(DexPrincipalType.USER)) {
                String username = ((DexUser) member).getUsername();
                log.debug("Changing user to {}", username);
                changeUser(username);
                return (DexUser) member;
            }
        }
        return null;
    }

    public boolean findUserRole(DexUser user, DexRoleType roleType) {
        Set<DexGroup> groups = identityService.findEffectiveGroups(user);
        for (DexGroup group : groups) {
            for (DexPrincipalRole role : group.getRoles()) {
                if (role.getRole().equals(roleType)) return true;
            }
        }
        return false;

    }


}

