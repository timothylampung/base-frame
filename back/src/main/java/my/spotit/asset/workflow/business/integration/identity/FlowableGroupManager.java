package my.spotit.asset.workflow.business.integration.identity;

import org.flowable.idm.api.Group;
import org.flowable.idm.api.GroupQuery;
import org.flowable.idm.engine.impl.GroupQueryImpl;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntity;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import my.spotit.asset.identity.domain.dao.DexGroupDao;
import my.spotit.asset.identity.domain.dao.DexUserDao;
import my.spotit.asset.identity.domain.model.DexGroup;
import my.spotit.asset.identity.domain.model.DexUser;

@Component
public class FlowableGroupManager implements GroupEntityManager {

    private static final Logger LOG = LoggerFactory.getLogger(FlowableGroupManager.class);

    @Autowired
    private DexGroupDao groupDao;

    @Autowired
    private DexUserDao userDao;

    @Override
    public Group createNewGroup(String groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupQuery createNewGroupQuery() {
        return new GroupQueryImpl();
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        throw new UnsupportedOperationException();
    }

    private GroupEntity createGroup(DexGroup name) {
        return new FlowableGroup(name);
    }

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query) {
        String userId = query.getUserId();
        if (userId != null) {
            List<Group> result = new ArrayList<>();

            DexUser user = userDao.findByUsername(userId);
            List<DexGroup> memberships = groupDao.findMemberships(user);
            for (DexGroup membership : memberships) {
                result.add(createGroup(membership));
            }

            return result;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNewGroup(Group group) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByPrivilegeId(String privilegeId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupEntity create() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupEntity findById(String entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(GroupEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(GroupEntity entity, boolean fireCreateEvent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupEntity update(GroupEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupEntity update(GroupEntity entity, boolean fireUpdateEvent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(GroupEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(GroupEntity entity, boolean fireDeleteEvent) {
        throw new UnsupportedOperationException();
    }
}
