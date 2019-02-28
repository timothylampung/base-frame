package my.spotit.asset.workorder.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.workorder.domain.model.DexWorkOrderActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderComment;
import my.spotit.asset.workorder.domain.model.DexWorkOrderLog;

import java.util.List;

public interface DexWorkOrderDao extends GenericDao<Long, DexWorkOrder> {

    DexWorkOrder findByReferenceNo(String referenceNo);

    DexWorkOrderActivity findActivityById(Long id);

    DexWorkOrderLog findLogById(Long id);

    DexWorkOrderComment findCommentById(Long id);

    DexWorkOrderLog findUnendedLog(DexWorkOrder order);

    List<DexWorkOrder> find(String filter, Integer offset, Integer limit);

    List<DexWorkOrderActivity> findActivities(String filter, DexWorkOrder workOrder, Integer offset, Integer limit);

    List<DexWorkOrderActivity> findActivities(DexWorkOrder order);

    List<DexWorkOrderLog> findLogs(String filter, DexWorkOrder workOrder, Integer offset, Integer limit);

    List<DexWorkOrderLog> findLogs(DexWorkOrder order);

    List<DexWorkOrderComment> findComments(String filter, DexWorkOrder workOrder, Integer offset, Integer limit);

    List<DexWorkOrderComment> findComments(DexWorkOrder order);

    Integer count(String filter);

    Integer countActivity(String filter, DexWorkOrder workOrder);

    Integer countActivity(DexWorkOrder workOrder);

    Integer countLog(String filter, DexWorkOrder workOrder);

    Integer countLog(DexWorkOrder workOrder);

    Integer countComment(DexWorkOrder workOrder);

    boolean hasUnendedLog(DexWorkOrder order);

    void addActivity(DexWorkOrder workOrder, DexWorkOrderActivity activity, DexUser user);

    void updateActivity(DexWorkOrder workOrder, DexWorkOrderActivity activity, DexUser user);

    void deleteActivity(DexWorkOrder workOrder, DexWorkOrderActivity activity, DexUser user);

    void addLog(DexWorkOrder workOrder, DexWorkOrderLog log, DexUser user);

    void updateLog(DexWorkOrder workOrder, DexWorkOrderLog log, DexUser user);

    void deleteLog(DexWorkOrder workOrder, DexWorkOrderLog log, DexUser user);

    void addComment(DexWorkOrder workOrder, DexWorkOrderComment comment, DexUser user);

    void updateComment(DexWorkOrder workOrder, DexWorkOrderComment comment, DexUser user);

    void deleteComment(DexWorkOrder workOrder, DexWorkOrderComment comment, DexUser user);


}
