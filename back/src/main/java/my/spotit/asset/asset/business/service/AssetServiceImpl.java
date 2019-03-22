package my.spotit.asset.asset.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;

import my.spotit.asset.asset.business.parser.AssetParserRegistry;
import my.spotit.asset.asset.business.parser.LocationParserRegistry;
import my.spotit.asset.asset.domain.dao.DexAssetCodeDao;
import my.spotit.asset.asset.domain.dao.DexAssetDao;
import my.spotit.asset.asset.domain.dao.DexLocationDao;
import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexAssetCode;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.business.service.SystemService;
import my.spotit.asset.workflow.business.service.WorkflowService;

@Transactional
@Service("assetService")
public class AssetServiceImpl implements AssetService {

    private static final Logger LOG = LoggerFactory.getLogger(AssetServiceImpl.class);

    private EntityManager entityManager;
    private ApplicationContext applicationContext;
    private SecurityService securityService;
    private SystemService systemService;
    private WorkflowService workflowService;
    private DexAssetCodeDao assetCodeDao;
    private DexAssetDao assetDao;
    private DexLocationDao locationDao;
    private LocationParserRegistry locationParserRegistry;
    private AssetParserRegistry assetParserRegistry;

    @Autowired
    public AssetServiceImpl(EntityManager entityManager, ApplicationContext applicationContext,
                            SecurityService securityService,
                            SystemService systemService, WorkflowService workflowService,
                            LocationParserRegistry locationParserStrategy,
                            AssetParserRegistry assetParserRegistry,
                            DexAssetCodeDao assetCodeDao, DexAssetDao assetDao,
                            DexLocationDao locationDao) {
        this.entityManager = entityManager;
        this.applicationContext = applicationContext;
        this.securityService = securityService;
        this.workflowService = workflowService;
        this.systemService = systemService;
        this.locationParserRegistry = locationParserStrategy;
        this.assetParserRegistry = assetParserRegistry;
        this.assetCodeDao = assetCodeDao;
        this.assetDao = assetDao;
        this.locationDao = locationDao;
    }

    //==============================================================================================
    // ASSET CODE
    //==============================================================================================

    @Override
    public DexAssetCode findAssetCodeById(Long id) {
        return assetCodeDao.findById(id);
    }

    @Override
    public DexAssetCode findAssetCodeByCode(String code) {
        return assetCodeDao.findAssetCodeByCode(code);
    }

    @Override
    public List<DexAssetCode> findAssetCodes(String filter, Integer offset, Integer limit) {
        return assetCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countAssetCode() {
        return assetCodeDao.count();
    }


    @Override
    public Integer countAssetCode(String filter) {
        return assetCodeDao.count(filter);
    }



    @Override
    public void saveAssetCode(DexAssetCode assetCode) {
        assetCodeDao.save(assetCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateAssetCode(DexAssetCode assetCode) {
        assetCodeDao.update(assetCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void removeAssetCode(DexAssetCode assetCode) {
        assetCodeDao.remove(assetCode, securityService.getCurrentUser());
        entityManager.flush();
    }

    //==============================================================================================
    // LOCATION
    //==============================================================================================

    @Override
    public List<DexLocation> findLocations() {
        return locationDao.find();
    }

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
        return locationDao.find(filter, offset, limit);
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
    public void parseLocation(File file) throws Exception {
        try {
            locationParserRegistry.process(file);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    //==============================================================================================
    // ASSET
    //==============================================================================================


    @Override
    public DexAsset findAssetById(Long id) {
        return assetDao.findById(id);
    }

    @Override
    public DexAsset findAssetByCode(String code) {
        return assetDao.findByCode(code);
    }

    @Override
    public List<DexAsset> findAssetsByCategory(String category) {
        return assetDao.findByCategory(category);
    }


    @Override
    public List<DexAsset> findAssetsByLocation(DexLocation location) {
        return assetDao.findByLocation(location);
    }


    @Override
    public List<DexAsset> findAssets(String filter, Integer offset, Integer limit) {
        return assetDao.find(filter, offset, limit);
    }

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

    @Override
    public void parseAsset(File file) throws Exception {
        try {
            assetParserRegistry.process(file);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    // =============================================================================================
    // PRIVATE METHODS
    // =============================================================================================

}
