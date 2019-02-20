import {Injectable} from "@angular/core";
import {
    FIND_ALL_ASSET_CODES, FIND_PAGED_ASSET_CODES,
    FindAllAssetCodesSuccessAction, FindPagedAssetCodesAction,
    FindPagedAssetCodesSuccessAction, REMOVE_ASSET_CODE, RemoveAssetCodeAction, RemoveAssetCodeSuccessAction,
    SAVE_ASSET_CODE,
    SaveAssetCodeAction, SaveAssetCodeSuccessAction, UPDATE_ASSET_CODE,
    UpdateAssetCodeAction, UpdateAssetCodeSuccessAction
} from "./asset-code.action";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";
import {AssetService} from "../../../services/asset.service";


@Injectable()
export class AssetCodeEffects {

    constructor(private actions$: Actions,
                private assetService: AssetService) {
    }

    @Effect()
    public findAllAssetCodes$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_ASSET_CODES),
            switchMap(action => this.assetService.findAssetCodes()),
            map(result => new FindAllAssetCodesSuccessAction(result)),);

    @Effect()
    public findPagedAssetCodes$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_ASSET_CODES),
            map((action: FindPagedAssetCodesAction) => action.payload),
            switchMap(payload => this.assetService.findPagedAssetCodes(payload.filter, payload.page)),
            map(result => new FindPagedAssetCodesSuccessAction(result)),);

    @Effect() saveAssetCode$ = this.actions$
        .pipe(
            ofType(SAVE_ASSET_CODE),
            map((action: SaveAssetCodeAction) => action.payload),
            switchMap((assetCode) => this.assetService.saveAssetCode(assetCode)),
            map((message) => new SaveAssetCodeSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedAssetCodesAction({filter: '%', page: 1})])),);

    @Effect() updateAssetCode$ = this.actions$
        .pipe(
            ofType(UPDATE_ASSET_CODE),
            map((action: UpdateAssetCodeAction) => action.payload),
            switchMap((assetCode) => this.assetService.updateAssetCode(assetCode)),
            map((assetCode) => new UpdateAssetCodeSuccessAction({message: 'success'})),
            switchMap(() => this.assetService.findAssetCodes()),
            map((assetCodes) => new FindPagedAssetCodesAction({filter: '%', page: 1})));

    @Effect() removeAssetCode$ = this.actions$
        .pipe(
            ofType(REMOVE_ASSET_CODE),
            map((action: RemoveAssetCodeAction) => action.payload),
            switchMap(payload => this.assetService.removeAssetCode(payload)),
            map(message => new RemoveAssetCodeSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedAssetCodesAction({filter: '%', page: 1})])),);
}
