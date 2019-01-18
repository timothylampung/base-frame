import {MetaObject} from "../../../models/meta-object.model";
import {Principal} from "./principal.model";

export interface GroupMember extends MetaObject {
    principal: Principal;
}
