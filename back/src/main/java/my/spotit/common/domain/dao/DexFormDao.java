package my.spotit.common.domain.dao;

import my.spotit.core.domain.GenericDao;
import my.spotit.common.domain.model.DexForm;

import java.util.List;

public interface DexFormDao extends GenericDao<Long, DexForm> {

    //getListOfForms
    List<DexForm> findAllForms();
    //getSingleListByCode
    
    DexForm findFormByCode(String code);


}
