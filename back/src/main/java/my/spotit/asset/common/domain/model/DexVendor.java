package my.spotit.asset.common.domain.model;
import my.spotit.asset.core.domain.DexMetaObject;

public interface DexVendor extends DexMetaObject {

    String getCode();

    void setCode(String code);

    String getName();

    void setName(String name);

    String getBranch();

    void setBranch(String branch);

    String getDescription();

    void setDescription(String description);

    String getAddress();

    void setAddress(String address);

    String getPersonInCharge();

    void setPersonInCharge(String personInCharge);

    String getContactNo();

    void setContactNo(String contactNo);

    String getEmail();

    void setEmail(String email);


}
