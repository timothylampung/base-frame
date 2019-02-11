package my.spotit.common.domain.dao;

import my.spotit.core.domain.GenericDao;
import my.spotit.common.domain.model.DexBank;

import java.util.List;

public interface DexBankDao extends GenericDao<Long, DexBank> {

    DexBank findBankByCode(String code);
    List<DexBank> findAllBanks();
    List<DexBank> findBankByBranch(String branch);
}
