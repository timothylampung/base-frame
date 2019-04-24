import {ActorType} from './actor-type.enum';
import {MetaObject} from "../../../models/meta-object.model";

export interface Actor extends MetaObject {
    code: string;
    accountNo: string;
    name: string;
    identityNo: string;
    email: string;
    office: string;
    phone: string;
    mobile: string;
    fax: string;
    address1: string;
    address2: string;
    postcode: string;
    actorType: ActorType;
}
