package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.identity.domain.model.DexStaff;
import my.spotit.asset.identity.domain.model.DexStaffImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 *
 */
@Repository("staffDao")
public class DexStaffDaoImpl extends GenericDaoSupport<Long, DexStaff> implements DexStaffDao {
    private static final Logger LOG = LoggerFactory.getLogger(DexStaffDaoImpl.class);

//    @Autowired
//    private DexActorDao actorDao;

    public DexStaffDaoImpl() {
        super(DexStaffImpl.class);
    }

    @Override
    public DexStaff findByCode(String code) {
        Query query = entityManager.createQuery("select u from DexStaff u where " +
                "u.code = :code ");
        query.setParameter("code", code);
        return (DexStaff) query.getSingleResult();
    }

//    @Override
//    public DexStaff findByNo(String identityNo) {
//        Query query = entityManager.createQuery("select u from DexStaff u where " +
//                u.identityNo = :identityNo ");
//        query.setParameter("identityNo", identityNo);
//        return (DexStaff) query.getSingleResult();
//    }

//    @Override
//    public DexStaff findByNRICNo(String nricNo) {
//        Query query = entityManager.createQuery("select u from DexStaff u where " +
//                "u. = :nricNo ");
//        query.setParameter("nricNo", nricNo);
//        return (DexStaff) query.getSingleResult();
//    }

    @Override
    public DexStaff findStaffByIdentityNo(String identityNo) {
        Query query = entityManager.createQuery("select u from DexStaff u where " +
                "u.identityNo = :identityNo ");
        query.setParameter("identityNo", identityNo);
        return (DexStaff) query.getSingleResult();
    }

    @Override
    public DexStaff findStaffByCode(String code) {
        Query query = entityManager.createQuery("select u from DexStaff u where " +
                "u.code = :code ");
        query.setParameter("code", code);
        return (DexStaff) query.getSingleResult();
    }

    @Override
    public boolean isEmailExists(String email) {
        Query query = entityManager.createQuery("select count(u) from DexUser u where " +
                "upper(u.email) = upper(:email) ");
        query.setParameter("email", email);
        return 0 < ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public boolean isExists(String name) {
        Query query = entityManager.createQuery("select count(u) from DexUser u where " +
                "upper(u.name) = upper(:name) ");
        query.setParameter("name", name);
        return 0 < ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public List<DexStaff> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select v from DexStaff v where " +
                "(upper(v.name) like upper(:filter)" +
                "or upper(v.code) like upper(:filter))" +
                "order by v.name");
        query.setParameter("filter", WILDCARD+filter +WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexStaff>) query.getResultList();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(v) from DexStaff v where " +
                "(upper(v.name) like upper(:filter)" +
                "or upper(v.code) like upper(:filter))");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.getSingleResult()).intValue();
    }
}