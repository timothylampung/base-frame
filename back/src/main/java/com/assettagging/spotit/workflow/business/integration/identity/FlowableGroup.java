package com.assettagging.spotit.workflow.business.integration.identity;

import com.assettagging.spotit.identity.domain.model.DexGroup;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntity;

public class FlowableGroup implements GroupEntity {

    private DexGroup group;

    public FlowableGroup(DexGroup group) {
        this.group = group;
    }

    @Override
    public String getId() {
        return group.getName();
    }

    @Override
    public void setId(String id) {
    }

    @Override
    public String getName() {
        return group.getName();
    }

    @Override
    public void setName(String name) {
        group.setName(name);
    }

    @Override
    public String getType() {
        return "group";
    }

    @Override
    public void setType(String string) {
    }

    @Override
    public int getRevision() {
        return 0;
    }

    @Override
    public void setRevision(int revision) {

    }

    @Override
    public int getRevisionNext() {
        return 0;
    }

    @Override
    public boolean isInserted() {
        return false;
    }

    @Override
    public void setInserted(boolean inserted) {

    }

    @Override
    public boolean isUpdated() {
        return false;
    }

    @Override
    public void setUpdated(boolean updated) {

    }

    @Override
    public boolean isDeleted() {
        return false;
    }

    @Override
    public void setDeleted(boolean deleted) {

    }

    @Override
    public Object getPersistentState() {
        return null;
    }

    @Override
    public Object getOriginalPersistentState() {
        return null;
    }

    @Override
    public void setOriginalPersistentState(Object persistentState) {

    }
}
