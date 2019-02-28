import {map, mergeMap, switchMap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {from, Observable} from 'rxjs';
import {
    FIND_ALL_ACTORS,
    FIND_PAGED_ACTORS,
    FindAllActorsSuccessAction,
    FindPagedActorsAction,
    FindPagedActorsSuccessAction, REMOVE_ACTOR, RemoveActorAction, RemoveActorSuccessAction,
    SAVE_ACTOR,
    SaveActorAction,
    SaveActorSuccessAction,
    UPDATE_ACTOR,
    UpdateActorAction,
    UpdateActorSuccessAction
} from "./actor.action";
import {IdentityService} from "../../../services/identity.service";

@Injectable()
export class ActorEffect {

    constructor(private actions$: Actions,
                private identityService: IdentityService) {
    }

    @Effect()
    public findAllActors$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_ACTORS),
            switchMap(action => this.identityService.findActors()),
            map(result => new FindAllActorsSuccessAction(result)),);

    @Effect()
    public findPagedActors$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_ACTORS),
            map((action: FindPagedActorsAction) => action.payload),
            switchMap(payload => this.identityService.findPagedActors(payload.filter, payload.page)),
            map(result => new FindPagedActorsSuccessAction(result)),);
}
