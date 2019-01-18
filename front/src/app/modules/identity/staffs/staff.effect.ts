import {map, mergeMap, switchMap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {
    FIND_ALL_STAFFS,
    FIND_PAGED_STAFFS,
    FindAllStaffsSuccessAction,
    FindPagedStaffsAction,
    FindPagedStaffsSuccessAction,
    REMOVE_STAFF,
    RemoveStaffAction,
    RemoveStaffSuccessAction,
    SAVE_STAFF,
    SaveStaffAction,
    SaveStaffSuccessAction,
    UPDATE_STAFF,
    UpdateStaffAction,
    UpdateStaffSuccessAction
} from './staff.action';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {from, Observable} from 'rxjs';
import {IdentityService} from "../../../services/identity.service";

@Injectable()
export class StaffEffects {

    constructor(private actions$: Actions,
                private identityService: IdentityService) {
    }

    @Effect()
    public findAllStaffs$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_STAFFS),
            switchMap(payload => this.identityService.findStaffs()),
            map(result => new FindAllStaffsSuccessAction(result)),);

    @Effect()
    public findPagedStaffs$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_STAFFS),
            map((action: FindPagedStaffsAction) => action.payload),
            switchMap(payload => this.identityService.findPagedStaffs(payload.filter, payload.page)),
            map(result => new FindPagedStaffsSuccessAction(result)),);

    @Effect() saveStaff$ = this.actions$
        .pipe(
            ofType(SAVE_STAFF),
            map((action: SaveStaffAction) => action.payload),
            switchMap((staff) => this.identityService.saveStaff(staff)),
            map((message) => new SaveStaffSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedStaffsAction({filter: 'todo', page: 1})])),);

    @Effect() updateStaff$ = this.actions$
        .pipe(
            ofType(UPDATE_STAFF),
            map((action: UpdateStaffAction) => action.payload),
            switchMap((staff) => this.identityService.updateStaff(staff)),
            map((staff) => new UpdateStaffSuccessAction({message: 'success'})),
            switchMap(() => this.identityService.findStaffs()),
            map((staffs) => new FindPagedStaffsAction({filter: 'todo', page: 1})));

    @Effect() removeStaff$ = this.actions$
        .pipe(
            ofType(REMOVE_STAFF),
            map((action: RemoveStaffAction) => action.payload),
            switchMap(payload => this.identityService.removeStaff(payload)),
            map(message => new RemoveStaffSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedStaffsAction({filter: 'todo', page: 1})])),);
}
