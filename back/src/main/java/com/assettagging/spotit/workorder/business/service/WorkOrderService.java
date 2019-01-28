package com.assettagging.spotit.workorder.business.service;

import com.assettagging.spotit.common.domain.model.DexPositionCode;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface WorkOrderService {

    //==============================================================================================
    // WORK ORDER
    //==============================================================================================
    
    DexWorkOrder findWorkOrderById (Long id);

    DexWorkOrder findWorkOrderByCode (String code);

    List<DexWorkOrder> findWorkOrders(String filter, Integer offset, Integer limit);

    Integer countWorkOrder();

    Integer countWorkOrder(String filter);

    void saveWorkOrder(DexWorkOrder WorkOrder);

    void updateWorkOrder(DexWorkOrder WorkOrder);

    void removeWorkOrder(DexWorkOrder WorkOrder);



//==============================================================================================
    // ACTIVITY
    //==============================================================================================




    DexActivity findActivityById (Long id);

    DexActivity findActivityByCode (String code);

    List<DexActivity> findActivitys(String filter, Integer offset, Integer limit);

    Integer countActivity();

    Integer countActivity(String filter);

    void saveActivity(DexActivity Activity);

    void updateActivity(DexActivity Activity);

    void removeActivity(DexActivity Activity);

}
