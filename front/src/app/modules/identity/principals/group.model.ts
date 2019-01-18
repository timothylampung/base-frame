import {Principal} from "./principal.model";

export interface Group extends Principal {
    memberCount: number;
}
