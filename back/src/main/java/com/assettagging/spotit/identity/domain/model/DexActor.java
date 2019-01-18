package com.assettagging.spotit.identity.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;

/**
 * @author canang technologies
 */
public interface DexActor extends DexMetaObject {

    String getCode();

    void setCode(String code);

    String getIdentityNo();

    void setIdentityNo(String identityNo);

    String getName();

    void setName(String name);

    String getFax();

    void setFax(String fax);

    String getEmail();

    void setEmail(String email);

    String getMobile();

    void setMobile(String mobile);

    String getPhone();

    void setPhone(String phone);

    String getAddress1();

    void setAddress1(String address1);

    String getAddress2();

    void setAddress2(String address2);

    String getAddress3();

    void setAddress3(String address3);

    String getPostcode();

    void setPostcode(String postcode);

    DexActorType getActorType();

    void setActorType(DexActorType actorType);

}
