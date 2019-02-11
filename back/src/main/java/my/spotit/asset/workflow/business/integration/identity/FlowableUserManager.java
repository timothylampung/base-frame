package my.spotit.asset.workflow.business.integration.identity;

import org.flowable.idm.api.PasswordEncoder;
import org.flowable.idm.api.PasswordSalt;
import org.flowable.idm.api.Picture;
import org.flowable.idm.api.User;
import org.flowable.idm.api.UserQuery;
import org.flowable.idm.engine.impl.UserQueryImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntity;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import my.spotit.asset.identity.domain.dao.DexGroupDao;
import my.spotit.asset.identity.domain.dao.DexUserDao;

@Component
public class FlowableUserManager implements UserEntityManager {

    private static final Logger LOG = LoggerFactory.getLogger(FlowableUserManager.class);

    @Autowired
    private DexGroupDao groupDao;

    @Autowired
    private DexUserDao userDao;

    @Autowired
    private FlowableUserQuery userQuery;

    @Override
    public User createNewUser(String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity findById(String entityId) {
        throw new UnsupportedOperationException();
    }

//    @Override
//    public List<Group> findGroupsByUser(String userId) {
//        LOG.debug("username:" + userId);
//        DexUser byUsername = userDao.findByUsername(userId);
//        List<DexGroup> principalGroups = groupDao.findMemberships(byUsername);
//        List<Group> groups = new ArrayList<Group>();
//        for (DexGroup group : principalGroups) {
//            LOG.debug("group:" + group);
//            FlowableGroup g = new FlowableGroup(group);
//            groups.add(g);
//        }
//        return groups;
//    }

    @Override
    public UserQuery createNewUserQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl query) {
//        if (query.getId() != null) {
//            List<User> result = new ArrayList<User>();
//            result.add(findUserById(query.getId()));
//            return result;
//        } else {
//            throw new UnsupportedOperationException();
//        }
        throw new UnsupportedOperationException();
    }


    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(User updatedUser) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean checkPassword(String userId, String password, PasswordEncoder passwordEncoder, PasswordSalt passwordSalt) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNewUser(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Picture getUserPicture(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setUserPicture(User user, Picture picture) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void deletePicture(User user) {
        throw new UnsupportedOperationException();

    }

    @Override
    public List<User> findUsersByPrivilegeId(String privilegeId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity create() {
        throw new UnsupportedOperationException();
    }


    @Override
    public void insert(UserEntity entity) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void insert(UserEntity entity, boolean fireCreateEvent) {
        throw new UnsupportedOperationException();

    }

    @Override
    public UserEntity update(UserEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity update(UserEntity entity, boolean fireUpdateEvent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(UserEntity entity) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void delete(UserEntity entity, boolean fireDeleteEvent) {
        throw new UnsupportedOperationException();

    }
}
