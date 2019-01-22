package com.assettagging.spotit.asset.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexAssetCode")
@Table(name = "DEX_ASST_CODE")
public class AssetCodeImpl implements AssetCode {


    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_ASST_CODE")
    @SequenceGenerator(name = "SQ_DEX_ASST_CODE", sequenceName = "SQ_DEX_ASST_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE")
    private String Code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;
}


