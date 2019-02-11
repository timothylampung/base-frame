package my.spotit.asset.common.domain.model;


import my.spotit.asset.core.domain.DexMetaObject;

public interface DexGradeCode extends DexMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);
}
