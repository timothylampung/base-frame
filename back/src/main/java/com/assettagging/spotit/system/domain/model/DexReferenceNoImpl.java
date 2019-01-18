package com.assettagging.spotit.system.domain.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.assettagging.spotit.core.domain.DexMetadata;

/**
 * @author canang technologies
 */
@Entity(name = "DexReferenceNo")
@Table(name = "DEX_RFRN_NO")
public class DexReferenceNoImpl implements DexReferenceNo {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_RFRN_NO")
    @SequenceGenerator(name = "SQ_DEX_RFRN_NO", sequenceName = "SQ_DEX_RFRN_NO", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotNull
    @Column(name = "PREFIX")
    private String prefix;

    @NotNull
    @Column(name = "SEQUENCE_FORMAT")
    private String sequenceFormat;

    @NotNull
    @Column(name = "REFERENCE_FORMAT")
    private String referenceFormat;

    @NotNull
    @Column(name = "INCREMENT_VALUE")
    private Integer incrementValue;

    @NotNull
    @Column(name = "CURRENT_VALUE")
    private Integer currentValue;

    @Embedded
    private DexMetadata metadata;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSequenceFormat() {
        return sequenceFormat;
    }

    public void setSequenceFormat(String sequenceFormat) {
        this.sequenceFormat = sequenceFormat;
    }

    public String getReferenceFormat() {
        return referenceFormat;
    }

    public void setReferenceFormat(String referenceFormat) {
        this.referenceFormat = referenceFormat;
    }

    public Integer getIncrementValue() {
        return incrementValue;
    }

    public void setIncrementValue(Integer incrementValue) {
        this.incrementValue = incrementValue;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public DexMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexReferenceNo.class;
    }
}

