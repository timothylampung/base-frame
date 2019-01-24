package com.assettagging.spotit.inventory.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.inventory.domain.model.DexPartCode;
import com.assettagging.spotit.inventory.domain.model.DexPartCodeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("PartCodeDao")
public class DexPartCodeDaoImpl extends GenericDaoSupport<Long, DexPartCode> implements DexPartCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexPartCodeImpl.class);

    public DexPartCodeDaoImpl() { super(DexPartCodeImpl.class); }

    @Override
    public List<DexPartCode> findAllPartCode() {
        Query q = entityManager.createQuery("select e from DexPartCode e");
        return q.getResultList();
    }
}
