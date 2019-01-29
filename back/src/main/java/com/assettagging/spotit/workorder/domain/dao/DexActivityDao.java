package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface DexActivityDao extends GenericDao<Long, DexActivity> {

    DexActivity findActivityByCode(String code);

    DexActivity findActivityById(Long id);

    List<DexActivity> findActivities();

    void addActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    void updateActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    void deleteActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user);

    List<DexActivity> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
