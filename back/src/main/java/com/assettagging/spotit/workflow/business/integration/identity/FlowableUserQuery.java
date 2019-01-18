package com.assettagging.spotit.workflow.business.integration.identity;

import org.flowable.engine.common.api.query.QueryProperty;
import org.flowable.idm.api.User;
import org.flowable.idm.api.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import com.assettagging.spotit.identity.domain.model.DexUser;

@Component("activitiUserQuery")
public class FlowableUserQuery implements UserQuery {

    private static final Logger LOG = LoggerFactory.getLogger(FlowableUserQuery.class);

    @Autowired
    private DexUserDao userDao;

    @Override
    public UserQuery asc() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery desc() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public long count() {
        return userDao.find(0, 99999).size();
    }

    @Override
    public User singleResult() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public List<User> list() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public List<User> listPage(int firstResult, int maxResults) {
        List<User> actUsers = new ArrayList<>();
        List<DexUser> users = userDao.find(firstResult, maxResults);
        for (DexUser user : users) {
            actUsers.add(new FlowableUser(user));
        }
        return actUsers;
    }

    @Override
    public UserQuery userId(String id) {
        final DexUser user = userDao.findByUsername(id);
        UserQuery aSubUserQuery = new UserQuery() {

            @Override
            public User singleResult() {
                FlowableUser actUser = new FlowableUser(user);
                return actUser;
            }

            @Override
            public List<User> listPage(int firstResult, int maxResults) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public List<User> list() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery desc() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public long count() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery asc() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userLastNameLike(String lastNameLike) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userFullNameLike(String fullNameLike) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userLastName(String lastName) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userId(String id) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userFirstNameLike(String firstNameLike) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userFirstName(String firstName) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userEmailLike(String emailLike) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userEmail(String email) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery orderByUserLastName() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery orderByUserId() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery orderByUserFirstName() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery orderByUserEmail() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery memberOfGroup(String groupId) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userIds(List<String> list) {
                return null;
            }

            @Override
            public UserQuery userIdIgnoreCase(String s) {
                return null;
            }

            @Override
            public UserQuery userFirstNameLikeIgnoreCase(String s) {
                return null;
            }

            @Override
            public UserQuery userLastNameLikeIgnoreCase(String s) {
                return null;
            }

            @Override
            public UserQuery userFullNameLikeIgnoreCase(String s) {
                return null;
            }

            @Override
            public UserQuery memberOfGroups(List<String> list) {
                return null;
            }

            @Override
            public UserQuery orderBy(QueryProperty property) {
                return null;
            }

            @Override
            public UserQuery orderBy(QueryProperty property, NullHandlingOnOrder nullHandlingOnOrder) {
                return null;
            }
        };

        return aSubUserQuery;
    }

    @Override
    public UserQuery userFirstName(String firstName) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userFirstNameLike(String firstNameLike) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userLastName(String lastName) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userLastNameLike(String lastNameLike) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userFullNameLike(String fullNameLike) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userEmail(String email) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userEmailLike(String emailLike) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery memberOfGroup(String groupId) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery orderByUserId() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery orderByUserFirstName() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery orderByUserLastName() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery orderByUserEmail() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userIds(List<String> list) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userIdIgnoreCase(String s) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userFirstNameLikeIgnoreCase(String s) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userLastNameLikeIgnoreCase(String s) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userFullNameLikeIgnoreCase(String s) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery memberOfGroups(List<String> list) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery orderBy(QueryProperty property) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery orderBy(QueryProperty property, NullHandlingOnOrder nullHandlingOnOrder) {
        throw new IllegalArgumentException("Not Impl");
    }
}

