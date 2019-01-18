import {Actor} from "../actors/actor.model";

export interface Beneficiary extends Actor {
    code: string;
    description: string;
}

export interface BeneficiaryResult {
    totalSize: number;
    data: Beneficiary[];
}

