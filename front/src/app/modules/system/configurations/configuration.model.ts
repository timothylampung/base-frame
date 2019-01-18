import {MetaObject} from "../../../models/meta-object.model";

export interface Configuration extends MetaObject {
    key: string
    value: string
}
