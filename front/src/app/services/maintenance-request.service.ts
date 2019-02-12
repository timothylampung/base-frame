import {environment} from '../../environments/environment';
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {
    MaintenanceRequest,
    MaintenanceRequestRecordSummary,
    MaintenanceRequestRecordSummaryResult, MaintenanceRequestResult,
    MaintenanceRequestTaskSummary,
    MaintenanceRequestTaskSummaryResult
} from "../modules/maintenance/maintenance-request.model";

@Injectable()
export class MaintenanceRequestService {
    private MAINTENANCE_REQUEST_API: string = environment.endpoint + '/api/maintenance-request/maintenance-requests';

    constructor(public http: HttpClient) {
    }

    // =================================================================================================================
    // MAINTENANCE_REQUESTS
    // =================================================================================================================

    findMaintenanceRequestByReferenceNo(referenceNo: string): Observable<MaintenanceRequest> {
        const url = `${this.MAINTENANCE_REQUEST_API}/${referenceNo}`;
        return this.http.get<MaintenanceRequest>(url);
    }

    findAssignedMaintenanceRequests(filter: string, page: number): Observable<MaintenanceRequestTaskSummaryResult> {
        const url = `${this.MAINTENANCE_REQUEST_API}/assigned-tasks?filter=${filter}&page=${page}`;
        return this.http.get<MaintenanceRequestTaskSummaryResult>(url);
    }

    findPooledMaintenanceRequests(filter: string, page: number): Observable<MaintenanceRequestTaskSummaryResult> {
        const url = `${this.MAINTENANCE_REQUEST_API}/pooled-tasks?filter=${filter}&page=${page}`;
        return this.http.get<MaintenanceRequestTaskSummaryResult>(url);
    }

    findArchivedMaintenanceRequests(filter: string, page: number): Observable<MaintenanceRequestRecordSummaryResult> {
        const url = `${this.MAINTENANCE_REQUEST_API}/archived-records?filter=${filter}&page=${page}`;
        return this.http.get<MaintenanceRequestRecordSummaryResult>(url);
    }

    findMaintenanceRequestTaskByTaskId(taskId: string): Observable<MaintenanceRequestTaskSummary> {
        return this.http.get<MaintenanceRequestTaskSummary>(
            this.MAINTENANCE_REQUEST_API + '/view-task/' + taskId
        );
    }

    findMaintenanceRequestRecordByRecordId(recordId: string): Observable<MaintenanceRequestRecordSummary> {
        return this.http.get<MaintenanceRequestRecordSummary>(
            this.MAINTENANCE_REQUEST_API + '/view-record/' + recordId
        );
    }

    findPagedMaintenanceRequests(filter: string, page: number): Observable<MaintenanceRequestResult> {
        const url = `${this.MAINTENANCE_REQUEST_API}/maintenance-requests?filter=${filter}&page=${page}`;
        return this.http.get<MaintenanceRequestResult>(url);
    }

    findMaintenanceRequests(): Observable<MaintenanceRequest[]> {
        const url = `${this.MAINTENANCE_REQUEST_API}/maintenance-requests`;
        return this.http.get<MaintenanceRequest[]>(url);
    }

    countAssignedMaintenanceRequests(): Observable<any> {
        const url = `${this.MAINTENANCE_REQUEST_API}/count-assigned-task`;
        return this.http.get<any>(url);
    }

    countPooledMaintenanceRequests(): Observable<any> {
        const url = `${this.MAINTENANCE_REQUEST_API}/count-pooled-task`;
        return this.http.get<any>(url);
    }


    startMaintenanceRequestTask(maintenanceRequest: MaintenanceRequest) {
        const url = `${this.MAINTENANCE_REQUEST_API}/start-task`;
        return this.http.post(url, JSON.stringify(maintenanceRequest));
    }

    completeMaintenanceRequestTask(taskId: string) {
        const url = `${this.MAINTENANCE_REQUEST_API}/complete-task/${taskId}`;
        return this.http.post(url, null);
    }

    releaseMaintenanceRequestTask(taskId: string) {
        const url = `${this.MAINTENANCE_REQUEST_API}/release-task/${taskId}`;
        return this.http.post(url, null);
    }

    claimMaintenanceRequestTask(taskIds: string[]) {
        const url = `${this.MAINTENANCE_REQUEST_API}/claim-task`;
        return this.http.post(url, JSON.stringify(taskIds));
    }

    removeMaintenanceRequestTask(taskId: string) {
        const url = `${this.MAINTENANCE_REQUEST_API}/remove-task/${taskId}`;
        return this.http.post(url, null);
    }

    updateMaintenanceRequest(maintenanceRequest: MaintenanceRequest) {
        const url = `${this.MAINTENANCE_REQUEST_API}/${maintenanceRequest.referenceNo}`;
        return this.http.put(url, JSON.stringify(maintenanceRequest));
    }
}

