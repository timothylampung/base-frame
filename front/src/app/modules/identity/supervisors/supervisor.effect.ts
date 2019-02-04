import {IdentityService} from "../../../services/identity.service";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {
    FIND_ALL_SUPERVISORS,
    FIND_PAGED_SUPERVISORS,
    FindAllSupervisorsSuccessAction,
    FindPagedSupervisorsAction,
    FindPagedSupervisorsSuccessAction, REMOVE_SUPERVISOR, RemoveSupervisorAction, RemoveSupervisorSuccessAction,
    SAVE_SUPERVISOR,
    SaveSupervisorAction,
    SaveSupervisorSuccessAction, UPDATE_SUPERVISOR, UpdateSupervisorAction, UpdateSupervisorSuccessAction
} from "./supervisor.action";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {Injectable} from "@angular/core";


@Injectable()
export class SupervisorEffects {

    constructor(private actions$: Actions,
                private identityService: IdentityService) {
    }

    @Effect()
    public findAllSupervisors$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_SUPERVISORS),
            switchMap(payload => this.identityService.findSupervisors()),
            map(result => new FindAllSupervisorsSuccessAction(result)),);

    @Effect()
    public findPagedSupervisors$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_SUPERVISORS),
            map((action: FindPagedSupervisorsAction) => action.payload),
            switchMap(payload => this.identityService.findPagedSupervisors(payload.filter, payload.page)),
            map(result => new FindPagedSupervisorsSuccessAction(result)),);

    @Effect() saveSupervisor$ = this.actions$
        .pipe(
            ofType(SAVE_SUPERVISOR),
            map((action: SaveSupervisorAction) => action.payload),
            switchMap((supervisor) => this.identityService.saveSupervisor(supervisor)),
            map((message) => new SaveSupervisorSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedSupervisorsAction({filter: 'todo', page: 1})])),);

    @Effect() updateSupervisor$ = this.actions$
        .pipe(
            ofType(UPDATE_SUPERVISOR),
            map((action: UpdateSupervisorAction) => action.payload),
            switchMap((supervisor) => this.identityService.updateSupervisor(supervisor)),
            map((supervisor) => new UpdateSupervisorSuccessAction({message: 'success'})),
            switchMap(() => this.identityService.findSupervisors()),
            map((supervisors) => new FindPagedSupervisorsAction({filter: 'todo', page: 1})));

    @Effect() removeSupervisor$ = this.actions$
        .pipe(
            ofType(REMOVE_SUPERVISOR),
            map((action: RemoveSupervisorAction) => action.payload),
            switchMap(payload => this.identityService.removeSupervisor(payload)),
            map(message => new RemoveSupervisorSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedSupervisorsAction({filter: 'todo', page: 1})])),);
}
