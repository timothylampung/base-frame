import {MetaObject} from "../../models";
import {Document} from '../../models/document.model';


export interface Notification extends MetaObject {
    recieverEmail: string;
    message?: string;
    context? : NotificationContext;
    document? : Document
}

export enum NotificationContext {
    WORK_ORDER,
    MAINTENANCE_REQUEST
}
