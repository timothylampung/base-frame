package com.assettagging.spotit.core.api;

/**
 */
public class Permission {
    private boolean view;
    private boolean create;
    private boolean update;
    private boolean delete;
    private boolean cancel;
    private boolean print;
    private boolean admin;

    public Permission(boolean view, boolean create, boolean update, boolean delete, boolean cancel, boolean print, boolean admin) {
        this.view = view;
        this.create = create;
        this.update = update;
        this.delete = delete;
        this.cancel = cancel;
        this.print = print;
        this.admin = admin;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
