package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexSupervisor;

import java.util.List;

public interface DexSupervisorDao extends GenericDao<Long, DexSupervisor> {

    List<DexSupervisor> find(String filter, Integer offset, Integer limit);

    DexSupervisor findSupervisorByIdentityNo(String identityNo);

    DexSupervisor findSupervisorByCode(String code);

    Integer count(String filter);
}
