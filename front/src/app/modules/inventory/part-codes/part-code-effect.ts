import {Injectable} from "@angular/core";
import {
    FIND_ALL_PART_CODES, FIND_PAGED_PART_CODES,
    FindAllPartCodesSuccessAction, FindPagedPartCodesAction,
    FindPagedPartCodesSuccessAction, REMOVE_PART_CODE, RemovePartCodeAction, RemovePartCodeSuccessAction,
    SAVE_PART_CODE,
    SavePartCodeAction, SavePartCodeSuccessAction, UPDATE_PART_CODE,
    UpdatePartCodeAction, UpdatePartCodeSuccessAction
} from "./part-code-action";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";
import {InventoryService} from "../../../services/inventory.service";


@Injectable()
export class PartCodeEffects {

    constructor(private actions$: Actions,
                private inventoryService: InventoryService) {
    }

    @Effect()
    public findAllPartCodes$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_PART_CODES),
            switchMap(action => this.inventoryService.findPartCodes()),
            map(result => new FindAllPartCodesSuccessAction(result)),);

    @Effect()
    public findPagedPartCodes$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_PART_CODES),
            map((action: FindPagedPartCodesAction) => action.payload),
            switchMap(payload => this.inventoryService.findPagedPartCodes(payload.filter, payload.page)),
            map(result => new FindPagedPartCodesSuccessAction(result)),);

    @Effect() savePartCode$ = this.actions$
        .pipe(
            ofType(SAVE_PART_CODE),
            map((action: SavePartCodeAction) => action.payload),
            switchMap((partCode) => this.inventoryService.savePartCode(partCode)),
            map((message) => new SavePartCodeSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedPartCodesAction({filter: 'todo', page: 1})])),);

    @Effect() updatePartCode$ = this.actions$
        .pipe(
            ofType(UPDATE_PART_CODE),
            map((action: UpdatePartCodeAction) => action.payload),
            switchMap((part) => this.inventoryService.updatePartCode(part)),
            map((part) => new UpdatePartCodeSuccessAction({message: 'success'})),
            switchMap(() => this.inventoryService.findPartCodes()),
            map((parts) => new FindPagedPartCodesAction({filter: 'todo', page: 1})));

    @Effect() removePartCode$ = this.actions$
        .pipe(
            ofType(REMOVE_PART_CODE),
            map((action: RemovePartCodeAction) => action.payload),
            switchMap(payload => this.inventoryService.removePartCode(payload)),
            map(message => new RemovePartCodeSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedPartCodesAction({filter: 'todo', page: 1})])),);
}
