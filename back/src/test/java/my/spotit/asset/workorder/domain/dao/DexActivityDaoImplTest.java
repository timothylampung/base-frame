package my.spotit.asset.workorder.domain.dao;

import my.spotit.AbstractTest;
import my.spotit.asset.identity.domain.dao.DexUserDao;
import my.spotit.asset.workorder.domain.model.DexActivity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

@Transactional
public class DexActivityDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexWorkOrderDaoImplTest.class);


    @Autowired
    private DexUserDao userDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DexActivityDao activityDao;

//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void addActivity(){
//        DexUser user = userDao.findByUsername("nazifah.rosli");
//
//        DexActivity activity = new DexActivityImpl();
//        activity.setCode("AT02");
//        activity.setDescription("DESC");
//        activity.setWorkOrder(long);
//
//        LOG.debug("----------------------prepared------------------------ {} ",activity.getContactNo() );
//        activityDao.save(activity, user);
//
//        entityManager.flush();
//        DexActivity savedActivity = activityDao.findByCode("CODE");
//        LOG.debug("--------------------saved-------------------------- {} ",savedActivity.getContactNo() );
//
//    }

    @Test
    public void findActivityByCode() {

        DexActivity findActivityByCode = activityDao.findByCode("AT01");
        LOG.debug("TESTING {} ", findActivityByCode.getDescription());

    }

    @Test
    public void findAllActivitys() {

        List<DexActivity> Activitys = activityDao.find();
        for (DexActivity Activity : Activitys) {
            LOG.debug("TEST: " + Activity.getDescription());
        }
    }

}