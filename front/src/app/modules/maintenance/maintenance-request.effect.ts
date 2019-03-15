import {catchError, map, switchMap, withLatestFrom} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action, select, Store} from '@ngrx/store';
import {forkJoin, Observable, of} from 'rxjs';
import * as _ from 'lodash';
import {
    CLAIM_WORK_ORDER_TASK,
    ClaimMaintenanceRequestTaskAction,
    ClaimMaintenanceRequestTaskSuccessAction,
    COMPLETE_WORK_ORDER_TASK,
    CompleteMaintenanceRequestTaskAction,
    CompleteMaintenanceRequestTaskSuccessAction,
    COUNT_ASSIGNED_WORK_ORDERS,
    COUNT_POOLED_WORK_ORDERS,
    CountAssignedMaintenanceRequestsSuccessAction,
    CountPooledMaintenanceRequestsSuccessAction,
    FIND_ARCHIVED_WORK_ORDERS,
    FIND_ASSIGNED_WORK_ORDERS,
    FIND_WORK_ORDER_BY_REFERENCE_NO,
    FIND_WORK_ORDER_RECORD_BY_RECORD_ID,
    FIND_WORK_ORDER_TASK_BY_TASK_ID,
    FIND_PAGED_WORK_ORDERS,
    FIND_POOLED_WORK_ORDERS,
    FindArchivedMaintenanceRequestsAction,
    FindArchivedMaintenanceRequestsSuccessAction,
    FindAssignedMaintenanceRequestsAction,
    FindAssignedMaintenanceRequestsSuccessAction,
    FindMaintenanceRequestByReferenceNoAction,
    FindMaintenanceRequestByReferenceNoSuccessAction,
    FindMaintenanceRequestRecordByRecordIdAction,
    FindMaintenanceRequestRecordByRecordIdSuccessAction,
    FindMaintenanceRequestTaskByTaskIdAction,
    FindMaintenanceRequestTaskByTaskIdSuccessAction,
    FindPagedMaintenanceRequestsAction,
    FindPagedMaintenanceRequestsSuccessAction,
    FindPooledMaintenanceRequestsAction,
    FindPooledMaintenanceRequestsSuccessAction,
    RELEASE_WORK_ORDER_TASK,
    ReleaseMaintenanceRequestTaskAction,
    ReleaseMaintenanceRequestTaskSuccessAction,
    RELOAD_WORK_ORDER_PAGE,
    ReloadMaintenanceRequestPageAction,
    REMOVE_WORK_ORDER_TASK,
    RemoveMaintenanceRequestTaskAction,
    RemoveMaintenanceRequestTaskSuccessAction,
    START_WORK_ORDER_TASK,
    StartMaintenanceRequestTaskAction,
    StartMaintenanceRequestTaskSuccessAction,
    UPDATE_WORK_ORDER,
    UpdateMaintenanceRequestAction,
    UpdateMaintenanceRequestSuccessAction,
} from './maintenance-request.action';
import {Router} from '@angular/router';
import {selectMaintenanceRequest, selectMaintenanceRequestTask} from './maintenance-request.selector';
import {AppState} from '../../core/core.state';
import {concatMap} from "rxjs/internal/operators";
import {LoadError} from "../../static/app.action";
import {MaintenanceRequestService} from "../../services/maintenance-request.service";
import {MaintenanceRequest} from "./maintenance-request.model";

@Injectable()
export class MaintenanceRequestEffects {
    constructor(private actions$: Actions,
                private router: Router,
                private store: Store<AppState>,
                private maintenanceRequestService: MaintenanceRequestService) {
    }

