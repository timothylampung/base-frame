package com.assettagging.spotit.inventory.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.inventory.domain.model.DexPart;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface DexPartDao extends GenericDao<Long, DexPart> {
    List<DexPart> findAllParts();
    DexPart findPartByCode(String code);
    List<DexPart> find(String filter, Integer offset, Integer limit);


    //HELPER

    Integer count(String filter);

}
