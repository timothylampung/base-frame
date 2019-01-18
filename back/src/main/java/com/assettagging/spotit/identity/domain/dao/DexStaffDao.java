package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.identity.domain.model.DexStaff;

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
