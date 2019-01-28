package com.assettagging.spotit.workorder.business.service;

import com.assettagging.spotit.common.business.service.CommonServiceImpl;
import com.assettagging.spotit.common.domain.dao.DexGradeCodeDao;
import com.assettagging.spotit.common.domain.dao.DexPositionCodeDao;
import com.assettagging.spotit.security.business.service.SecurityService;
import com.assettagging.spotit.workorder.domain.dao.DexActivityDao;
import com.assettagging.spotit.workorder.domain.dao.DexWorkOrderDao;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Transactional
@Service("workOrderService")
public class WorkOrderServiceImpl implements WorkOrderService{

    private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

    private EntityManager entityManager;
    private SecurityService securityService;
    private DexWorkOrderDao workOrderDao;
    private DexActivityDao activityDao;

    @Autowired
    public WorkOrderServiceImpl(EntityManager entityManager, SecurityService securityService,
                             DexWorkOrderDao workOrderDao, DexActivityDao activityDao) {
        this.entityManager = entityManager;
        this.securityService = securityService;
        this.workOrderDao = workOrderDao;
        this.activityDao = activityDao;
    }

    //====================================================================================================
    // WORK ORDER
    //====================================================================================================


    @Override
    public DexWorkOrder findWorkOrderById(Long id) {
        return workOrderDao.findById(id) ;
    }

    @Override
    public DexWorkOrder findWorkOrderByCode(String code) {
        return workOrderDao.findWorkOrderByCode(code);
    }

    @Override
    public List<DexWorkOrder> findWorkOrders(String filter, Integer offset, Integer limit) {
        return workOrderDao.find(filter, offset, limit);
    }

    @Override
    public Integer countWorkOrder() {
        return workOrderDao.count();
    }

    @Override
    public Integer countWorkOrder(String filter) {
        return workOrderDao.count(filter);

    }

    @Override
    public void saveWorkOrder(DexWorkOrder WorkOrder) {
        workOrderDao.save(WorkOrder, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void updateWorkOrder(DexWorkOrder WorkOrder) {
        workOrderDao.update(WorkOrder, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void removeWorkOrder(DexWorkOrder WorkOrder) {
        workOrderDao.remove(WorkOrder, securityService.getCurrentUser());
        entityManager.flush();


    }


    //====================================================================================================
    // ACTIVITY
    //====================================================================================================
//TODO 2 diff find methods
    @Override
    public DexActivity findActivityById(Long id) {
        return activityDao.findActivityById(id);
    }

    @Override
    public DexActivity findActivityByCode(String code) {
        return activityDao.findActivityByCode(code);
    }

    @Override
    public List<DexActivity> findActivitys(String filter, Integer offset, Integer limit) {
        return activityDao.find(filter,offset,limit);
    }

    @Override
    public Integer countActivity() {
        return activityDao.count();
    }

    @Override
    public Integer countActivity(String filter) {
        return activityDao.count(filter);
    }

    @Override
    public void saveActivity(DexActivity Activity) {

        activityDao.save(Activity, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void updateActivity(DexActivity Activity) {

        activityDao.update(Activity, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void removeActivity(DexActivity Activity) {
        activityDao.remove(Activity, securityService.getCurrentUser());
        entityManager.flush();

    }



}
