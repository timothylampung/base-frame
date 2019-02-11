package my.spotit.asset.identity.business.service;

import my.spotit.asset.identity.domain.dao.DexActorDao;
import my.spotit.asset.identity.domain.model.DexActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author canang technologies
 */
@Transactional
@Service("DexActorServiceImpl")
public class ActorServiceImpl implements ActorService {

    private DexActorDao dexActorDao;

    @Autowired
    public ActorServiceImpl(DexActorDao dexActorDao) {
        this.dexActorDao = dexActorDao;
    }


    @Override
    public List<DexActor> findAllActors() {
        return dexActorDao.find();
    }

}
