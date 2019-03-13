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
import my.spotit.asset.dashboard.domain.model.DexWorkOrderWeeklyTimeSpentProjection;

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

    @Override
    public List<DexWorkOrderWeeklyTimeSpentProjection> findWorkOrderWeeklyTimeSpentProjections() {
        Query query = entityManager.createQuery("select a from DexWorkOrderWeeklyTimeSpentProjection a group by a.week, a.total");
        return query.getResultList();
    }

    @Override
    public Integer countWorkOrder() {
        Query query = entityManager.createQuery("select count(w) from DexWorkOrder w");
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer countStaff() {
        Query query = entityManager.createQuery("select count(w) from DexStaff w");
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer countMaintenanceRequest() {
        Query query = entityManager.createQuery("select count(w) from DexMaintenanceRequest w");
        return ((Long) query.getSingleResult()).intValue();
    }
}
