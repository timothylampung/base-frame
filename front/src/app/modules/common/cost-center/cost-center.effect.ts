import {Injectable} from "@angular/core";
import {
    FIND_ALL_COST_CENTERS,
    FIND_PAGED_COST_CENTERS,
    FindAllCostCentersSuccessAction,
    FindPagedCostCentersAction,
    FindPagedCostCentersSuccessAction,
    REMOVE_COST_CENTER,
    RemoveCostCenterAction,
    RemoveCostCenterSuccessAction,
    SAVE_COST_CENTER,
    SaveCostCenterAction,
    SaveCostCenterSuccessAction,
    UPDATE_COST_CENTER,
    UpdateCostCenterAction,
    UpdateCostCenterSuccessAction
} from "./cost-center.action";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {CommonService} from "../../../services";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";

@Injectable()
export class CostCenterEffects {

    constructor(private actions$: Actions,
                private commonService: CommonService) {
    }

    @Effect()
    public findAllCostCenters$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_COST_CENTERS),
            switchMap(payload => this.commonService.findCostCenters()),
            map(result => new FindAllCostCentersSuccessAction(result)),);

    @Effect()
    public findPagedCostCenters$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_COST_CENTERS),
            map((action: FindPagedCostCentersAction) => action.payload),
            switchMap(payload => this.commonService.findPagedCostCenters(payload.filter, payload.page)),
            map(result => new FindPagedCostCentersSuccessAction(result)),);

    @Effect() saveCostCenter$ = this.actions$
        .pipe(
            ofType(SAVE_COST_CENTER),
            map((action: SaveCostCenterAction) => action.payload),
            switchMap((costCenter) => this.commonService.saveCostCenter(costCenter)),
            map((message) => new SaveCostCenterSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedCostCentersAction({filter: 'todo', page: 1})])),);

    @Effect() updateCostCenter$ = this.actions$
        .pipe(
            ofType(UPDATE_COST_CENTER),
            map((action: UpdateCostCenterAction) => action.payload),
            switchMap((costCenter) => this.commonService.updateCostCenter(costCenter)),
            map((costCenter) => new UpdateCostCenterSuccessAction({message: 'success'})),
            switchMap(() => this.commonService.findCostCenters()),
            map((costCenters) => new FindPagedCostCentersAction({filter: 'todo', page: 1})));

    @Effect() removeCostCenter$ = this.actions$
        .pipe(
            ofType(REMOVE_COST_CENTER),
            map((action: RemoveCostCenterAction) => action.payload),
            switchMap(payload => this.commonService.removeCostCenter(payload)),
            map(message => new RemoveCostCenterSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedCostCentersAction({filter: 'todo', page: 1})])),);
}
