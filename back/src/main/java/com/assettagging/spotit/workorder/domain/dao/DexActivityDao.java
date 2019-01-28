package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.common.domain.model.DexGradeCode;
import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface DexActivityDao extends GenericDao<Long, DexActivity> {

    DexActivity findActivityByCode(String code);
    DexActivity findActivityById(Long id);
    List<DexActivity> findAllActivitys();
    List<DexActivity> find(String filter, Integer offset, Integer limit);





    void saveActivity(DexWorkOrder workOrder , DexActivity activity, DexUser user);

//HELPER

    Integer count(String filter);





}

