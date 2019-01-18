package com.assettagging.spotit.workflow.business.integration.identity;

import org.flowable.idm.api.Picture;
import org.flowable.idm.engine.impl.persistence.entity.ByteArrayRef;
import org.flowable.idm.engine.impl.persistence.entity.UserEntity;

import com.assettagging.spotit.identity.domain.model.DexUser;

public class FlowableUser implements UserEntity {

    private DexUser user;

    public FlowableUser(DexUser user) {
        this.user = user;
    }

    @Override
    public String getId() {
        return user.getUsername();
    }

    @Override
    public void setId(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getFirstName() {
        return user.getUsername();
    }

    @Override
    public void setFirstName(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getLastName() {
        return user.getUsername();
    }

    @Override
    public void setLastName(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getEmail() {
        return user.getUsername();
    }

    @Override
    public void setEmail(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public void setPassword(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Picture getPicture() {
        return null;
    }

    @Override
    public void setPicture(Picture picture) {

    }

    @Override
    public boolean isPictureSet() {
        return false;
    }

    @Override
    public ByteArrayRef getPictureByteArrayRef() {
        return null;
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

