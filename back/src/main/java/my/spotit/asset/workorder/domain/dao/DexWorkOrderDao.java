package my.spotit.asset.workorder.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface DexWorkOrderDao extends GenericDao<Long, DexWorkOrder> {

    DexWorkOrder findByReferenceNo(String referenceNo);

    DexActivity findActivityById(Long id);

    List<DexWorkOrder> find(String filter, Integer offset, Integer limit);

    List<DexActivity> findActivities(String filter, DexWorkOrder workOrder, Integer offset, Integer limit);

    List<DexActivity> findActivities(DexWorkOrder order);

    Integer count(String filter);

    Integer countActivity(String filter, DexWorkOrder workOrder);

    Integer countActivity(DexWorkOrder workOrder);

    void addActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    void updateActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    void deleteActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

}
