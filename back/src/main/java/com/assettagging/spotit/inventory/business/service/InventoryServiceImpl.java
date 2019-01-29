package com.assettagging.spotit.inventory.business.service;

import com.assettagging.spotit.inventory.domain.dao.DexComponentDao;
import com.assettagging.spotit.inventory.domain.dao.DexPartCodeDao;
import com.assettagging.spotit.inventory.domain.dao.DexPartDao;
import com.assettagging.spotit.inventory.domain.model.DexComponent;
import com.assettagging.spotit.inventory.domain.model.DexPart;
import com.assettagging.spotit.inventory.domain.model.DexPartCode;
import com.assettagging.spotit.security.business.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Transactional
@Service("InventoryService")
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private EntityManager entityManager;
    private SecurityService securityService;
    private DexComponentDao componentDao;
    private DexPartDao partDao;
    private DexPartCodeDao partCodeDao;

    @Autowired
    public InventoryServiceImpl(EntityManager entityManager, SecurityService securityService,
                                DexComponentDao componentDao, DexPartDao partDao, DexPartCodeDao partCodeDao) {
        this.entityManager = entityManager;
        this.securityService = securityService;
        this.componentDao = componentDao;
        this.partDao = partDao;
        this.partCodeDao = partCodeDao;
    }

    //====================================================================================================
    // COMPONENT
    //====================================================================================================


    @Override
    public List<DexComponent> findAllComponents() {
        return componentDao.findAllComponents();
    }


    @Override
    public DexComponent findComponentById(Long id) {
        return componentDao.findById(id) ;
    }

    @Override
    public DexComponent findComponentByCode(String code) {
        return componentDao.findComponentByCode(code);
    }

    @Override
    public List<DexComponent> findComponents(String filter, Integer offset, Integer limit) {
        return componentDao.find(filter, offset, limit);
    }

    @Override
    public Integer countComponent() {
        return componentDao.count();
    }

    @Override
    public Integer countComponent(String filter) {
        return componentDao.count(filter);

    }

    @Override
    public void saveComponent(DexComponent Component) {
        componentDao.save(Component, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void updateComponent(DexComponent Component) {
        componentDao.update(Component, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void removeComponent(DexComponent Component) {
        componentDao.remove(Component, securityService.getCurrentUser());
        entityManager.flush();


    }


    //====================================================================================================
    // PART
    //====================================================================================================


    @Override
    public List<DexPart> findAllParts() {
        return partDao.findAllParts();
    }

    @Override
    public DexPart findPartByCode(String code) {
        return partDao.findPartByCode(code);
    }

    @Override
    public List<DexPart> findParts(String filter, Integer offset, Integer limit) {
        return partDao.find(filter,offset,limit);
    }

    @Override
    public Integer countPart() {
        return partDao.count();
    }

    @Override
    public Integer countPart(String filter) {
        return partDao.count(filter);
    }

    @Override
    public void savePart(DexPart Part) {

        partDao.save(Part, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void updatePart(DexPart Part) {

        partDao.update(Part, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void removePart(DexPart Part) {
        partDao.remove(Part, securityService.getCurrentUser());
        entityManager.flush();

    }
 //====================================================================================================
    // PARTCODE
    //====================================================================================================
//TODO 2 diff find methods, find which one works

    @Override
    public List<DexPartCode> findAllPartCodes() {
        return partCodeDao.findAllPartCodes();
    }


    @Override
    public DexPartCode findPartCodeByCode(String code) {
        return partCodeDao.findPartCodeByCode(code);
    }

    @Override
    public List<DexPartCode> findPartCodes(String filter, Integer offset, Integer limit) {
        return partCodeDao.find(filter,offset,limit);
    }

    @Override
    public Integer countPartCode() {
        return partCodeDao.count();
    }

    @Override
    public Integer countPartCode(String filter) {
        return partCodeDao.count(filter);
    }

    @Override
    public void savePartCode(DexPartCode PartCode) {

        partCodeDao.save(PartCode, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void updatePartCode(DexPartCode PartCode) {

        partCodeDao.update(PartCode, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void removePartCode(DexPartCode PartCode) {
        partCodeDao.remove(PartCode, securityService.getCurrentUser());
        entityManager.flush();

    }



}
