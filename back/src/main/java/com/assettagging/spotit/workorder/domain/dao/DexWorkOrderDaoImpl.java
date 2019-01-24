package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrderImpl;
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



}
