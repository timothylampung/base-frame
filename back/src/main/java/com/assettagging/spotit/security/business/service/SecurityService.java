package com.assettagging.spotit.security.business.service;


import com.assettagging.spotit.identity.domain.model.DexUser;

/**
 * @author canang technologies
 */
public interface SecurityService {

    DexUser getCurrentUser();
}
