package com.assettagging.spotit.system.domain.dao;


import java.util.List;

import com.assettagging.spotit.system.domain.model.DexReferenceNo;
import com.assettagging.spotit.core.domain.GenericDao;

/**
 * @author canang technologies
 */
public interface DexReferenceNoDao extends GenericDao<Long, DexReferenceNo> {

    DexReferenceNo findByCode(String code);

    List<DexReferenceNo> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
