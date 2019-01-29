package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexLocation")
@Table(name = "DEX_LCTN")


public class DexLocationImpl extends DexMetadata implements DexLocation{

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_LCTN")
    @SequenceGenerator(name = "SQ_DEX_LCTN", sequenceName = "SQ_DEX_LCTN", allocationSize = 1)
    private Long id;

    @Embedded
    private DexMetadata metadata;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;


    @ManyToOne(targetEntity = DexLocationImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private DexLocation parent;

    @NotNull
    @Column(name = "ADDRESS")
    private String Address;

    @NotNull
    @Column(name = "NAME")
    private String Name;

    @Override
    public DexMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }
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

    @Override
    public DexLocation getParent() {
        return parent;
    }

    @Override
    public void setParent(DexLocation parent) {
        this.parent = parent;
    }

    @Override
    public String getAddress() {
        return Address;
    }

    @Override
    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public void setName(String name) {
        Name = name;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexLocation.class;
    }
}