    @Effect()
    public findMaintenanceRequestByReferenceNo$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_WORK_ORDER_BY_REFERENCE_NO),
        map((action: FindMaintenanceRequestByReferenceNoAction) => action.referenceNo),
        switchMap(referenceNo => {
            return this.maintenanceRequestService
                .findMaintenanceRequestByReferenceNo(referenceNo)
                .pipe(
                    map(
                        result =>
                            new FindMaintenanceRequestByReferenceNoSuccessAction(result)
                    ),
                    catchError(err => of(new LoadError(err)))
                );
        })
    );


    @Effect()
    public findAssignedMaintenanceRequests$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_ASSIGNED_WORK_ORDERS),
        map((action: FindAssignedMaintenanceRequestsAction) => action.payload),
        switchMap(payload =>
            this.maintenanceRequestService
                .findAssignedMaintenanceRequests(payload.filter, payload.page)
                .pipe(
                    map(result => new FindAssignedMaintenanceRequestsSuccessAction(result)),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    public findPooledMaintenanceRequests$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_POOLED_WORK_ORDERS),
        map((action: FindPooledMaintenanceRequestsAction) => action.payload),
        switchMap(payload =>
            this.maintenanceRequestService.findPooledMaintenanceRequests(payload.filter, payload.page)
                .pipe(
                    map(result => new FindPooledMaintenanceRequestsSuccessAction(result)),
                    catchError(err => of(new LoadError(err)))
                )
        ),
    );

    @Effect()
    public findArchivedMaintenanceRequests$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_ARCHIVED_WORK_ORDERS),
        map((action: FindArchivedMaintenanceRequestsAction) => action.payload),
        switchMap(payload =>
            this.maintenanceRequestService.findArchivedMaintenanceRequests(
                payload.filter,
                payload.page
            ).pipe(
                map(result => new FindArchivedMaintenanceRequestsSuccessAction(result)),
                catchError(err => of(new LoadError(err)))
            )
        )
    );

    @Effect()
    findMaintenanceRequestTaskByTaskId = this.actions$.pipe(
        ofType(FIND_WORK_ORDER_TASK_BY_TASK_ID),
        map((action: FindMaintenanceRequestTaskByTaskIdAction) => action.payload),
        switchMap(payload =>
            this.maintenanceRequestService.findMaintenanceRequestTaskByTaskId(payload.taskId)
                .pipe(
                    map(task => new FindMaintenanceRequestTaskByTaskIdSuccessAction(task)),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    findMaintenanceRequestRecordByRecordId = this.actions$.pipe(
        ofType(FIND_WORK_ORDER_RECORD_BY_RECORD_ID),
        map((action: FindMaintenanceRequestRecordByRecordIdAction) => action.payload),
        switchMap(payload =>
            this.maintenanceRequestService.findMaintenanceRequestRecordByRecordId(payload.recordId)
                .pipe(
                    map(record => new FindMaintenanceRequestRecordByRecordIdSuccessAction(record)),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    public findPagedMaintenanceRequests$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_PAGED_WORK_ORDERS),
        map((action: FindPagedMaintenanceRequestsAction) => action.payload),
        switchMap(payload =>
            this.maintenanceRequestService.findPagedMaintenanceRequests(payload.filter, payload.page)
                .pipe(
                    map(result => new FindPagedMaintenanceRequestsSuccessAction(result)),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    public countAssignedMaintenanceRequests$: Observable<Action> = this.actions$.pipe(
        ofType(COUNT_ASSIGNED_WORK_ORDERS),
        switchMap(payload => this.maintenanceRequestService.countAssignedMaintenanceRequests()
            .pipe(
                map(count => new CountAssignedMaintenanceRequestsSuccessAction(count)),
                catchError(err => of(new LoadError(err)))
            )
        )
    );

    @Effect()
    public countPooledMaintenanceRequests$: Observable<Action> = this.actions$.pipe(
        ofType(COUNT_POOLED_WORK_ORDERS),
        switchMap(payload => this.maintenanceRequestService.countPooledMaintenanceRequests()
            .pipe(
                map(count => new CountPooledMaintenanceRequestsSuccessAction(count)),
                catchError(err => of(new LoadError(err)))
            )
        ),
    );


    @Effect()
    startMaintenanceRequestTask$: Observable<Action> = this.actions$.pipe(
        ofType(START_WORK_ORDER_TASK),
        map((action: StartMaintenanceRequestTaskAction) => action.payload),
        switchMap(maintenanceRequest => this.maintenanceRequestService.startMaintenanceRequestTask(maintenanceRequest)
            .pipe(
                map(_ => {
                    this.router.navigate(['maintenanceRequest/maintenanceRequest-tasks/assigned']);
                    return new StartMaintenanceRequestTaskSuccessAction({message: ''});
                }),
                catchError(err => of(new LoadError(err)))
            ))
    );

    @Effect()
    completeMaintenanceRequestTask$: Observable<Action> = this.actions$.pipe(
        ofType(COMPLETE_WORK_ORDER_TASK),
        map((action: CompleteMaintenanceRequestTaskAction) => action.payload),
        switchMap(payload => this.maintenanceRequestService.completeMaintenanceRequestTask(payload.taskId)
            .pipe(
                map(_ => {
                    this.router.navigate(['maintenanceRequest/maintenance-request-tasks/assigned']);
                    return new CompleteMaintenanceRequestTaskSuccessAction({message: ''});
                }),
                catchError(err => of(new LoadError(err)))
            )
        )
    );

    @Effect()
    claimMaintenanceRequestTask$: Observable<Action> = this.actions$.pipe(
        ofType(CLAIM_WORK_ORDER_TASK),
        map((action: ClaimMaintenanceRequestTaskAction) => action.payload),
        switchMap(payload =>
            this.maintenanceRequestService.claimMaintenanceRequestTask(payload.taskIds)
                .pipe(
                    map(_ => {
                        this.router.navigate(['maintenanceRequest/maintenanceRequest-tasks/assigned']);
                        return new ClaimMaintenanceRequestTaskSuccessAction({message: ''});
                    }),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    releaseMaintenanceRequestTask$: Observable<Action> = this.actions$.pipe(
        ofType(RELEASE_WORK_ORDER_TASK),
        map((action: ReleaseMaintenanceRequestTaskAction) => action.payload),
        switchMap(payload =>
            this.maintenanceRequestService.completeMaintenanceRequestTask(payload.taskId)
                .pipe(
                    map(_ => {
                        this.router.navigate(['maintenanceRequest/maintenanceRequest-tasks/assigned']);
                        return new ReleaseMaintenanceRequestTaskSuccessAction({message: ''});
                    }),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    removeMaintenanceRequestTask$: Observable<Action> = this.actions$.pipe(
        ofType(REMOVE_WORK_ORDER_TASK),
        map((action: RemoveMaintenanceRequestTaskAction) => action.payload),
        switchMap(payload => this.maintenanceRequestService.removeMaintenanceRequestTask(payload.taskId)
            .pipe(
                map(message => new RemoveMaintenanceRequestTaskSuccessAction({message: 'success'})),
                catchError(err => of(new LoadError(err)))
            )),
    );

    @Effect()
    updateMaintenanceRequest$ = this.actions$.pipe(
        ofType(UPDATE_WORK_ORDER),
        map((action: UpdateMaintenanceRequestAction) => action.payload),
        switchMap(maintenanceRequest => this.maintenanceRequestService.updateMaintenanceRequest(maintenanceRequest)
            .pipe(
                concatMap(maintenanceRequest =>
                    [new UpdateMaintenanceRequestSuccessAction({message: 'success'}), new ReloadMaintenanceRequestPageAction()]),
                catchError(err => of(new LoadError(err)))
            )),
    );


    @Effect()
    public reloadMaintenanceRequestPage$: Observable<Action> = this.actions$.pipe(
        ofType(RELOAD_WORK_ORDER_PAGE),
        withLatestFrom(this.store.select(selectMaintenanceRequest)),
        concatMap(([action, maintenanceRequest]: [Action, MaintenanceRequest]) => []),
    );
}
