package com.assettagging.spotit.identity.api.vo;

/**
 * @author canang technologies
 */
public enum ActorType {

    STAFF, //0
    APPLICANT; //1

    public static ActorType get(int index) {
        return values()[index];
    }

}
