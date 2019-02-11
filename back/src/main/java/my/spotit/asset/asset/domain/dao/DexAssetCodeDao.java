package my.spotit.asset.asset.domain.dao;

import java.util.List;

import my.spotit.asset.asset.domain.model.DexAssetCode;
import my.spotit.asset.core.domain.GenericDao;

public interface DexAssetCodeDao extends GenericDao<Long, DexAssetCode> {

    DexAssetCode findAssetCodeByCode(String code);

    List<DexAssetCode> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
