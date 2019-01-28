import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";


export  interface MaintenanceRequest extends MetaObject {

    code: string;
    description: string;

}

export interface MaintenanceRequestResult {

    totalSize: number;
    data: MaintenanceRequest[];

}
