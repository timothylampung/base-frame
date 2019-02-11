package my.spotit.asset.domain.dao;

import my.spotit.asset.domain.model.DexAssetCode;
import my.spotit.core.domain.GenericDao;

public interface DexAssetCodeDao extends GenericDao<Long, DexAssetCode> {

    DexAssetCode findByCode(String code);

}
