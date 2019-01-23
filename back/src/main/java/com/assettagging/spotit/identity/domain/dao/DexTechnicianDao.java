package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.identity.domain.model.DexStaff;
import com.assettagging.spotit.identity.domain.model.DexTechnician;

import java.util.List;

public interface DexTechnicianDao extends GenericDao<Long, DexTechnician> {

    List<DexTechnician> find(String filter, Integer offset, Integer limit);

    DexTechnician findByCode(String code);

}
