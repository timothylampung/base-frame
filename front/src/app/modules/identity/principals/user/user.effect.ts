import {map, mergeMap, switchMap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {from, Observable} from 'rxjs';
import {IdentityService} from "../../../../services/identity.service";
import {
    FIND_ALL_USERS,
    FIND_PAGED_USERS,
    FindAllUsersSuccessAction,
    FindPagedUsersAction,
    FindPagedUsersSuccessAction, REMOVE_USER, RemoveUserAction, RemoveUserSuccessAction,
    SAVE_USER,
    SaveUserAction,
    SaveUserSuccessAction,
    UPDATE_USER,
    UpdateUserAction,
    UpdateUserSuccessAction
} from "./user.action";

@Injectable()
export class UserEffect {

    constructor(private actions$: Actions,
                private identityService: IdentityService) {
    }

    @Effect()
    public findAllUsers$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_USERS),
            switchMap(action => this.identityService.findUsers()),
            map(result => new FindAllUsersSuccessAction(result)),);

    @Effect()
    public findPagedUsers$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_USERS),
            map((action: FindPagedUsersAction) => action.payload),
            switchMap(payload => this.identityService.findPagedUsers(payload.filter, payload.page)),
            map(result => new FindPagedUsersSuccessAction(result)),);

    @Effect() saveUser$ = this.actions$
        .pipe(
            ofType(SAVE_USER),
            map((action: SaveUserAction) => action.payload),
            switchMap((bank) => this.identityService.saveUser(bank)),
            map((message) => new SaveUserSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedUsersAction({filter: 'todo', page: 1})])),);

    @Effect() updateUser$ = this.actions$
        .pipe(
            ofType(UPDATE_USER),
            map((action: UpdateUserAction) => action.payload),
            switchMap((bank) => this.identityService.updateUser(bank)),
            map((bank) => new UpdateUserSuccessAction({message: 'success'})),
            switchMap(() => this.identityService.findUsers()),
            map((bankCodes) => new FindPagedUsersAction({filter: 'todo', page: 1})));

    @Effect() removeUser$ = this.actions$
        .pipe(
            ofType(REMOVE_USER),
            map((action: RemoveUserAction) => action.payload),
            switchMap(payload => this.identityService.removeUser(payload)),
            map(message => new RemoveUserSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedUsersAction({filter: 'todo', page: 1})])),);
}
