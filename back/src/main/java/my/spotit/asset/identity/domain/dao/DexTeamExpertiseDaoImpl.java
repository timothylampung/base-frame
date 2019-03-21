package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.identity.domain.model.DexTeamExpertise;
import my.spotit.asset.identity.domain.model.DexTeamExpertiseImpl;
import org.springframework.stereotype.Repository;

@Repository("DexTeamExpertiseDaoImpl")
public class DexTeamExpertiseDaoImpl extends GenericDaoSupport<Long, DexTeamExpertise> implements DexTeamExpertiseDao {
    public DexTeamExpertiseDaoImpl() {
        super(DexTeamExpertiseImpl.class);
    }
}
