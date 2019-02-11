package my.spotit.asset.system.domain.model;

import java.math.BigDecimal;

import my.spotit.asset.core.domain.DexMetaObject;

/**
 */
public interface DexConfiguration extends DexMetaObject {

    String getKey();

    void setKey(String value);

    String getValue();

    void setValue(String value);

    byte[] getValueByteArray();

    void setValueByteArray(byte[] value);

    String getDescription();

    void setDescription(String description);

    Integer getValueAsInteger();

    Double getValueAsDouble();

    Long getValueAsLong();

    BigDecimal getValueAsBigDecimal();

    Boolean getValueAsBoolean();

}
