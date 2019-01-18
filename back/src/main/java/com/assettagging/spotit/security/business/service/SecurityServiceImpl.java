package com.assettagging.spotit.security.business.service;

import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import com.assettagging.spotit.identity.domain.model.DexUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @author canang technologies
 */
@Transactional
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityServiceImpl.class);

    private EntityManager entityManager;
    private ApplicationContext applicationContext;
    private DexUserDao userDao;

    @Autowired
    public SecurityServiceImpl(EntityManager entityManager, ApplicationContext applicationContext,
                               DexUserDao userDao) {
        this.entityManager = entityManager;
        this.applicationContext = applicationContext;
        this.userDao = userDao;
    }

    @Override
    public DexUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return userDao.findByUsername(((UserDetails) auth.getPrincipal()).getUsername());
        } else if (auth.getPrincipal() instanceof User) {
            return userDao.findByUsername(((User) auth.getPrincipal()).getUsername());
        } else if (auth.getPrincipal() instanceof String) {
            return userDao.findByUsername((String) auth.getPrincipal());
        } else {
            return null;
        }
    }
}
