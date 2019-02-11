package my.spotit.asset.security.business.service;


import my.spotit.asset.identity.domain.model.DexUser;

/**
 * @author canang technologies
 */
public interface SecurityService {

    DexUser getCurrentUser();
}
