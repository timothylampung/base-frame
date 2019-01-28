package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.common.domain.model.DexPositionCode;
import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface DexWorkOrderDao extends GenericDao<Long, DexWorkOrder> {


    List<DexWorkOrder> findAllWorkOrders();
    DexWorkOrder findWorkOrderByCode(String code);
    List<DexWorkOrder> find(String filter, Integer offset, Integer limit);


    //HELPER

    Integer count(String filter);



}
