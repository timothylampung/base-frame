package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.identity.domain.model.DexSupervisor;
import com.assettagging.spotit.identity.domain.model.DexSupervisorImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("supervisorDao")
public class DexSupervisorDaoImpl extends GenericDaoSupport<Long, DexSupervisor> implements DexSupervisorDao {

    public DexSupervisorDaoImpl() { super(DexSupervisorImpl.class); }

    @Override
    public List<DexSupervisor> find(String filter, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public DexSupervisor findByCode(String code) {
        return null;
    }
}
