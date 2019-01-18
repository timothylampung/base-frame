package com.assettagging.spotit.common.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.common.domain.model.DexBank;

import java.util.List;

public interface DexBankDao extends GenericDao<Long, DexBank> {

    DexBank findBankByCode(String code);
    List<DexBank> findAllBanks();
    List<DexBank> findBankByBranch(String branch);
}
