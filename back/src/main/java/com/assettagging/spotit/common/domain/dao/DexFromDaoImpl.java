package com.assettagging.spotit.common.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import org.springframework.stereotype.Repository;
import com.assettagging.spotit.common.domain.model.DexForm;

import java.util.List;

@Repository("FormDao")
public class DexFromDaoImpl extends GenericDaoSupport<Long, DexForm> implements DexFormDao {

    public DexFromDaoImpl() {
        super(DexFromDaoImpl.class);
    }

    @Override
    public List<DexForm> findAllForms() {
        return null;
    }

    @Override
    public DexForm findFormByCode(String code) {
        return null;
    }
}
