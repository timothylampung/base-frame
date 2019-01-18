import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {Injectable} from '@angular/core';
import {from, Observable, of} from 'rxjs';
import {map, mergeMap, switchMap} from 'rxjs/operators';
import {CommonService} from '../../../services/common.service';
import {catchError} from "rxjs/internal/operators";
import {LoadError} from "../../../static/app.action";
import {
    FIND_ALL_PERIODS,
    FIND_PAGED_PERIODS, FindAllPeriodsSuccessAction,
    FindPagedPeriodsAction,
    FindPagedPeriodsSuccessAction,
    REMOVE_PERIOD,
    RemovePeriodAction,
    RemovePeriodSuccessAction,
    SAVE_PERIOD,
    SavePeriodAction,
    SavePeriodSuccessAction,
    UPDATE_PERIOD,
    UpdatePeriodAction,
    UpdatePeriodSuccessAction
} from './period.action';


@Injectable()
export class PeriodEffects {

    constructor(private actions$: Actions,
                private commonService: CommonService) {
    }

    @Effect()
    public findAllPeriods$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_PERIODS),
            switchMap(payload => this.commonService.findPeriods()),
            map(result => new FindAllPeriodsSuccessAction(result)),);


    @Effect()
    public findPagedPeriods$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_PERIODS),
            map((action: FindPagedPeriodsAction) => action.payload),
            switchMap(payload => this.commonService.findPagedPeriods(payload.filter, payload.page).pipe(
                map(result => new FindPagedPeriodsSuccessAction(result)),
                catchError(err => of(new LoadError(err)))
            )));

    @Effect() savePeriod$ = this.actions$
        .pipe(
            ofType(SAVE_PERIOD),
            map((action: SavePeriodAction) => action.payload),
            switchMap((period) => this.commonService.savePeriod(period).pipe(
                map((message) => new SavePeriodSuccessAction({message: 'success'})),
                catchError(err => of(new LoadError(err)))
            )),
            mergeMap((action) => from([action, new FindPagedPeriodsAction({filter: 'todo', page: 1})])),);

    @Effect() updatePeriod$ = this.actions$
        .pipe(
            ofType(UPDATE_PERIOD),
            map((action: UpdatePeriodAction) => action.payload),
            switchMap((period) => this.commonService.updatePeriod(period).pipe(
                map((period) => new UpdatePeriodSuccessAction({message: 'success'})),
                catchError(err => of(new LoadError(err)))
            )),
            map(() => new FindPagedPeriodsAction({filter: 'todo', page: 1})));

    @Effect() removePeriod$ = this.actions$
        .pipe(
            ofType(REMOVE_PERIOD),
            map((action: RemovePeriodAction) => action.payload),
            switchMap(payload => this.commonService.removePeriod(payload).pipe(
                map(message => new RemovePeriodSuccessAction({message: 'success'})),
                catchError(err => of(new LoadError(err)))
            )),
            mergeMap(action => from([action, new FindPagedPeriodsAction({filter: 'todo', page: 1})])),);
}
