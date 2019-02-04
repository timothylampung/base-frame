import {map, mergeMap, switchMap} from 'rxjs/operators';
import {Injectable} from '@angular/core';

import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {from, Observable} from 'rxjs';
import {IdentityService} from "../../../services/identity.service";
import {
    FIND_ALL_FACILITY_MANAGERS,
    FIND_PAGED_FACILITY_MANAGERS,
    FindAllFacilityManagersSuccessAction,
    FindPagedFacilityManagersAction,
    FindPagedFacilityManagersSuccessAction, REMOVE_FACILITY_MANAGER, RemoveFacilityManagerAction, RemoveFacilityManagerSuccessAction,
    SAVE_FACILITY_MANAGER,
    SaveFacilityManagerAction, SaveFacilityManagerSuccessAction,
    UPDATE_FACILITY_MANAGER, UpdateFacilityManagerAction, UpdateFacilityManagerSuccessAction
} from "./facility-manager.action";

@Injectable()
export class FacilityManagerEffects {

    constructor(private actions$: Actions,
                private identityService: IdentityService) {
    }

    @Effect()
    public findAllFacilityManagers$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_FACILITY_MANAGERS),
            switchMap(payload => this.identityService.findFacilityManagers()),
            map(result => new FindAllFacilityManagersSuccessAction(result)),);

    @Effect()
    public findPagedFacilityManagers$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_FACILITY_MANAGERS),
            map((action: FindPagedFacilityManagersAction) => action.payload),
            switchMap(payload => this.identityService.findPagedFacilityManagers(payload.filter, payload.page)),
            map(result => new FindPagedFacilityManagersSuccessAction(result)),);

    @Effect() saveFacilityManager$ = this.actions$
        .pipe(
            ofType(SAVE_FACILITY_MANAGER),
            map((action: SaveFacilityManagerAction) => action.payload),
            switchMap((technician) => this.identityService.saveFacilityManager(technician)),
            map((message) => new SaveFacilityManagerSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedFacilityManagersAction({filter: 'todo', page: 1})])),);

    @Effect() updateFacilityManager$ = this.actions$
        .pipe(
            ofType(UPDATE_FACILITY_MANAGER),
            map((action: UpdateFacilityManagerAction) => action.payload),
            switchMap((technician) => this.identityService.updateFacilityManager(technician)),
            map((technician) => new UpdateFacilityManagerSuccessAction({message: 'success'})),
            switchMap(() => this.identityService.findFacilityManagers()),
            map((technicians) => new FindPagedFacilityManagersAction({filter: 'todo', page: 1})));

    @Effect() removeFacilityManager$ = this.actions$
        .pipe(
            ofType(REMOVE_FACILITY_MANAGER),
            map((action: RemoveFacilityManagerAction) => action.payload),
            switchMap(payload => this.identityService.removeFacilityManager(payload)),
            map(message => new RemoveFacilityManagerSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedFacilityManagersAction({filter: 'todo', page: 1})])),);
}
