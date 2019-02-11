package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexStaff;

import java.util.List;

/**
 */
public interface DexStaffDao extends GenericDao<Long, DexStaff> {

    List<DexStaff> find(String filter, Integer offset, Integer limit);

    DexStaff findByCode(String code);

//    DexStaff findByNo(String staffNo);
//
//    DexStaff findByNRICNo(String nricNo);

    DexStaff findStaffByIdentityNo(String identityNo);

    DexStaff findStaffByCode(String code);

    Integer count(String filter);

    boolean isEmailExists(String email);

    boolean isExists(String staffNo);

}
