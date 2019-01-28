package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.identity.domain.model.DexStaff;
import com.assettagging.spotit.identity.domain.model.DexSupervisor;

import java.util.List;

public interface DexSupervisorDao extends GenericDao<Long, DexSupervisor> {

    List<DexSupervisor> find(String filter, Integer offset, Integer limit);

    DexSupervisor findByCode(String code);

    DexSupervisor findSupervisorByIdentityNo(String identityNo);

    DexSupervisor findSupervisorByCode(String code);

    Integer count(String filter);
}
