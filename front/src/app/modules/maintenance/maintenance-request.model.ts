import {Document} from '../../models/document.model';
import {Task} from '../../models/task.model';
import {Record} from '../../models/record.model';
import {FlowState} from '../../models/flow-state.enum';
import {Actor} from "../identity/actors/actor.model";
import {Location} from "../asset/locations/location-model";
import {Asset} from "../asset/assets/asset-model";

export interface MaintenanceRequest extends Document {
    requestedDate: Date;
    reporter: Actor;
    delegator: Actor;
    verifier: Actor;
    location: Location;
    asset: Asset;
    remark:String;
}

export interface MaintenanceRequestResult {
    totalSize: number;
    data: MaintenanceRequest[];
}

export interface MaintenanceRequestRecordSummary extends Record {
    totalAmount;
}

export interface MaintenanceRequestRecordSummaryResult {
    totalSize: number;
    data: MaintenanceRequestRecordSummary[];
}

export interface MaintenanceRequestTaskSummary extends Task {
    maintenanceRequest: MaintenanceRequest;
}

export interface MaintenanceRequestTaskSummaryResult {
    totalSize: number;
    data: MaintenanceRequestTaskSummary[];
}

export const initStateMaintenanceRequestResult: MaintenanceRequestResult = {
    data: [], totalSize: 0
};

export const initStateMaintenanceRequestTaskResult: MaintenanceRequestTaskSummaryResult = {
    data: [], totalSize: 0
};

export const initStateMaintenanceRequestRecordResult: MaintenanceRequestRecordSummaryResult = {
    data: [], totalSize: 0
};

export const initStateMaintenanceRequest: MaintenanceRequest = {
    id: null,
    referenceNo: null,
    description: null,
    flowState: FlowState.NEW,
    sourceNo: null,
    requestedDate:null,
    reporter: null,
    delegator: null,
    verifier: null,
    location: null,
    asset: null,
    remark:null,
};

export const initStateMaintenanceRequestTaskSummary: MaintenanceRequestTaskSummary = {
    id: null,
    taskId: null,
    taskName: null,
    assignee: null,
    referenceNo: null,
    sourceNo: null,
    description: null,
    flowState: FlowState.NEW,
    maintenanceRequest: initStateMaintenanceRequest,
};
