package my.spotit.asset.inventory.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.inventory.domain.model.DexComponent;

import java.util.List;

public interface DexComponentDao extends GenericDao<Long, DexComponent> {

    DexComponent findComponentByCode(String code);

    List<DexComponent> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
