import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {Injectable} from '@angular/core';
import {from, Observable, of} from 'rxjs';
import {map, mergeMap, switchMap} from 'rxjs/operators';
import {CommonService} from '../../../services/common.service';
import {catchError} from "rxjs/internal/operators";
import {LoadError} from "../../../static/app.action";
import {
    FIND_ALL_WORK_ORDERS,
    FIND_PAGED_WORK_ORDERS, FindAllWorkOrdersSuccessAction,
    FindPagedWorkOrdersAction,
    FindPagedWorkOrdersSuccessAction,
    REMOVE_WORK_ORDER,
    RemoveWorkOrderAction,
    RemoveWorkOrderSuccessAction,
    SAVE_WORK_ORDER,
    SaveWorkOrderAction,
    SaveWorkOrderSuccessAction,
    UPDATE_WORK_ORDER,
    UpdateWorkOrderAction,
    UpdateWorkOrderSuccessAction
} from './work-order.action';
import {WorkOrderService} from "../../../services/work-order.service";


@Injectable()
export class WorkOrderEffects {

    constructor(private actions$: Actions,
                private workOrderService: WorkOrderService) {
    }

    @Effect()
    public findAllWorkOrders$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_WORK_ORDERS),
            switchMap(payload => this.workOrderService.findWorkOrders()),
            map(result => new FindAllWorkOrdersSuccessAction(result)),);


    @Effect()
    public findPagedWorkOrders$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_WORK_ORDERS),
            map((action: FindPagedWorkOrdersAction) => action.payload),
            switchMap(payload => this.workOrderService.findPagedWorkOrders(payload.filter, payload.page).pipe(
                map(result => new FindPagedWorkOrdersSuccessAction(result)),
                catchError(err => of(new LoadError(err)))
            )));

    @Effect() saveWorkOrder$ = this.actions$
        .pipe(
            ofType(SAVE_WORK_ORDER),
            map((action: SaveWorkOrderAction) => action.payload),
            switchMap((period) => this.workOrderService.saveWorkOrder(period).pipe(
                map((message) => new SaveWorkOrderSuccessAction({message: 'success'})),
                catchError(err => of(new LoadError(err)))
            )),
            mergeMap((action) => from([action, new FindPagedWorkOrdersAction({filter: 'todo', page: 1})])),);

    @Effect() updateWorkOrder$ = this.actions$
        .pipe(
            ofType(UPDATE_WORK_ORDER),
            map((action: UpdateWorkOrderAction) => action.payload),
            switchMap((period) => this.workOrderService.updateWorkOrder(period).pipe(
                map((period) => new UpdateWorkOrderSuccessAction({message: 'success'})),
                catchError(err => of(new LoadError(err)))
            )),
            map(() => new FindPagedWorkOrdersAction({filter: 'todo', page: 1})));

    @Effect() removeWorkOrder$ = this.actions$
        .pipe(
            ofType(REMOVE_WORK_ORDER),
            map((action: RemoveWorkOrderAction) => action.payload),
            switchMap(payload => this.workOrderService.removeWorkOrder(payload).pipe(
                map(message => new RemoveWorkOrderSuccessAction({message: 'success'})),
                catchError(err => of(new LoadError(err)))
            )),
            mergeMap(action => from([action, new FindPagedWorkOrdersAction({filter: 'todo', page: 1})])),);
}
