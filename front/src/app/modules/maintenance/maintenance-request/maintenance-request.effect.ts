import {Injectable} from "@angular/core";
import {
    FIND_ALL_MAINTENANCE_REQUESTS, FIND_PAGED_MAINTENANCE_REQUESTS,
    FindAllMaintenanceRequestsSuccessAction, FindPagedMaintenanceRequestsAction,
    FindPagedMaintenanceRequestsSuccessAction, REMOVE_MAINTENANCE_REQUEST, RemoveMaintenanceRequestAction, RemoveMaintenanceRequestSuccessAction,
    SAVE_MAINTENANCE_REQUEST,
    SaveMaintenanceRequestAction, SaveMaintenanceRequestSuccessAction, UPDATE_MAINTENANCE_REQUEST,
    UpdateMaintenanceRequestAction, UpdateMaintenanceRequestSuccessAction
} from "./maintenance-request.action";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";
import {MaintenanceRequestService} from "../../../services/maintenance-request.service";


@Injectable()
export class MaintenanceRequestEffects {

    constructor(private actions$: Actions,
                private maintenanceService: MaintenanceRequestService) {
    }

    @Effect()
    public findAllMaintenanceRequests$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_MAINTENANCE_REQUESTS),
            switchMap(action => this.maintenanceService.findMaintenanceRequests()),
            map(result => new FindAllMaintenanceRequestsSuccessAction(result)),);

    @Effect()
    public findPagedMaintenanceRequests$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_MAINTENANCE_REQUESTS),
            map((action: FindPagedMaintenanceRequestsAction) => action.payload),
            switchMap(payload => this.maintenanceService.findPagedMaintenanceRequests(payload.filter, payload.page)),
            map(result => new FindPagedMaintenanceRequestsSuccessAction(result)),);

    @Effect() saveMaintenanceRequest$ = this.actions$
        .pipe(
            ofType(SAVE_MAINTENANCE_REQUEST),
            map((action: SaveMaintenanceRequestAction) => action.payload),
            switchMap((maintenanceRequest) => this.maintenanceService.saveMaintenanceRequest(maintenanceRequest)),
            map((message) => new SaveMaintenanceRequestSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedMaintenanceRequestsAction({filter: 'todo', page: 1})])),);

    @Effect() updateMaintenanceRequest$ = this.actions$
        .pipe(
            ofType(UPDATE_MAINTENANCE_REQUEST),
            map((action: UpdateMaintenanceRequestAction) => action.payload),
            switchMap((maintenanceRequest) => this.maintenanceService.updateMaintenanceRequest(maintenanceRequest)),
            map((maintenanceRequest) => new UpdateMaintenanceRequestSuccessAction({message: 'success'})),
            switchMap(() => this.maintenanceService.findMaintenanceRequests()),
            map((maintenanceRequests) => new FindPagedMaintenanceRequestsAction({filter: 'todo', page: 1})));

    @Effect() removeMaintenanceRequest$ = this.actions$
        .pipe(
            ofType(REMOVE_MAINTENANCE_REQUEST),
            map((action: RemoveMaintenanceRequestAction) => action.payload),
            switchMap(payload => this.maintenanceService.removeMaintenanceRequest(payload)),
            map(message => new RemoveMaintenanceRequestSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedMaintenanceRequestsAction({filter: 'todo', page: 1})])),);
}
