package my.spotit.asset.common.business.service;

import my.spotit.asset.common.domain.dao.DexGradeCodeDao;
import my.spotit.asset.common.domain.dao.DexPositionCodeDao;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.common.domain.model.DexGradeCode;
import my.spotit.asset.common.domain.model.DexPositionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * @author canang technologies
 */
@Transactional
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

    private EntityManager entityManager;
    private SecurityService securityService;
    private DexPositionCodeDao positionCodeDao;
    private DexGradeCodeDao gradeCodeDao;

    @Autowired
    public CommonServiceImpl(EntityManager entityManager, SecurityService securityService,
                             DexPositionCodeDao positionCodeDao, DexGradeCodeDao gradeCodeDao) {
        this.entityManager = entityManager;
        this.securityService = securityService;
        this.positionCodeDao = positionCodeDao;
        this.gradeCodeDao = gradeCodeDao;
    }


    //====================================================================================================
    // Position CODE
    //====================================================================================================

    @Override
    public DexPositionCode findPositionCodeById(Long id) {
        return positionCodeDao.findById(id);
    }

    @Override
    public DexPositionCode findPositionCodeByCode(String code) {
        return positionCodeDao.findByCode(code);
    }

    @Override
    public List<DexPositionCode> findPositionCodes(String filter, Integer offset, Integer limit) {
        return positionCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countPositionCode() {
        return positionCodeDao.count();
    }

    @Override
    public Integer countPositionCode(String filter) {
        return positionCodeDao.count(filter);
    }

    @Override
    public void savePositionCode(DexPositionCode PositionCode) {
        positionCodeDao.save(PositionCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updatePositionCode(DexPositionCode PositionCode) {
        positionCodeDao.update(PositionCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void removePositionCode(DexPositionCode PositionCode) {
        positionCodeDao.remove(PositionCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    //====================================================================================================
    // GRADE CODE
    //====================================================================================================

    @Override
    public DexGradeCode findGradeCodeById(Long id) {
        return gradeCodeDao.findById(id);
    }

    @Override
    public DexGradeCode findGradeCodeByCode(String code) {
        return gradeCodeDao.findByCode(code);
    }

    @Override
    public List<DexGradeCode> findGradeCodes(String filter, Integer offset, Integer limit) {
        return gradeCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countGradeCode() {
        return gradeCodeDao.count();
    }

    @Override
    public Integer countGradeCode(String filter) {
        return gradeCodeDao.count(filter);
    }

    @Override
    public void saveGradeCode(DexGradeCode GradeCode) {
        gradeCodeDao.save(GradeCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateGradeCode(DexGradeCode GradeCode) {
        gradeCodeDao.update(GradeCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void removeGradeCode(DexGradeCode GradeCode) {
        gradeCodeDao.remove(GradeCode, securityService.getCurrentUser());
        entityManager.flush();
    }
}

