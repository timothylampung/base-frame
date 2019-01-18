package com.assettagging.spotit.system.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.assettagging.spotit.core.domain.DexMetadata;

/**
 */
@Entity(name = "DexConfiguration")
@Table(name = "DEX_CNFG")
public class DexConfigurationImpl implements DexConfiguration {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_CNFG")
    @SequenceGenerator(name = "SQ_DEX_CNFG", sequenceName = "SQ_DEX_CNFG", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CONFIG_KEY", nullable = false)
    private String key;

    @Column(name = "CONFIG_VALUE")
    private String value;

    @Column(name = "CONFIG_VALUE_DOUBLE")
    private Double valueDouble;

    @Column(name = "CONFIG_VALUE_LONG")
    private Long valueLong;

    @Column(name = "CONFIG_VALUE_BYTEA")
    private byte[] valueByteArray;

    @Column(name = "DESCRIPTION")
    private String description;

    @Embedded
    private DexMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public byte[] getValueByteArray() {
        return valueByteArray;
    }

    public void setValueByteArray(byte[] valueByteArray) {
        this.valueByteArray = valueByteArray;
    }

    public Double getValueDouble() {
        return valueDouble;
    }

    public void setValueDouble(Double valueDouble) {
        this.valueDouble = valueDouble;
    }

    public Long getValueLong() {
        return valueLong;
    }

    public void setValueLong(Long valueLong) {
        this.valueLong = valueLong;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DexMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexConfiguration.class;
    }

    @Transient
    public Integer getValueAsInteger() {
        if (null != getValue())
            return new Integer(getValue());
        else return 0;
    }

    @Transient
    public Double getValueAsDouble() {
        if (null != getValue())
            return new Double(getValue());
        else return 0d;
    }

    @Transient
    public Long getValueAsLong() {
        if (null != getValue())
            return new Long(getValue());
        else return 0l;
    }

    @Transient
    public BigDecimal getValueAsBigDecimal() {
        if (null != getValue())
            return new BigDecimal(getValue());
        else return BigDecimal.ZERO;
    }

    @Transient
    public Boolean getValueAsBoolean() {
        if (null != getValue())
            return new Boolean(getValue());
        else return Boolean.FALSE;
    }
}
