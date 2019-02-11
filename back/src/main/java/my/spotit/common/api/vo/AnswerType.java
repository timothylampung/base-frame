package my.spotit.common.api.vo;

public enum AnswerType {
    NoAnswer,
    A,
    B,
    C,
    D;

    public static AnswerType get(int ordinal){
        return values()[ordinal];
    }
}
