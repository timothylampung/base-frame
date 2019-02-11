package my.spotit.asset.asset.domain.dao;

import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.core.domain.GenericDao;

import java.util.List;

public interface DexLocationDao extends GenericDao <Long, DexLocation> {


  DexLocation findByCode(String code);

  List<DexLocation> find(String filter, Integer offset, Integer limit);

  Integer count(String filter);
}
