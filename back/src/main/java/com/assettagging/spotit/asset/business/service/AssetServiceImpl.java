package com.assettagging.spotit.asset.business.service;

import com.assettagging.spotit.asset.domain.dao.DexAssetCodeDao;
import com.assettagging.spotit.asset.domain.dao.DexAssetDao;
import com.assettagging.spotit.asset.domain.dao.DexLocationDao;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetCode;
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
    public DexAssetCode findAssetCodeById(Long id) {
        return assetCodeDao.findById(id);
    }

    @Override
    public DexAssetCode findAssetCodeByCode(String code) {
        return assetCodeDao.findByCode(code);
    }

    @Override
    public List<DexAssetCode> findAssetCodes(String filter, Integer offset, Integer limit) {
        return assetCodeDao.find(offset, limit);
    }

    @Override
    public Integer countAssetCode() {
        return assetCodeDao.count();
    }



    @Override
    public void saveAssetCode(DexAssetCode AssetCode) {
        assetCodeDao.save(AssetCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateAssetCode(DexAssetCode AssetCode) {
        assetCodeDao.update(AssetCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void removeAssetCode(DexAssetCode AssetCode) {
        assetCodeDao.remove(AssetCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public DexLocation findLocationById(Long id) {
        return null;
    }

    @Override
    public DexLocation findLocationByCode(String code) {
        return null;
    }

    @Override
    public List<DexLocation> findLocations(String filter, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public Integer countLocation() {
        return null;
    }

    @Override
    public Integer countLocation(String filter) {
        return null;
    }

    @Override
    public void saveLocation(DexLocation Location) {

    }

    @Override
    public void updateLocation(DexLocation Location) {

    }

    @Override
    public void removeLocation(DexLocation Location) {

    }

    @Override
    public DexAsset findAssetById(Long id) {
        return null;
    }

    @Override
    public DexAsset findAssetByCode(String code) {
        return null;
    }

    @Override
    public List<DexAsset> findAssets(String filter, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public Integer countAsset() {
        return null;
    }

    @Override
    public void saveAsset(DexAsset Asset) {

    }

    @Override
    public void updateAsset(DexAsset Asset) {

    }

    @Override
    public void removeAsset(DexAsset Asset) {

    }
}
