package my.spotit.asset.common.domain.dao;

import my.spotit.asset.core.domain.GenericDaoSupport;
import org.springframework.stereotype.Repository;
import my.spotit.asset.common.domain.model.DexForm;

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
