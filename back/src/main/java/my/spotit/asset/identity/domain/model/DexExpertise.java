package my.spotit.asset.identity.domain.model;


import my.spotit.asset.core.domain.DexMetaObject;

/**
 * @author canang technologies
 */
public interface DexExpertise extends DexMetaObject {

    DexGroup getGroup();

    void setGroup(DexGroup group);

    DexStaff getStaff();

    void setStaff(DexStaff staff);
}
