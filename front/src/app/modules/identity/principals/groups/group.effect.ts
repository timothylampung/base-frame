import {map, mergeMap, switchMap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {
    FIND_PAGED_GROUPS,
    FindPagedGroupsAction,
    FindPagedGroupsSuccessAction,
    REMOVE_GROUP,
    RemoveGroupAction,
    RemoveGroupSuccessAction,
    SAVE_GROUP,
    SaveGroupAction,
    SaveGroupSuccessAction,
    UPDATE_GROUP,
    UpdateGroupAction,
    UpdateGroupSuccessAction
} from './group.action';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {from, Observable} from 'rxjs';
import {IdentityService} from '../../../../services/identity.service';

@Injectable()
export class GroupEffect {

    constructor(private actions$: Actions,
                private identityService: IdentityService) {
    }

    @Effect()
    public findPagedGroups$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_GROUPS),
            map((action: FindPagedGroupsAction) => action.payload),
            switchMap(payload => this.identityService.findPagedGroups(payload.filter, payload.page)),
            map(result => new FindPagedGroupsSuccessAction(result)));

    @Effect() saveGroup$ = this.actions$
        .pipe(
            ofType(SAVE_GROUP),
            map((action: SaveGroupAction) => action.payload),
            switchMap((group) => this.identityService.saveGroup(group)),
            map((message) => new SaveGroupSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedGroupsAction({filter: '%', page: 1})])));

    @Effect() updateGroup$ = this.actions$
        .pipe(
            ofType(UPDATE_GROUP),
            map((action: UpdateGroupAction) => action.payload),
            switchMap((group) => this.identityService.updateGroup(group)),
            map((group) => new UpdateGroupSuccessAction({message: 'success'})),
            switchMap(() => this.identityService.findGroups()),
            map((groups) => new FindPagedGroupsAction({filter: '%', page: 1})));

    @Effect() removeGroup$ = this.actions$
        .pipe(
            ofType(REMOVE_GROUP),
            map((action: RemoveGroupAction) => action.payload),
            switchMap(payload => this.identityService.removeGroup(payload)),
            map(message => new RemoveGroupSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedGroupsAction({filter: '%', page: 1})])),);
}
