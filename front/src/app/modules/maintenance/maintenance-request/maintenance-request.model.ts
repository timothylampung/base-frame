import {MetaObject} from "../../../models/meta-object.model";
import {Actor} from "../../identity/actors/actor.model";
import {Location} from "../../asset/locations/location-model";
import {Asset} from "../../asset/assets/asset-model";
import {Document} from "../../../models/document.model";


export interface MaintenanceRequest extends Document {
    remark: string;
    requestedDate: Date;
    delegated: boolean;
    requester: Actor;
    delegator: Actor;
    verifier: Actor;
    location: Location;
    asset: Asset;
}

export interface MaintenanceRequestResult {

    totalSize: number;
    data: MaintenanceRequest[];

}
