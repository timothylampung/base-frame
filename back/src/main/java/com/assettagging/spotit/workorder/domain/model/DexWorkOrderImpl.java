package com.assettagging.spotit.workorder.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexWorkOrder")
@Table(name = "DEX_WODR")

public class DexWorkOrderImpl implements DexWorkOrder {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_WODR")
    @SequenceGenerator(name = "SQ_DEX_WODR", sequenceName = "SQ_DEX_WODR", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

}
