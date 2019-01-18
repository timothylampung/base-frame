package com.assettagging.spotit.common.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.common.domain.model.DexForm;

import java.util.List;

public interface DexFormDao extends GenericDao<Long, DexForm> {

    //getListOfForms
    List<DexForm> findAllForms();
    //getSingleListByCode
    
    DexForm findFormByCode(String code);


}
