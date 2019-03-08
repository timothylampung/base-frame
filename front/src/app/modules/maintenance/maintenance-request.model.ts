import {Document} from '../../models/document.model';
import {Task} from '../../models/task.model';
import {Record} from '../../models/record.model';
import {FlowState} from '../../models/flow-state.enum';
import {Actor} from "../identity/actors/actor.model";
import {Location} from "../asset/locations/location-model";
import {Asset} from "../asset/assets/asset-model";
import {File} from "../common/file/file.model";

export interface MaintenanceRequest extends Document {
    requestedDate: Date;
    requester: Actor;
    delegator: Actor;
    verifier: Actor;
    location: Location;
    asset: Asset;
    remark:String;
    reporter:Asset;
    file? : File
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
    request: MaintenanceRequest;
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
    requester: null,
    reporter:null,
    delegator: null,
    verifier: null,
    location: null,
    asset: null,
    remark:null,
    file : null
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
    request: initStateMaintenanceRequest,
};
