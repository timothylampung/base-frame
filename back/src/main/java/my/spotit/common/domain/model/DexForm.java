package my.spotit.common.domain.model;

import my.spotit.core.domain.DexMetaObject;

public interface DexForm extends DexMetaObject {
    void setId(Long id);

    String getCode();

    void setCode(String code);

    String getRefNo();

    void setRefNo(String refNo);
}
