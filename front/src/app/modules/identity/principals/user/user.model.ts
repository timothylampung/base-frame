import {Principal} from "../principal.model";

export interface User extends Principal {
    realName: string;
    password: string;
    email: string;
}
