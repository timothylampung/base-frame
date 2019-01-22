package com.assettagging.spotit.asset.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexAset")
@Table(name = "DEX_ASST")



public class DexAssetImpl implements DexAsset {


    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_ASST")
    @SequenceGenerator(name = "SQ_DEX_ASST", sequenceName = "SQ_DEX_ASST", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

}
