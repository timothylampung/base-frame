package my.spotit.asset.workorder.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface DexActivityDao extends GenericDao<Long, DexActivity> {

    DexActivity findActivityByCode(String code);

    DexActivity findActivityById(Long id);

    List<DexActivity> findActivities();

    void addActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    void updateActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    void deleteActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    List<DexActivity> find(String filter, DexWorkOrder workOrder, Integer offset, Integer limit);

    Integer count(String filter);
}