import {PrincipalType} from './principal-type.enum';
import {MetaObject} from "../../../models/meta-object.model";

export interface Principal extends MetaObject {
    name: string;
    principalType: PrincipalType;
}
