import {Actor} from "../actors/actor.model";

export interface Technician extends Actor {
}

export interface TechnicianResult {
    totalSize: number;
    data: Technician[];
}

