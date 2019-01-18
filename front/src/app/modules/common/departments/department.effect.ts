import {map, mergeMap, switchMap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {
    FIND_ALL_DEPARTMENTS,
    FIND_PAGED_DEPARTMENTS,
    FindAllDepartmentsSuccessAction,
    FindPagedDepartmentsAction,
    FindPagedDepartmentsSuccessAction,
    REMOVE_DEPARTMENT,
    RemoveDepartmentAction,
    RemoveDepartmentSuccessAction,
    SAVE_DEPARTMENT,
    SaveDepartmentAction,
    SaveDepartmentSuccessAction,
    UPDATE_DEPARTMENT,
    UpdateDepartmentAction,
    UpdateDepartmentSuccessAction
} from './department.action';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {from, Observable} from 'rxjs';
import {CommonService} from '../../../services/common.service';

@Injectable()
export class DepartmentEffects {

    constructor(private actions$: Actions,
                private commonService: CommonService) {
    }

    @Effect()
    public findAllDepartments$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_DEPARTMENTS),
            switchMap(payload => this.commonService.findDepartments()),
            map(result => new FindAllDepartmentsSuccessAction(result)),);

    @Effect()
    public findPagedDepartments$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_DEPARTMENTS),
            map((action: FindPagedDepartmentsAction) => action.payload),
            switchMap(payload => this.commonService.findPagedDepartments(payload.filter, payload.page)),
            map(result => new FindPagedDepartmentsSuccessAction(result)),);

    @Effect() saveDepartment$ = this.actions$
        .pipe(
            ofType(SAVE_DEPARTMENT),
            map((action: SaveDepartmentAction) => action.payload),
            switchMap((department) => this.commonService.saveDepartment(department)),
            map((message) => new SaveDepartmentSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedDepartmentsAction({filter: 'todo', page: 1})])),);

    @Effect() updateDepartment$ = this.actions$
        .pipe(
            ofType(UPDATE_DEPARTMENT),
            map((action: UpdateDepartmentAction) => action.payload),
            switchMap((department) => this.commonService.updateDepartment(department)),
            map((department) => new UpdateDepartmentSuccessAction({message: 'success'})),
            switchMap(() => this.commonService.findDepartments()),
            map((departments) => new FindPagedDepartmentsAction({filter: 'todo', page: 1})));

    @Effect() removeDepartment$ = this.actions$
        .pipe(
            ofType(REMOVE_DEPARTMENT),
            map((action: RemoveDepartmentAction) => action.payload),
            switchMap(payload => this.commonService.removeDepartment(payload)),
            map(message => new RemoveDepartmentSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedDepartmentsAction({filter: 'todo', page: 1})])),);
}
