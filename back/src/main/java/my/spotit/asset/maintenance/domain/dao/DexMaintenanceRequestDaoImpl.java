package my.spotit.asset.maintenance.domain.dao;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequestImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

import java.util.List;

@Repository("MaintenanceRequestDao")
public class DexMaintenanceRequestDaoImpl extends GenericDaoSupport<Long, DexMaintenanceRequest> implements DexMaintenanceRequestDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexMaintenanceRequestDaoImpl.class);

    public DexMaintenanceRequestDaoImpl() {
        super(DexMaintenanceRequestImpl.class);
    }

    @Override
    public DexMaintenanceRequest findByReferenceNo(String code) {
        Query query = entityManager.createQuery("select r from DexMaintenanceRequest r where r.referenceNo  =:code and  " +
                " r.metadata.state = :state");
        query.setParameter("code", code);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (DexMaintenanceRequest) query.getSingleResult();
    }

    @Override
    public List<DexMaintenanceRequest> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select r from DexMaintenanceRequest r where " +
                "(upper(r.referenceNo) like upper(:filter) " +
                "or upper(r.description) like upper(:filter)) " +
                "and r.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexMaintenanceRequest>) query.getResultList();
    }

    @Override
    public List<DexMaintenanceRequest> find(DexAsset asset, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select r from DexMaintenanceRequest r where " +
                "r.asset = :asset " +
                "and r.metadata.state = :state ");
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setParameter("asset", asset);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexMaintenanceRequest>) query.getResultList();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(r) from DexMaintenanceRequest r where " +
                "(upper(r.referenceNo) like upper(:filter) " +
                "or upper(r.description) like upper(:filter)) " +
                "and r.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer count(DexAsset asset) {
        Query query = entityManager.createQuery("select count(r) from DexMaintenanceRequest r where " +
                "r.asset = :asset " +
                "and r.metadata.state = :state ");
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setParameter("asset", asset);
        return ((Long) query.getSingleResult()).intValue();
    }
}
