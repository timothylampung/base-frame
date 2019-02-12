import {catchError, map, switchMap, withLatestFrom} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action, select, Store} from '@ngrx/store';
import {forkJoin, Observable, of} from 'rxjs';
import * as _ from 'lodash';
import {
    ADD_ACTIVITY,
    AddActivityAction,
    AddActivitySuccessAction,
    CLAIM_WORK_ORDER_TASK,
    ClaimWorkOrderTaskAction,
    ClaimWorkOrderTaskSuccessAction,
    COMPLETE_WORK_ORDER_TASK,
    CompleteWorkOrderTaskAction,
    CompleteWorkOrderTaskSuccessAction,
    COUNT_ASSIGNED_WORK_ORDERS,
    COUNT_POOLED_WORK_ORDERS,
    CountAssignedWorkOrdersSuccessAction,
    CountPooledWorkOrdersSuccessAction,
    FIND_ARCHIVED_WORK_ORDERS,
    FIND_ASSIGNED_WORK_ORDERS,
    FIND_WORK_ORDER_BY_REFERENCE_NO,
    FIND_ACTIVITIES,
    FIND_WORK_ORDER_RECORD_BY_RECORD_ID,
    FIND_WORK_ORDER_TASK_BY_TASK_ID,
    FIND_WORK_ORDER_TRANSACTIONS,
    FIND_PAGED_WORK_ORDERS,
    FIND_POOLED_WORK_ORDERS,
    FindArchivedWorkOrdersAction,
    FindArchivedWorkOrdersSuccessAction,
    FindAssignedWorkOrdersAction,
    FindAssignedWorkOrdersSuccessAction,
    FindWorkOrderByReferenceNoAction,
    FindWorkOrderByReferenceNoSuccessAction,
    FindActivitiesAction,
    FindActivitiesSuccessAction,
    FindWorkOrderRecordByRecordIdAction,
    FindWorkOrderRecordByRecordIdSuccessAction,
    FindWorkOrderTaskByTaskIdAction,
    FindWorkOrderTaskByTaskIdSuccessAction,
    FindPagedWorkOrdersAction,
    FindPagedWorkOrdersSuccessAction,
    FindPooledWorkOrdersAction,
    FindPooledWorkOrdersSuccessAction,
    RELEASE_WORK_ORDER_TASK,
    ReleaseWorkOrderTaskAction,
    ReleaseWorkOrderTaskSuccessAction,
    RELOAD_WORK_ORDER_PAGE,
    ReloadWorkOrderPageAction,
    REMOVE_ACTIVITY,
    REMOVE_WORK_ORDER_TASK,
    RemoveActivityAction,
    RemoveActivitySuccessAction,
    RemoveWorkOrderTaskAction,
    RemoveWorkOrderTaskSuccessAction,
    START_WORK_ORDER_TASK,
    StartWorkOrderTaskAction,
    StartWorkOrderTaskSuccessAction,
    UPDATE_WORK_ORDER,
    UPDATE_ACTIVITY,
    UpdateWorkOrderAction,
    UpdateActivityAction,
    UpdateActivitySuccessAction,
    UpdateWorkOrderSuccessAction,
} from './work-order.action';
import {WorkOrderService} from '../../services/work-order.service';
import {Router} from '@angular/router';
import {selectWorkOrder, selectWorkOrderTask} from './work-order.selector';
import {AppState} from '../../core/core.state';
import {Activity} from "./activity.model";
import {concatMap} from "rxjs/internal/operators";
import {WorkOrder} from "./work-order.model";
import {LoadError} from "../../static/app.action";

@Injectable()
export class WorkOrderEffects {
    constructor(private actions$: Actions,
                private router: Router,
                private store: Store<AppState>,
                private workOrderService: WorkOrderService) {
    }

