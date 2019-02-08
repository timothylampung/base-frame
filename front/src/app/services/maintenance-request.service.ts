import {environment} from '../../environments/environment';
import {Injectable} from "@angular/core";
import {MaintenanceRequest, MaintenanceRequestResult} from "../modules/maintenance/maintenance-request/maintenance-request.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";


@Injectable()
export class MaintenanceRequestService {

    private MAINTENANCE_API: string = environment.endpoint + '/api/maintenance';

    constructor(public http: HttpClient) {
    }


    // ===================================================================================================================
    // MAINTENANCE REQUESTS
    // ===================================================================================================================

    findPagedMaintenanceRequests(filter: string, page: number): Observable<MaintenanceRequestResult> {
        return this.http.get<MaintenanceRequestResult>("/assets/mock-data/paged-maintenance-requests.json"
            // ,
            // {
            //     params: {
            //         filter: filter,
            //         page: page.toString()
            //     }
            // }
        );
    }

    findMaintenanceRequests(): Observable<MaintenanceRequest[]> {
        return this.http.get<MaintenanceRequest[]>(this.MAINTENANCE_API + '/maintenance-requests');
    }

    saveMaintenanceRequest(code: MaintenanceRequest) {
        return this.http.post(this.MAINTENANCE_API + '/maintenance-requests', JSON.stringify(code));
    }

    updateMaintenanceRequest(code: MaintenanceRequest) {
        return this.http.put(this.MAINTENANCE_API + '/maintenance-requests/' + code.referenceNo, JSON.stringify(code));
    }

    removeMaintenanceRequest(code: MaintenanceRequest) {
        return this.http.delete(this.MAINTENANCE_API + '/maintenance-requests/' + code.referenceNo);
    }

}

