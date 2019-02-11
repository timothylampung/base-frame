package my.spotit.asset.common.domain.dao;

import my.spotit.asset.core.domain.GenericDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import my.spotit.asset.common.domain.model.DexBank;
import my.spotit.asset.common.domain.model.DexBankImpl;

import javax.persistence.Query;
import java.util.List;

@Repository("BankDao")
public class DexBankDaoImpl extends GenericDaoSupport<Long, DexBank> implements DexBankDao {


    private static final Logger LOG = LoggerFactory.getLogger(DexBankImpl.class);

    public DexBankDaoImpl() {
        super(DexBankImpl.class);
    }


    @Override
    public DexBank findBankByCode(String code) {
        Query q = entityManager.createQuery("select e from DexBank e where e.code =:code")
                .setParameter("code",code);
        return (DexBank) q.getSingleResult();
    }

    @Override
    public List<DexBank> findAllBanks() {
        Query q = entityManager.createQuery("select e from DexBank e ");
        return q.getResultList();
    }

    @Override
    public List<DexBank> findBankByBranch(String branch) {
        Query q = entityManager.createQuery("select e from DexBank e where e.branch =:branch")
                .setParameter("branch",branch);
        return (List<DexBank>) q.getResultList();    }

}
