package com.assettagging.spotit.asset.business.service;

import com.assettagging.spotit.asset.domain.dao.DexAssetCodeDao;
import com.assettagging.spotit.asset.domain.dao.DexAssetDao;
import com.assettagging.spotit.asset.domain.dao.DexLocationDao;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.security.business.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Service("assetService")
public class AssetServiceImpl implements AssetService {

    private static final Logger LOG = LoggerFactory.getLogger(com.assettagging.spotit.asset.business.service.AssetServiceImpl.class);

    private EntityManager entityManager;
    private SecurityService securityService;
    private DexAssetCodeDao assetCodeDao;
    private DexAssetDao assetDao;
    private DexLocationDao locationDao;

    @Autowired
    public AssetServiceImpl(EntityManager entityManager, SecurityService securityService,
                            DexAssetCodeDao assetCodeDao, DexAssetDao assetDao, DexLocationDao locationDao) {
        this.entityManager = entityManager;
        this.securityService = securityService;
        this.assetCodeDao = assetCodeDao;
        this.assetDao = assetDao;
        this.locationDao = locationDao;
    }

    //====================================================================================================
    // ASSET CODE
    //====================================================================================================


    @Override
    public List<DexLocation> findAllLocations(String s, int i, int limit) { return locationDao.findAllLocations(); }

    @Override
    public DexLocation findLocationById(Long id) {
        return locationDao.findById(id);
    }

    @Override
    public DexLocation findLocationByCode(String code) {
        return locationDao.findByCode(code);
    }

    @Override
    public List<DexLocation> findLocations(String filter, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public Integer countLocation(String filter) {
        return locationDao.count(filter);
    }

    @Override
    public void saveLocation(DexLocation location) {
        locationDao.save(location, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateLocation(DexLocation location) {
        locationDao.update(location, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void removeLocation(DexLocation location) {
        locationDao.delete(location, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public DexAsset findAssetById(Long id) {
        return assetDao.findById(id);
    }

    @Override
    public DexAsset findAssetByCode(String code) {
        return assetDao.findByCode(code);
    }

    @Override
    public List<DexAsset> findAssetsByLocation(DexLocation location) {
        return assetDao.findByLocation(location);
    }

    @Override
    public List<DexAsset> findAssets(String s, int i, int limit) { return  assetDao.find(); }

    @Override
    public Integer countAsset() {
        return assetDao.count();
    }

    @Override
    public Integer countAsset(String filter) {
        return assetDao.count(filter);
    }


    @Override
    public void saveAsset(DexAsset asset) {
        assetDao.save(asset, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateAsset(DexAsset asset) {
        assetDao.update(asset, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void removeAsset(DexAsset asset) {
        assetDao.remove(asset, securityService.getCurrentUser());
        entityManager.flush();
    }
}