    @Effect()
    public findWorkOrderByReferenceNo$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_WORK_ORDER_BY_REFERENCE_NO),
        map((action: FindWorkOrderByReferenceNoAction) => action.referenceNo),
        switchMap(referenceNo => {
            return this.workOrderService
                .findWorkOrderByReferenceNo(referenceNo)
                .pipe(
                    map(
                        result =>
                            new FindWorkOrderByReferenceNoSuccessAction(result)
                    ),
                    catchError(err => of(new LoadError(err)))
                );
        })
    );


    @Effect()
    public findActivities$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_ACTIVITIES),
        map((action: FindActivitiesAction) => action.payload),
        switchMap(payload => {
            return forkJoin(
                this.workOrderService.findActivities(payload.workOrder),
                of(payload.workOrder)
            );
        }),
        concatMap((z: Activity[]) => [new FindActivitiesSuccessAction(z)]));

    @Effect()
    public findAssignedWorkOrders$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_ASSIGNED_WORK_ORDERS),
        map((action: FindAssignedWorkOrdersAction) => action.payload),
        switchMap(payload =>
            this.workOrderService
                .findAssignedWorkOrders(payload.filter, payload.page)
                .pipe(
                    map(result => new FindAssignedWorkOrdersSuccessAction(result)),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    public findPooledWorkOrders$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_POOLED_WORK_ORDERS),
        map((action: FindPooledWorkOrdersAction) => action.payload),
        switchMap(payload =>
            this.workOrderService.findPooledWorkOrders(payload.filter, payload.page)
                .pipe(
                    map(result => new FindPooledWorkOrdersSuccessAction(result)),
                    catchError(err => of(new LoadError(err)))
                )
        ),
    );

    @Effect()
    public findArchivedWorkOrders$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_ARCHIVED_WORK_ORDERS),
        map((action: FindArchivedWorkOrdersAction) => action.payload),
        switchMap(payload =>
            this.workOrderService.findArchivedWorkOrders(
                payload.filter,
                payload.page
            ).pipe(
                map(result => new FindArchivedWorkOrdersSuccessAction(result)),
                catchError(err => of(new LoadError(err)))
            )
        )
    );

    @Effect()
    findWorkOrderTaskByTaskId = this.actions$.pipe(
        ofType(FIND_WORK_ORDER_TASK_BY_TASK_ID),
        map((action: FindWorkOrderTaskByTaskIdAction) => action.payload),
        switchMap(payload =>
            this.workOrderService.findWorkOrderTaskByTaskId(payload.taskId)
                .pipe(
                    map(task => new FindWorkOrderTaskByTaskIdSuccessAction(task)),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    findWorkOrderRecordByRecordId = this.actions$.pipe(
        ofType(FIND_WORK_ORDER_RECORD_BY_RECORD_ID),
        map((action: FindWorkOrderRecordByRecordIdAction) => action.payload),
        switchMap(payload =>
            this.workOrderService.findWorkOrderRecordByRecordId(payload.recordId)
                .pipe(
                    map(record => new FindWorkOrderRecordByRecordIdSuccessAction(record)),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    public findPagedWorkOrders$: Observable<Action> = this.actions$.pipe(
        ofType(FIND_PAGED_WORK_ORDERS),
        map((action: FindPagedWorkOrdersAction) => action.payload),
        switchMap(payload =>
            this.workOrderService.findPagedWorkOrders(payload.filter, payload.page)
                .pipe(
                    map(result => new FindPagedWorkOrdersSuccessAction(result)),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    public countAssignedWorkOrders$: Observable<Action> = this.actions$.pipe(
        ofType(COUNT_ASSIGNED_WORK_ORDERS),
        switchMap(payload => this.workOrderService.countAssignedWorkOrders()
            .pipe(
                map(count => new CountAssignedWorkOrdersSuccessAction(count)),
                catchError(err => of(new LoadError(err)))
            )
        )
    );

    @Effect()
    public countPooledWorkOrders$: Observable<Action> = this.actions$.pipe(
        ofType(COUNT_POOLED_WORK_ORDERS),
        switchMap(payload => this.workOrderService.countPooledWorkOrders()
            .pipe(
                map(count => new CountPooledWorkOrdersSuccessAction(count)),
                catchError(err => of(new LoadError(err)))
            )
        ),
    );


    @Effect()
    startWorkOrderTask$: Observable<Action> = this.actions$.pipe(
        ofType(START_WORK_ORDER_TASK),
        map((action: StartWorkOrderTaskAction) => action.payload),
        switchMap(workOrder => this.workOrderService.startWorkOrderTask(workOrder)
            .pipe(
                map(_ => {
                    this.router.navigate(['workOrder/workOrder-tasks/assigned']);
                    return new StartWorkOrderTaskSuccessAction({message: ''});
                }),
                catchError(err => of(new LoadError(err)))
            ))
    );

    @Effect()
    completeWorkOrderTask$: Observable<Action> = this.actions$.pipe(
        ofType(COMPLETE_WORK_ORDER_TASK),
        map((action: CompleteWorkOrderTaskAction) => action.payload),
        switchMap(payload => this.workOrderService.completeWorkOrderTask(payload.taskId)
            .pipe(
                map(_ => {
                    this.router.navigate(['workOrder/workOrder-tasks/assigned']);
                    return new CompleteWorkOrderTaskSuccessAction({message: ''});
                }),
                catchError(err => of(new LoadError(err)))
            )
        )
    );

    @Effect()
    claimWorkOrderTask$: Observable<Action> = this.actions$.pipe(
        ofType(CLAIM_WORK_ORDER_TASK),
        map((action: ClaimWorkOrderTaskAction) => action.payload),
        switchMap(payload =>
            this.workOrderService.claimWorkOrderTask(payload.taskIds)
                .pipe(
                    map(_ => {
                        this.router.navigate(['workOrder/workOrder-tasks/assigned']);
                        return new ClaimWorkOrderTaskSuccessAction({message: ''});
                    }),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    releaseWorkOrderTask$: Observable<Action> = this.actions$.pipe(
        ofType(RELEASE_WORK_ORDER_TASK),
        map((action: ReleaseWorkOrderTaskAction) => action.payload),
        switchMap(payload =>
            this.workOrderService.completeWorkOrderTask(payload.taskId)
                .pipe(
                    map(_ => {
                        this.router.navigate(['workOrder/workOrder-tasks/assigned']);
                        return new ReleaseWorkOrderTaskSuccessAction({message: ''});
                    }),
                    catchError(err => of(new LoadError(err)))
                )
        )
    );

    @Effect()
    removeWorkOrderTask$: Observable<Action> = this.actions$.pipe(
        ofType(REMOVE_WORK_ORDER_TASK),
        map((action: RemoveWorkOrderTaskAction) => action.payload),
        switchMap(payload => this.workOrderService.removeWorkOrderTask(payload.taskId)
            .pipe(
                map(message => new RemoveWorkOrderTaskSuccessAction({message: 'success'})),
                catchError(err => of(new LoadError(err)))
            )),
    );

    @Effect()
    updateWorkOrder$ = this.actions$.pipe(
        ofType(UPDATE_WORK_ORDER),
        map((action: UpdateWorkOrderAction) => action.payload),
        switchMap(workOrder => this.workOrderService.updateWorkOrder(workOrder)
            .pipe(
                concatMap(workOrder =>
                    [new UpdateWorkOrderSuccessAction({message: 'success'}), new ReloadWorkOrderPageAction()]),
                catchError(err => of(new LoadError(err)))
            )),
    );

    @Effect()
    addActivity$ = this.actions$.pipe(
        ofType(ADD_ACTIVITY),
        map((action: AddActivityAction) => action.payload),
        switchMap(payload =>
            this.workOrderService.addActivity(
                payload.workOrder,
                payload.workOrderItem
            ).pipe(
                map(item => new AddActivitySuccessAction(item)),
                withLatestFrom(this.store.pipe(select(selectWorkOrderTask))),
                map((latest) => latest[1]),
                map((task) => new FindActivitiesAction({workOrder: task.workOrder})),
                catchError(err => of(new LoadError(err)))
            )
        )
    );

    @Effect()
    updateActivity$ = this.actions$.pipe(
        ofType(UPDATE_ACTIVITY),
        map((action: UpdateActivityAction) => action.payload),
        switchMap(payload =>
            this.workOrderService.updateActivity(
                payload.workOrder,
                payload.workOrderItem
            ).pipe(
                map(item => new UpdateActivitySuccessAction(item)),
                withLatestFrom(this.store.pipe(select(selectWorkOrderTask))),
                map((latest) => latest[1]),
                map((task) => new FindActivitiesAction({workOrder: task.workOrder})),
                catchError(err => of(new LoadError(err)))
            )
        )
    );

    @Effect()
    deleteActivity$ = this.actions$.pipe(
        ofType(REMOVE_ACTIVITY),
        map((action: RemoveActivityAction) => action.payload),
        switchMap(payload =>
            this.workOrderService.deleteActivity(
                payload.workOrder,
                payload.workOrderItem
            ).pipe(
                map(message => new RemoveActivitySuccessAction({message: 'success'})),
                withLatestFrom(this.store.pipe(select(selectWorkOrderTask))),
                map((latest) => latest[1]),
                map((task) => new FindActivitiesAction({workOrder: task.workOrder})),
                catchError(err => of(new LoadError(err)))
            )
        ),
    );

    @Effect()
    public reloadWorkOrderPage$: Observable<Action> = this.actions$.pipe(
        ofType(RELOAD_WORK_ORDER_PAGE),
        withLatestFrom(this.store.select(selectWorkOrder)),
        concatMap(([action, workOrder]: [Action, WorkOrder]) =>
            [new FindActivitiesAction({workOrder: workOrder})]),
    );
}
