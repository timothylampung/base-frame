package my.spotit.asset.maintenance.domain.dao;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;

import java.util.List;

public interface DexMaintenanceRequestDao extends GenericDao<Long, DexMaintenanceRequest> {

    DexMaintenanceRequest findByReferenceNo(String referenceNo);

    List<DexMaintenanceRequest> find(String filter, Integer offset, Integer limit);

    List find(DexAsset asset, Integer offset, Integer limit);

    Integer count(String filter);

    Integer count(DexAsset asset);

//    void addItem(DexMaintenanceRequest request, DexMaintenanceRequestItem item, DexUser user);
//    void updateItem(DexMaintenanceRequest request, DexMaintenanceRequestItem item, DexUser user);
//    void deleteItem(DexMaintenanceRequest request, DexMaintenanceRequestItem item, DexUser user);

}
