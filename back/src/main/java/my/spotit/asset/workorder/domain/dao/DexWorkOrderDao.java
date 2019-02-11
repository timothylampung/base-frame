package my.spotit.asset.workorder.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface DexWorkOrderDao extends GenericDao<Long, DexWorkOrder> {


    List<DexWorkOrder> findAllWorkOrders();
    DexWorkOrder findWorkOrderByCode(String code);
    List<DexWorkOrder> find(String filter, Integer offset, Integer limit);


    //HELPER

    Integer count(String filter);



}
