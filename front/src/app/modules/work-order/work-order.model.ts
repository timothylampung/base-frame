import {Document} from '../../models/document.model';
import {Task} from '../../models/task.model';
import {Record} from '../../models/record.model';
import {FlowState} from '../../models/flow-state.enum';
import {Actor} from "../identity/actors/actor.model";
import {Location} from "../asset/locations/location-model";
import {Asset} from "../asset/assets/asset-model";
import {MaintenanceRequest} from "../maintenance/maintenance-request.model";

export interface WorkOrder extends Document {
    reporter: Actor;
    assignee: Actor;
    verifier: Actor;
    location: Location
    asset: Asset
    maintenanceRequest:MaintenanceRequest;
}

export interface WorkOrderResult {
    totalSize: number;
    data: WorkOrder[];
}

export interface WorkOrderRecordSummary extends Record {
    totalAmount;
}

export interface WorkOrderRecordSummaryResult {
    totalSize: number;
    data: WorkOrderRecordSummary[];
}

export interface WorkOrderTaskSummary extends Task {
    workOrder: WorkOrder;
}

export interface WorkOrderTaskSummaryResult {
    totalSize: number;
    data: WorkOrderTaskSummary[];
}

export const initStateWorkOrderResult: WorkOrderResult = {
    data: [], totalSize: 0
};

export const initStateWorkOrderTaskResult: WorkOrderTaskSummaryResult = {
    data: [], totalSize: 0
};

export const initStateWorkOrderRecordResult: WorkOrderRecordSummaryResult = {
    data: [], totalSize: 0
};

export const initStateWorkOrder: WorkOrder = {
    id: null,
    referenceNo: null,
    description: null,
    flowState: FlowState.NEW,
    sourceNo: null,
    reporter: null,
    assignee: null,
    verifier: null,
    location: null,
    asset: null,
    maintenanceRequest:null,
};

export const initStateWorkOrderTaskSummary: WorkOrderTaskSummary = {
    id: null,
    taskId: null,
    taskName: null,
    assignee: null,
    referenceNo: null,
    sourceNo: null,
    description: null,
    flowState: FlowState.NEW,
    workOrder: initStateWorkOrder,
};
