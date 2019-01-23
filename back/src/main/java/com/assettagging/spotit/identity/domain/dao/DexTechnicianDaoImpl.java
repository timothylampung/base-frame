package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.identity.domain.model.DexTechnician;
import com.assettagging.spotit.identity.domain.model.DexTechnicianImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("technicianDao")
public class DexTechnicianDaoImpl extends GenericDaoSupport<Long, DexTechnician> implements DexTechnicianDao {

    public DexTechnicianDaoImpl() { super(DexTechnicianImpl.class); }

    @Override
    public List<DexTechnician> find(String filter, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public DexTechnician findByCode(String code) {
        return null;
    }
}
