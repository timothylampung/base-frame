package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.identity.domain.model.DexTeam;
import my.spotit.asset.identity.domain.model.DexTeamImpl;
import org.springframework.stereotype.Repository;

@Repository("DexTeamDaoImpl")
public class DexTeamDaoImpl extends GenericDaoSupport<Long, DexTeam> implements DexTeamDao {
    public DexTeamDaoImpl() {
        super(DexTeamImpl.class);
    }
}
