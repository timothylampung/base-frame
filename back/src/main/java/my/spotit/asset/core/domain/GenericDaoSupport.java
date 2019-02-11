package my.spotit.asset.core.domain;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import my.spotit.asset.identity.domain.model.DexUser;

/**
 * @author canang technologies
 */
public class GenericDaoSupport<K, I> implements GenericDao<K, I>, InitializingBean {

    public static final String WILDCARD = "%";
    private static final Logger LOG = LoggerFactory.getLogger(GenericDaoSupport.class);

    @Autowired(required = true)
    protected EntityManager entityManager;

    private Class<I> interfaceClass;
    private Class entityClass;

    public GenericDaoSupport(Class entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        interfaceClass = (Class<I>) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public I newInstance() {
        try {
            return (I) entityClass.newInstance();
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        }
        return null;
    }

    public I refresh(I i) {
        entityManager.refresh(i);
        return i;
    }

    public I findById(K k) {
        return (I) entityManager.getReference(entityClass, (Serializable) k);
    }

    public List<I> find() {
        Query query = entityManager.createQuery("select a "
                + " from " + entityClass.getName() + " a");
        return (List<I>) query.getResultList();
    }

    public List<I> find(Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select a "
                + " from " + entityClass.getName() + " a");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<I>) query.getResultList();
    }

    @Override
    public Integer count() {
        Query query = entityManager.createQuery("select count(a) "
                + " from " + entityClass.getName() + " a");
        return ((Long) query.getSingleResult()).intValue();
    }

    public void save(I entity, DexUser user) {
        Assert.notNull(user, "User cannot be null");
        Assert.notNull(entity, "Object cannot be null");

        try {
            prepareMetadata(entity, user, true);
            prepareFlowData(entity, user);
            entityManager.persist(entity);
        } catch (HibernateException e) {
            LOG.debug("error occurred", e);
        }
    }

    public void saveOrUpdate(I entity, DexUser user) {
        Assert.notNull(user, "User cannot be null");
        Assert.notNull(entity, "Object cannot be null");

        try {
            prepareMetadata(entity, user, true);
            prepareFlowData(entity, user);
            entityManager.persist(entity);
        } catch (HibernateException e) {
            LOG.debug("error occurred", e);
        }
    }

    public void update(I entity, DexUser user) {
        Assert.notNull(user, "User cannot be null");
        Assert.notNull(entity, "Object cannot be null");

        prepareMetadata(entity, user, true);
        entityManager.merge(entity);
    }

    public void deactivate(I entity, DexUser user) {
        Assert.notNull(user, "User cannot be null");
        Assert.notNull(entity, "Object cannot be null");

        // session
        prepareMetadata(entity, user, false);
        entityManager.merge(entity);
    }

    /**
     * @param entity
     * @param user
     */
    public void remove(I entity, DexUser user) {
        Assert.notNull(user, "User cannot be null");
        Assert.notNull(entity, "Object cannot be null");

        prepareMetadata(entity, user, false);
        entityManager.merge(entity);
    }

    @Override
    public void delete(I entity, DexUser user) {
        Assert.notNull(user, "User cannot be null");
        Assert.notNull(entity, "Object cannot be null");

        entityManager.remove(entity);
    }

    private void prepareMetadata(I i, DexUser user, boolean active) {
        if (i instanceof DexMetaObject) {
            DexMetadata metadata = null;
            if (((DexMetaObject) i).getMetadata() != null)
                metadata = ((DexMetaObject) i).getMetadata();
            else
                metadata = new DexMetadata();
            metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            metadata.setCreatorId(user.getId());
            metadata.setState(active ? DexMetaState.ACTIVE : DexMetaState.INACTIVE);
            ((DexMetaObject) i).setMetadata(metadata);
        }
    }

    private void prepareFlowData(I i, DexUser user) {
        if (i instanceof DexFlowObject) {
            DexFlowdata flowData = null;
            if (((DexFlowObject) i).getFlowdata() != null)
                flowData = ((DexFlowObject) i).getFlowdata();
            else
                flowData = new DexFlowdata();
            flowData.setDraftedDate(new Timestamp(System.currentTimeMillis()));
            flowData.setDrafterId(user.getId());
            flowData.setState(DexFlowState.DRAFTED);
            ((DexFlowObject) i).setFlowdata(flowData);
        }
    }
}
