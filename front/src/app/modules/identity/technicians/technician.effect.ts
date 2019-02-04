import {map, mergeMap, switchMap} from 'rxjs/operators';
import {Injectable} from '@angular/core';

import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {from, Observable} from 'rxjs';
import {IdentityService} from "../../../services/identity.service";
import {
    FIND_ALL_TECHNICIANS,
    FIND_PAGED_TECHNICIANS,
    FindAllTechniciansSuccessAction,
    FindPagedTechniciansAction,
    FindPagedTechniciansSuccessAction, REMOVE_TECHNICIAN, RemoveTechnicianAction, RemoveTechnicianSuccessAction,
    SAVE_TECHNICIAN,
    SaveTechnicianAction, SaveTechnicianSuccessAction,
    UPDATE_TECHNICIAN, UpdateTechnicianAction, UpdateTechnicianSuccessAction
} from "./technician.action";

@Injectable()
export class TechnicianEffects {

    constructor(private actions$: Actions,
                private identityService: IdentityService) {
    }

    @Effect()
    public findAllTechnicians$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_TECHNICIANS),
            switchMap(payload => this.identityService.findTechnicians()),
            map(result => new FindAllTechniciansSuccessAction(result)),);

    @Effect()
    public findPagedTechnicians$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_TECHNICIANS),
            map((action: FindPagedTechniciansAction) => action.payload),
            switchMap(payload => this.identityService.findPagedTechnicians(payload.filter, payload.page)),
            map(result => new FindPagedTechniciansSuccessAction(result)),);

    @Effect() saveTechnician$ = this.actions$
        .pipe(
            ofType(SAVE_TECHNICIAN),
            map((action: SaveTechnicianAction) => action.payload),
            switchMap((technician) => this.identityService.saveTechnician(technician)),
            map((message) => new SaveTechnicianSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedTechniciansAction({filter: 'todo', page: 1})])),);

    @Effect() updateTechnician$ = this.actions$
        .pipe(
            ofType(UPDATE_TECHNICIAN),
            map((action: UpdateTechnicianAction) => action.payload),
            switchMap((technician) => this.identityService.updateTechnician(technician)),
            map((technician) => new UpdateTechnicianSuccessAction({message: 'success'})),
            switchMap(() => this.identityService.findTechnicians()),
            map((technicians) => new FindPagedTechniciansAction({filter: 'todo', page: 1})));

    @Effect() removeTechnician$ = this.actions$
        .pipe(
            ofType(REMOVE_TECHNICIAN),
            map((action: RemoveTechnicianAction) => action.payload),
            switchMap(payload => this.identityService.removeTechnician(payload)),
            map(message => new RemoveTechnicianSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedTechniciansAction({filter: 'todo', page: 1})])),);
}
