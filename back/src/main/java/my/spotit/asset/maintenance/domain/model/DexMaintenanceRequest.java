package my.spotit.asset.maintenance.domain.model;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.core.domain.DexDocument;
import my.spotit.asset.identity.domain.model.DexActor;

import java.util.Date;

public interface DexMaintenanceRequest extends DexDocument {

    String getRemark();

    void setRemark(String remark);

    Date getRequestedDate();

    void setRequestedDate(Date requestedDate);

    Boolean isDelegated();

    void setDelegated(Boolean delegated);

    DexActor getDelegator();

    void setDelegator(DexActor actor);

    DexActor getVerifier();

    void setVerifier(DexActor actor);

    DexActor getRequester();

    void setRequester(DexActor requester);

    DexLocation getLocation();

    void setLocation(DexLocation location);

    DexAsset getAsset();

    void setAsset(DexAsset asset);
}
