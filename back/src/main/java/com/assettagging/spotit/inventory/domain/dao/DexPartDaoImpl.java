package com.assettagging.spotit.inventory.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.inventory.domain.model.DexPart;
import com.assettagging.spotit.inventory.domain.model.DexPartCode;
import com.assettagging.spotit.inventory.domain.model.DexPartImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("PartDao")
public class DexPartDaoImpl extends GenericDaoSupport<Long, DexPart> implements DexPartDao{

    private static final Logger LOG = LoggerFactory.getLogger(DexPartImpl.class);

    public DexPartDaoImpl() { super(DexPartImpl.class); }

    @Override
    public List<DexPart> findAllPart() {
        Query q = entityManager.createQuery("select e from DexPart e");
        return q.getResultList();
    }
}
