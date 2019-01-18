import {map, mergeMap, switchMap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {
    FIND_PAGED_CONFIGURATIONS,
    FindPagedConfigurationsAction,
    FindPagedConfigurationsSuccessAction,
    REMOVE_CONFIGURATION,
    RemoveConfigurationAction,
    RemoveConfigurationSuccessAction,
    SAVE_CONFIGURATION,
    SaveConfigurationAction,
    SaveConfigurationSuccessAction,
    UPDATE_CONFIGURATION,
    UpdateConfigurationAction,
    UpdateConfigurationSuccessAction
} from './configuration.action';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {from, Observable} from 'rxjs';
import {SystemService} from '../../../services/system.service';

@Injectable()
export class ConfigurationEffects {

    constructor(private actions$: Actions,
                private systemService: SystemService) {
    }

    @Effect()
    public findPagedConfigurations$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_CONFIGURATIONS),
            map((action: FindPagedConfigurationsAction) => action.payload),
            switchMap(payload => this.systemService.findPagedConfigurations(payload.filter, payload.page)),
            map(result => new FindPagedConfigurationsSuccessAction(result)));

    @Effect() saveConfiguration$ = this.actions$
        .pipe(
            ofType(SAVE_CONFIGURATION),
            map((action: SaveConfigurationAction) => action.payload),
            switchMap((configuration) => this.systemService.saveConfiguration(configuration)),
            map((message) => new SaveConfigurationSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedConfigurationsAction({filter: '%', page: 1})])));

    @Effect() updateConfiguration$ = this.actions$
        .pipe(
            ofType(UPDATE_CONFIGURATION),
            map((action: UpdateConfigurationAction) => action.payload),
            switchMap((configuration) => this.systemService.updateConfiguration(configuration)),
            map((configuration) => new UpdateConfigurationSuccessAction({message: 'success'})),
            switchMap(() => this.systemService.findConfigurations()),
            map((configurations) => new FindPagedConfigurationsAction({filter: '%', page: 1})));

    @Effect() removeConfiguration$ = this.actions$
        .pipe(
            ofType(REMOVE_CONFIGURATION),
            map((action: RemoveConfigurationAction) => action.payload),
            switchMap(payload => this.systemService.removeConfiguration(payload)),
            map(message => new RemoveConfigurationSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedConfigurationsAction({filter: '%', page: 1})])),);
}
