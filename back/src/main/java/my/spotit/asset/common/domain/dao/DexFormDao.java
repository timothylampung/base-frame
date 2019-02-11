package my.spotit.asset.common.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.common.domain.model.DexForm;

import java.util.List;

public interface DexFormDao extends GenericDao<Long, DexForm> {

    //getListOfForms
    List<DexForm> findAllForms();
    //getSingleListByCode
    
    DexForm findFormByCode(String code);


}
