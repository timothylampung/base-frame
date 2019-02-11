package my.spotit.identity.api.vo;

import my.spotit.common.api.vo.PositionCode;

/**
 * @author canang technologies
 */
public class Staff extends Actor {

    private PositionCode positionCode;

    public PositionCode getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(PositionCode positionCode) {
        this.positionCode = positionCode;
    }
}
