package com.assettagging.spotit.maintenance.domain.dao;

import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.common.domain.model.DexGradeCode;
import com.assettagging.spotit.core.domain.DexMetaState;
import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequestImpl;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
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
    public List<DexMaintenanceRequest> findAllMaintenanceRequest() {
        Query q = entityManager.createQuery("select e from DexMaintenanceRequest e");
        return q.getResultList();
    }

    @Override
    public void addMaintenanceRequest(DexMaintenanceRequest maintenanceRequest, DexLocation location, DexActor requester, DexUser user) {
        maintenanceRequest.setLocation(location);
        maintenanceRequest.setRequester(requester);
        save(maintenanceRequest, user);
        entityManager.flush();
    }


    @Override
    public DexMaintenanceRequest findByCode(String code) {
        Query query = entityManager.createQuery("select e from DexMaintenanceRequest e where e.referenceNo  =:code and  " +
                " e.metadata.state = :state");
        query.setParameter("code", code);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (DexMaintenanceRequest) query.getSingleResult();


    }

    @Override
    public List<DexMaintenanceRequest> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexMaintenanceRequest s where " +
                "(upper(s.referenceNo) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexMaintenanceRequest>) query.getResultList();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexMaintenanceRequest s where " +
                "(upper(s.referenceNo) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }


}
