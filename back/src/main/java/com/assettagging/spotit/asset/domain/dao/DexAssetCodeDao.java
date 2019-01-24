package com.assettagging.spotit.asset.domain.dao;

import com.assettagging.spotit.asset.domain.model.DexAssetCode;
import com.assettagging.spotit.core.domain.GenericDao;

import java.util.List;

public interface DexAssetCodeDao extends GenericDao <Long, DexAssetCode> {


  DexAssetCode findAssetCodeByCode(String code);

    List<DexAssetCode> findAllAssetCodes();

}
