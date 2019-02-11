package my.spotit.asset.domain.dao;

import my.spotit.asset.domain.model.DexLocation;
import my.spotit.core.domain.GenericDao;

import java.util.List;

public interface DexLocationDao extends GenericDao <Long, DexLocation> {


  DexLocation findByCode(String code);

  Integer count(String filter);

  List<DexLocation> find(String filter, Integer offset, Integer limit);
}
