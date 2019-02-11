import {Injectable} from "@angular/core";
import {
    FIND_ALL_COMPONENTS, FIND_PAGED_COMPONENTS,
    FindAllComponentsSuccessAction, FindPagedComponentsAction,
    FindPagedComponentsSuccessAction, REMOVE_COMPONENT, RemoveComponentAction, RemoveComponentSuccessAction,
    SAVE_COMPONENT,
    SaveComponentAction, SaveComponentSuccessAction, UPDATE_COMPONENT,
    UpdateComponentAction, UpdateComponentSuccessAction
} from "./component-action";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";
import {InventoryService} from "../../../services/inventory.service";


@Injectable()
export class ComponentEffects {

    constructor(private actions$: Actions,
                private inventoryService: InventoryService) {
    }

    @Effect()
    public findAllComponents$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_COMPONENTS),
            switchMap(action => this.inventoryService.findComponents()),
            map(result => new FindAllComponentsSuccessAction(result)),);

    @Effect()
    public findPagedComponents$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_COMPONENTS),
            map((action: FindPagedComponentsAction) => action.payload),
            switchMap(payload => this.inventoryService.findPagedComponents(payload.filter, payload.page)),
            map(result => new FindPagedComponentsSuccessAction(result)),);

    @Effect() saveComponent$ = this.actions$
        .pipe(
            ofType(SAVE_COMPONENT),
            map((action: SaveComponentAction) => action.payload),
            switchMap((component) => this.inventoryService.saveComponent(component)),
            map((message) => new SaveComponentSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedComponentsAction({filter: 'todo', page: 1})])),);

    @Effect() updateComponent$ = this.actions$
        .pipe(
            ofType(UPDATE_COMPONENT),
            map((action: UpdateComponentAction) => action.payload),
            switchMap((component) => this.inventoryService.updateComponent(component)),
            map((component) => new UpdateComponentSuccessAction({message: 'success'})),
            switchMap(() => this.inventoryService.findComponents()),
            map((components) => new FindPagedComponentsAction({filter: 'todo', page: 1})));

    @Effect() removeComponent$ = this.actions$
        .pipe(
            ofType(REMOVE_COMPONENT),
            map((action: RemoveComponentAction) => action.payload),
            switchMap(payload => this.inventoryService.removeComponent(payload)),
            map(message => new RemoveComponentSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedComponentsAction({filter: 'todo', page: 1})])),);
}
