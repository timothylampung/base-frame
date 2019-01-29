package com.assettagging.spotit.inventory.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.inventory.domain.model.DexComponent;
import com.assettagging.spotit.inventory.domain.model.DexPart;
import com.assettagging.spotit.inventory.domain.model.DexPartCode;

import java.util.List;

public interface DexComponentDao extends GenericDao<Long, DexComponent> {

    List<DexComponent> findAllComponents();
    DexComponent findComponentByCode(String code);
    List<DexComponent> find(String filter, Integer offset, Integer limit);


    //HELPER

    Integer count(String filter);
}
