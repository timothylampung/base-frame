package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexFacilityManager;

import java.util.List;

public interface DexFacilityManagerDao extends GenericDao<Long, DexFacilityManager> {

    List<DexFacilityManager> find(String filter, Integer offset, Integer limit);

    DexFacilityManager findFacilityManagerByIdentityNo(String identityNo);

    DexFacilityManager findFacilityManagerByCode(String code);

    Integer count(String filter);
}
