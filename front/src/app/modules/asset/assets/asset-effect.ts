import {Injectable} from "@angular/core";
import {
    FIND_ALL_ASSETS, FIND_PAGED_ASSETS,
    FindAllAssetsSuccessAction, FindPagedAssetsAction,
    FindPagedAssetsSuccessAction, REMOVE_ASSET, RemoveAssetAction, RemoveAssetSuccessAction,
    SAVE_ASSET,
    SaveAssetAction, SaveAssetSuccessAction, UPDATE_ASSET,
    UpdateAssetAction, UpdateAssetSuccessAction
} from "./asset-action";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";
import {AssetService} from "../../../services/asset.service";


@Injectable()
export class AssetEffects {

    constructor(private actions$: Actions,
                private assetService: AssetService) {
    }

    @Effect()
    public findAllAssets$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_ASSETS),
            switchMap(action => this.assetService.findAssets()),
            map(result => new FindAllAssetsSuccessAction(result)),);

    @Effect()
    public findPagedAssets$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_ASSETS),
            map((action: FindPagedAssetsAction) => action.payload),
            switchMap(payload => this.assetService.findPagedAssets(payload.filter, payload.page)),
            map(result => new FindPagedAssetsSuccessAction(result)),);

    @Effect() saveAsset$ = this.actions$
        .pipe(
            ofType(SAVE_ASSET),
            map((action: SaveAssetAction) => action.payload),
            switchMap((asset) => this.assetService.saveAsset(asset)),
            map((message) => new SaveAssetSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedAssetsAction({filter: 'todo', page: 1})])),);

    @Effect() updateAsset$ = this.actions$
        .pipe(
            ofType(UPDATE_ASSET),
            map((action: UpdateAssetAction) => action.payload),
            switchMap((asset) => this.assetService.updateAsset(asset)),
            map((asset) => new UpdateAssetSuccessAction({message: 'success'})),
            switchMap(() => this.assetService.findAssets()),
            map((assets) => new FindPagedAssetsAction({filter: 'todo', page: 1})));

    @Effect() removeAsset$ = this.actions$
        .pipe(
            ofType(REMOVE_ASSET),
            map((action: RemoveAssetAction) => action.payload),
            switchMap(payload => this.assetService.removeAsset(payload)),
            map(message => new RemoveAssetSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedAssetsAction({filter: 'todo', page: 1})])),);
}
