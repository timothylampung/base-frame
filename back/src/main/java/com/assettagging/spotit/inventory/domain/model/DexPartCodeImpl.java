package com.assettagging.spotit.inventory.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexPartCode")
@Table(name = "DEX_PART_CODE")

public class DexPartCodeImpl implements DexPartCode {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_PART_CODE")
    @SequenceGenerator(name = "SQ_DEX_PART_CODE", sequenceName = "SQ_DEX_PART_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "PART_CODE", unique = true, nullable = false)
    private String partCode;

    @NotNull
    @Column(name = "DESCRIPTION", unique = true, nullable = false)
    private String description;
}
