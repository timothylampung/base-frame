package com.assettagging.spotit.common.api.vo;

public enum DifficultyLevel {

    EASY,
    MEDIUM,
    HARD;

    public static DifficultyLevel get(int ordinal){
        return values()[ordinal];
    }
}
