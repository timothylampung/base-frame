package my.spotit.asset.common.domain.dao;

import my.spotit.asset.common.domain.model.DexFile;
import my.spotit.asset.common.domain.model.DexFileImpl;
import my.spotit.asset.core.domain.GenericDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class DexFileDaoImpl extends GenericDaoSupport<Long, DexFile> implements DexFileDao {
    public DexFileDaoImpl() {
        super(DexFileImpl.class);
    }
}
