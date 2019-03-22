package my.spotit.asset.identity.domain.model;


import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;

/**
 * @author canang technologies
 */
@Entity(name = "DexGroupMember")
@Table(name = "DEX_EXPT")
public class DexExpertiseImpl implements DexExpertise {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_EXPT")
    @SequenceGenerator(name = "SQ_DEX_EXPT", sequenceName = "SQ_DEX_EXPT", allocationSize = 1)
    private Long id;

    @OneToOne(targetEntity = DexGroupImpl.class)
    @JoinColumn(name = "TEAM_ID")
    private DexGroup group;

    @OneToOne(targetEntity = DexStaffImpl.class)
    @JoinColumn(name = "STAFF_ID")
    private DexStaff staff;

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
    public DexGroup getGroup() {
        return group;
    }

    @Override
    public void setGroup(DexGroup group) {
        this.group = group;
    }

    @Override
    public DexStaff getStaff() {
        return staff;
    }

    @Override
    public void setStaff(DexStaff staff) {
        this.staff = staff;
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
        return DexGroupMember.class;
    }


}
