import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";

export  interface Location extends MetaObject {
    code: string;
    description: string;
}

export interface LocationResult {

    totalSize: number;
    data: Location[];

}
