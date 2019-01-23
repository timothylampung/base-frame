package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.identity.domain.model.DexFacilityManager;
import com.assettagging.spotit.identity.domain.model.DexStaffImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("facilityManagerDao")
public class DexFacilityManagerDaoImpl extends GenericDaoSupport<Long, DexFacilityManager> implements DexFacilityManagerDao {

    public DexFacilityManagerDaoImpl() { super(DexStaffImpl.class); }

    @Override
    public List<DexFacilityManager> find(String filter, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public DexFacilityManager findByCode(String code) {
        return null;
    }
}
