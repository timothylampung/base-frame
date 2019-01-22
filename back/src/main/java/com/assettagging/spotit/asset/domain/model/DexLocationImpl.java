package com.assettagging.spotit.asset.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexLocation")
@Table(name = "DEX_LCTN")


public class DexLocationImpl implements DexLocation{

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_LCTN")
    @SequenceGenerator(name = "SQ_DEX_LCTN", sequenceName = "SQ_DEX_LCTN", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
}
