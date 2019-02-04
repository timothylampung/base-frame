import {Actor} from "../actors/actor.model";

export interface Supervisor extends Actor {
}

export interface SupervisorResult {
    totalSize: number;
    data: Supervisor[];
}

