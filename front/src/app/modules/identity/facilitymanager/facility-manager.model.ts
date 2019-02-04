import {Actor} from "../actors/actor.model";

export interface FacilityManager extends Actor {
}

export interface FacilityManagerResult {
    totalSize: number;
    data: FacilityManager[];
}

