import {Actor} from "../actors/actor.model";
import {PositionCode} from "../../common/position-codes/position-code.model";

export interface Staff extends Actor {
    positionCode: PositionCode;
}

export interface StaffResult {
    totalSize: number;
    data: Staff[];
}

