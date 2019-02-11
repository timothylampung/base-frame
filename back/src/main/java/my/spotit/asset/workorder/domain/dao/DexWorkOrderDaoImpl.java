package my.spotit.asset.workorder.domain.dao;

import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("WorkOrderDao")
public class DexWorkOrderDaoImpl extends GenericDaoSupport<Long, DexWorkOrder> implements DexWorkOrderDao {


    private static final Logger LOG = LoggerFactory.getLogger(DexWorkOrderImpl.class);

    public DexWorkOrderDaoImpl() {
        super(DexWorkOrderImpl.class);
    }


    @Override
    public DexWorkOrder findWorkOrderByCode(String code) {
        Query q = entityManager.createQuery("select e from DexWorkOrder e where e.code =:code")
                .setParameter("code",code);
        return (DexWorkOrder) q.getSingleResult();
    }

    @Override
    public List<DexWorkOrder> findAllWorkOrders() {
        Query q = entityManager.createQuery("select e from DexWorkOrder e ");
        return q.getResultList();

    }

    @Override
    public List<DexWorkOrder> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexWorkOrder s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexWorkOrder>) query.getResultList();
    }


    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexWorkOrder s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }






}
