package com.assettagging.spotit.inventory.domain.dao;

import com.assettagging.spotit.common.domain.model.DexBankImpl;
import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.inventory.domain.model.DexComponent;
import com.assettagging.spotit.inventory.domain.model.DexComponentImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("ComponentDao")
public class DexComponentDaoImpl extends GenericDaoSupport<Long, DexComponent> implements DexComponentDao{

    private static final Logger LOG = LoggerFactory.getLogger(DexComponentImpl.class);

    public DexComponentDaoImpl() { super(DexComponentImpl.class); }

    @Override
    public List<DexComponent> findAllComponent() {
        Query q = entityManager.createQuery("select e from DexComponent e ");
        return q.getResultList();
    }
}
