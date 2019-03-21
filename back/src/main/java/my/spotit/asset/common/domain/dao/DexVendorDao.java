package my.spotit.asset.common.domain.dao;

import my.spotit.asset.common.domain.model.DexVendor;
import my.spotit.asset.core.domain.GenericDao;

import java.util.List;

public interface DexVendorDao extends GenericDao<Long, DexVendor> {

    DexVendor findVendorByCode(String code);

    List<DexVendor> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
