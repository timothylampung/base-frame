package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexTechnician;

import java.util.List;

public interface DexTechnicianDao extends GenericDao<Long, DexTechnician> {

    List<DexTechnician> find(String filter, Integer offset, Integer limit);

    DexTechnician findTechnicianByIdentityNo(String identityNo);

    DexTechnician findTechnicianByCode(String code);

    Integer count(String filter);
}
