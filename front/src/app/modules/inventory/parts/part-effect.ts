import {Injectable} from "@angular/core";
import {
    FIND_ALL_PARTS, FIND_PAGED_PARTS,
    FindAllPartsSuccessAction, FindPagedPartsAction,
    FindPagedPartsSuccessAction, REMOVE_PART, RemovePartAction, RemovePartSuccessAction,
    SAVE_PART,
    SavePartAction, SavePartSuccessAction, UPDATE_PART,
    UpdatePartAction, UpdatePartSuccessAction
} from "./part-action";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";
import {InventoryService} from "../../../services/inventory.service";


@Injectable()
export class PartEffects {

    constructor(private actions$: Actions,
                private inventoryService: InventoryService) {
    }

    @Effect()
    public findAllParts$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_PARTS),
            switchMap(action => this.inventoryService.findParts()),
            map(result => new FindAllPartsSuccessAction(result)),);

    @Effect()
    public findPagedParts$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_PARTS),
            map((action: FindPagedPartsAction) => action.payload),
            switchMap(payload => this.inventoryService.findPagedParts(payload.filter, payload.page)),
            map(result => new FindPagedPartsSuccessAction(result)),);

    @Effect() savePart$ = this.actions$
        .pipe(
            ofType(SAVE_PART),
            map((action: SavePartAction) => action.payload),
            switchMap((part) => this.inventoryService.savePart(part)),
            map((message) => new SavePartSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedPartsAction({filter: '%', page: 1})])),);

    @Effect() updatePart$ = this.actions$
        .pipe(
            ofType(UPDATE_PART),
            map((action: UpdatePartAction) => action.payload),
            switchMap((part) => this.inventoryService.updatePart(part)),
            map((part) => new UpdatePartSuccessAction({message: 'success'})),
            switchMap(() => this.inventoryService.findParts()),
            map((parts) => new FindPagedPartsAction({filter: 'todo', page: 1})));

    @Effect() removePart$ = this.actions$
        .pipe(
            ofType(REMOVE_PART),
            map((action: RemovePartAction) => action.payload),
            switchMap(payload => this.inventoryService.removePart(payload)),
            map(message => new RemovePartSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedPartsAction({filter: 'todo', page: 1})])),);
}
