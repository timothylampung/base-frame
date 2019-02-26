package my.spotit.asset.dashboard.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import my.spotit.asset.dashboard.domain.model.DexWorkOrderWeeklyProjection;

/**
 * @author canang technologies
 */
@Transactional
@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService {

    private static final Logger LOG = LoggerFactory.getLogger(DashboardServiceImpl.class);

    private EntityManager entityManager;

    @Autowired
    public DashboardServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DexWorkOrderWeeklyProjection> findWorkOrderWeeklyProjections() {
        Query query = entityManager.createQuery("select a from DexWorkOrderWeeklyProjection a group by a.week");
        return query.getResultList();
    }
}
