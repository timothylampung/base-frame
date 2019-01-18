package com.assettagging.spotit.common.domain.model;

import com.assettagging.spotit.core.domain.DexMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexPositionCode")
@Table(name = "DEX_PSTN_CODE")
public class DexPositionCodeImpl implements DexPositionCode {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_PSTN_CODE")
    @SequenceGenerator(name = "SQ_DEX_PSTN_CODE", sequenceName = "SQ_DEX_PSTN_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    
    @Embedded
    private DexMetadata metadata;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

	@Override
    public DexMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexPositionCode.class;
    }

}
