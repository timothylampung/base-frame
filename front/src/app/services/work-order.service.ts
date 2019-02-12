import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {
    WorkOrder,
    WorkOrderRecordSummary,
    WorkOrderRecordSummaryResult,
    WorkOrderResult,
    WorkOrderTaskSummary,
    WorkOrderTaskSummaryResult
} from '../modules/work-order/work-order.model';
import {Activity} from "../modules/work-order/activity.model";

@Injectable()
export class WorkOrderService {
    private WORK_ORDER_API: string = environment.endpoint + '/api/work-order/work-orders';

    constructor(public http: HttpClient) {
    }

    // =================================================================================================================
    // WORK_ORDERS
    // =================================================================================================================

    findWorkOrderByReferenceNo(referenceNo: string): Observable<WorkOrder> {
        const url = `${this.WORK_ORDER_API}/${referenceNo}`;
        return this.http.get<WorkOrder>(url);
    }

    findActivities(workOrder: WorkOrder): Observable<Activity[]> {
        const url = `${this.WORK_ORDER_API}/${workOrder.referenceNo}/activities`;
        return this.http.get<Activity[]>(url);
    }

    findAssignedWorkOrders(filter: string, page: number): Observable<WorkOrderTaskSummaryResult> {
        const url = `${this.WORK_ORDER_API}/assigned-tasks?filter=${filter}&page=${page}`;
        return this.http.get<WorkOrderTaskSummaryResult>(url);
    }

    findPooledWorkOrders(filter: string, page: number): Observable<WorkOrderTaskSummaryResult> {
        const url = `${this.WORK_ORDER_API}/pooled-tasks?filter=${filter}&page=${page}`;
        return this.http.get<WorkOrderTaskSummaryResult>(url);
    }

    findArchivedWorkOrders(filter: string, page: number): Observable<WorkOrderRecordSummaryResult> {
        const url = `${this.WORK_ORDER_API}/archived-records?filter=${filter}&page=${page}`;
        return this.http.get<WorkOrderRecordSummaryResult>(url);
    }

    findWorkOrderTaskByTaskId(taskId: string): Observable<WorkOrderTaskSummary> {
        return this.http.get<WorkOrderTaskSummary>(
            this.WORK_ORDER_API + '/view-task/' + taskId
        );
    }

    findWorkOrderRecordByRecordId(recordId: string): Observable<WorkOrderRecordSummary> {
        return this.http.get<WorkOrderRecordSummary>(
            this.WORK_ORDER_API + '/view-record/' + recordId
        );
    }

    findPagedWorkOrders(filter: string, page: number): Observable<WorkOrderResult> {
        const url = `${this.WORK_ORDER_API}/work-orders?filter=${filter}&page=${page}`;
        return this.http.get<WorkOrderResult>(url);
    }

    findWorkOrders(): Observable<WorkOrder[]> {
        const url = `${this.WORK_ORDER_API}/work-orders`;
        return this.http.get<WorkOrder[]>(url);
    }

    countAssignedWorkOrders(): Observable<any> {
        const url = `${this.WORK_ORDER_API}/count-assigned-task`;
        return this.http.get<any>(url);
    }

    countPooledWorkOrders(): Observable<any> {
        const url = `${this.WORK_ORDER_API}/count-pooled-task`;
        return this.http.get<any>(url);
    }


    startWorkOrderTask(workOrder: WorkOrder) {
        const url = `${this.WORK_ORDER_API}/start-task`;
        return this.http.post(url, JSON.stringify(workOrder));
    }

    completeWorkOrderTask(taskId: string) {
        const url = `${this.WORK_ORDER_API}/complete-task/${taskId}`;
        return this.http.post(url, null);
    }

    releaseWorkOrderTask(taskId: string) {
        const url = `${this.WORK_ORDER_API}/release-task/${taskId}`;
        return this.http.post(url, null);
    }

    claimWorkOrderTask(taskIds: string[]) {
        const url = `${this.WORK_ORDER_API}/claim-task`;
        return this.http.post(url, JSON.stringify(taskIds));
    }

    removeWorkOrderTask(taskId: string) {
        const url = `${this.WORK_ORDER_API}/remove-task/${taskId}`;
        return this.http.post(url, null);
    }

    updateWorkOrder(workOrder: WorkOrder) {
        const url = `${this.WORK_ORDER_API}/${workOrder.referenceNo}`;
        return this.http.put(url, JSON.stringify(workOrder));
    }

    addActivity(workOrder: WorkOrder, item: Activity): Observable<Activity> {
        const url = `${this.WORK_ORDER_API}/${workOrder.referenceNo}/activities`;
        return this.http.post<Activity>(url, item);
    }

    updateActivity(workOrder: WorkOrder, item: Activity): Observable<Activity> {
        const url = `${this.WORK_ORDER_API}/${workOrder.referenceNo}/activities/${item.id}`;
        return this.http.put<Activity>(url, JSON.stringify(item));
    }

    deleteActivity(workOrder: WorkOrder, item: Activity) {
        const url = `${this.WORK_ORDER_API}/${workOrder.referenceNo}/activities/${item.id}`;
        return this.http.delete(url);
    }
}
