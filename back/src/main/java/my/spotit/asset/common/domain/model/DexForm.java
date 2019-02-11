package my.spotit.asset.common.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

public interface DexForm extends DexMetaObject {
    void setId(Long id);

    String getCode();

    void setCode(String code);

    String getRefNo();

    void setRefNo(String refNo);
}
