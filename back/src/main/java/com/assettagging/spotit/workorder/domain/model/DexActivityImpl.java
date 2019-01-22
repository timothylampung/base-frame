package com.assettagging.spotit.workorder.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexActivity")
@Table(name = "DEX_ATVT")


public class DexActivityImpl implements DexActivity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_ATVT")
    @SequenceGenerator(name = "SQ_DEX_ATVT", sequenceName = "SQ_DEX_ATVT", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;


}
