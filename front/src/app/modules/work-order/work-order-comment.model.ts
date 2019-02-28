import {MetaObject} from "../../models";
import {User} from "../identity/principals/user/user.model";

export interface WorkOrderComment extends MetaObject {
    body: string;
    poster: User;
}
