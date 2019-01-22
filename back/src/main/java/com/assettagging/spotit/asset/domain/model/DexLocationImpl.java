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
}
