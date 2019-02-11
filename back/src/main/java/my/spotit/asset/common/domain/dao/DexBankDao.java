package my.spotit.asset.common.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.common.domain.model.DexBank;

import java.util.List;

public interface DexBankDao extends GenericDao<Long, DexBank> {

    DexBank findBankByCode(String code);

    List<DexBank> findBankByBranch(String branch);
}
