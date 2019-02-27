package my.spotit.asset.workorder.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderLog;

import java.util.List;

public interface DexWorkOrderDao extends GenericDao<Long, DexWorkOrder> {

    DexWorkOrder findByReferenceNo(String referenceNo);

    DexActivity findActivityById(Long id);

    DexWorkOrderLog findUnendedLog(DexWorkOrder order);

    List<DexWorkOrder> find(String filter, Integer offset, Integer limit);

    List<DexActivity> findActivities(String filter, DexWorkOrder workOrder, Integer offset, Integer limit);

    List<DexActivity> findActivities(DexWorkOrder order);

    List<DexWorkOrderLog> findLogs(String filter, DexWorkOrder workOrder, Integer offset, Integer limit);

    List<DexWorkOrderLog> findLogs(DexWorkOrder order);

    Integer count(String filter);

    Integer countActivity(String filter, DexWorkOrder workOrder);

    Integer countActivity(DexWorkOrder workOrder);

    Integer countLog(String filter, DexWorkOrder workOrder);

    Integer countLog(DexWorkOrder workOrder);

    boolean hasUnendedLog(DexWorkOrder order);

    void addActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    void updateActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    void deleteActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    void addLog(DexWorkOrder workOrder, DexWorkOrderLog log, DexUser user);

    void updateLog(DexWorkOrder workOrder, DexWorkOrderLog log, DexUser user);

    void deleteLog(DexWorkOrder workOrder, DexWorkOrderLog log, DexUser user);


}
