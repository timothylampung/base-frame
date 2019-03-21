import {Injectable} from "@angular/core";
import {
    FIND_ALL_LOCATIONS,
    FIND_PAGED_LOCATIONS,
    FindAllLocationsSuccessAction,
    FindPagedLocationsAction,
    FindPagedLocationsSuccessAction,
    REMOVE_LOCATION,
    RemoveLocationAction,
    RemoveLocationSuccessAction,
    SAVE_LOCATION,
    SaveLocationAction,
    SaveLocationSuccessAction,
    UPDATE_LOCATION,
    UpdateLocationAction,
    UpdateLocationSuccessAction,
    UPLOAD_LOCATION, UploadLocationAction, UploadLocationErrorAction, UploadLocationSuccessAction
} from "./location-action";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {catchError, map, mergeMap, switchMap} from "rxjs/operators";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";
import {AssetService} from "../../../services/asset.service";
import {LoadError} from "../../../static/app.action";


@Injectable()
export class LocationEffects {

    constructor(private actions$: Actions,
                private assetService: AssetService) {
    }

    @Effect()
    public findAllLocations$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_LOCATIONS),
            switchMap(action => this.assetService.findLocations()),
            map(result => new FindAllLocationsSuccessAction(result)),);

    @Effect()
    public findPagedLocations$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_LOCATIONS),
            map((action: FindPagedLocationsAction) => action.payload),
            switchMap(payload => this.assetService.findPagedLocations(payload.filter, payload.page)),
            map(result => new FindPagedLocationsSuccessAction(result)),);

    @Effect() saveLocation$ = this.actions$
        .pipe(
            ofType(SAVE_LOCATION),
            map((action: SaveLocationAction) => action.payload),
            switchMap((location) => this.assetService.saveLocation(location)),
            map((message) => new SaveLocationSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedLocationsAction({
                filter: '%',
                page: 1
            })])),);

    @Effect() updateLocation$ = this.actions$
        .pipe(
            ofType(UPDATE_LOCATION),
            map((action: UpdateLocationAction) => action.payload),
            switchMap((location) => this.assetService.updateLocation(location)),
            map((location) => new UpdateLocationSuccessAction({message: 'success'})),
            switchMap(() => this.assetService.findLocations()),
            map((locations) => new FindPagedLocationsAction({filter: '%', page: 1})));

    @Effect() removeLocation$ = this.actions$
        .pipe(
            ofType(REMOVE_LOCATION),
            map((action: RemoveLocationAction) => action.payload),
            switchMap(payload => this.assetService.removeLocation(payload)),
            map(message => new RemoveLocationSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedLocationsAction({
                filter: '%',
                page: 1
            })])),);

    @Effect()
    public uploadLocation$ = this.actions$.pipe(
        ofType(UPLOAD_LOCATION),
        map((action: UploadLocationAction) => action.payload.file),
        switchMap(file => this.assetService.uploadLocation(file)
            .pipe(
                switchMap(() => [
                    new UploadLocationSuccessAction({message: ''}),
                    new FindPagedLocationsAction({filter: '%', page: 1})
                ]),
                catchError(err => [new LoadError(err), new UploadLocationErrorAction(err)])
            )
        ));

}
