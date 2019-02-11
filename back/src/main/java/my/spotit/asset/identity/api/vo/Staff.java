package my.spotit.asset.identity.api.vo;

import my.spotit.asset.common.api.vo.PositionCode;

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
