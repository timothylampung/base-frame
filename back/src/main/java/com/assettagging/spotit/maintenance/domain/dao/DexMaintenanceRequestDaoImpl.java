package com.assettagging.spotit.maintenance.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequestImpl;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@Repository("MaintenanceRequestDao")
public class DexMaintenanceRequestDaoImpl extends GenericDaoSupport<Long, DexMaintenanceRequest> implements DexMaintenanceRequestDao {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(DexMaintenanceRequestImpl.class);

    public DexMaintenanceRequestDaoImpl() { super(DexMaintenanceRequestImpl.class); }

    public List<DexMaintenanceRequest> findAllMaintenanceRequest() {
        Query q = entityManager.createQuery("select e from DexMaintenanceRequest e");
        return q.getResultList();
    }

}
