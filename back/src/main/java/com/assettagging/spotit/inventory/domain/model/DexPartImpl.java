package com.assettagging.spotit.inventory.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexPart")
@Table(name = "DEX_PART")

public class DexPartImpl implements DexPart{

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_PART")
    @SequenceGenerator(name = "SQ_DEX_PART", sequenceName = "SQ_DEX_PART", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "PART", unique = true, nullable = false)
    private String part;

    @NotNull
    @Column(name = "DESCRIPTION", unique = true, nullable = false)
    private String description;
}
