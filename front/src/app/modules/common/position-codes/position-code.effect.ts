import {map, mergeMap, switchMap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {
    FIND_PAGED_POSITION_CODES,
    FindPagedPositionCodesAction,
    FindPagedPositionCodesSuccessAction,
    REMOVE_POSITION_CODE,
    RemovePositionCodeAction,
    RemovePositionCodeSuccessAction,
    SAVE_POSITION_CODE,
    SavePositionCodeAction,
    SavePositionCodeSuccessAction,
    UPDATE_POSITION_CODE,
    UpdatePositionCodeAction,
    UpdatePositionCodeSuccessAction
} from './position-code.action';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {from, Observable} from 'rxjs';
import {CommonService} from '../../../services/common.service';

@Injectable()
export class PositionCodeEffects {

    constructor(private actions$: Actions,
                private commonService: CommonService) {
    }

    @Effect()
    public findPagedPositionCodes$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_POSITION_CODES),
            map((action: FindPagedPositionCodesAction) => action.payload),
            switchMap(payload => this.commonService.findPagedPositionCodes(payload.filter, payload.page)),
            map(result => new FindPagedPositionCodesSuccessAction(result)),);

    @Effect() savePositionCode$ = this.actions$
        .pipe(
            ofType(SAVE_POSITION_CODE),
            map((action: SavePositionCodeAction) => action.payload),
            switchMap((positionCode) => this.commonService.savePositionCode(positionCode)),
            map((message) => new SavePositionCodeSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedPositionCodesAction({filter: 'todo', page: 1})])),);

    @Effect() updatePositionCode$ = this.actions$
        .pipe(
            ofType(UPDATE_POSITION_CODE),
            map((action: UpdatePositionCodeAction) => action.payload),
            switchMap((positionCode) => this.commonService.updatePositionCode(positionCode)),
            map((positionCode) => new UpdatePositionCodeSuccessAction({message: 'success'})),
            switchMap(() => this.commonService.findPositionCodes()),
            map((positionCodes) => new FindPagedPositionCodesAction({filter: 'todo', page: 1})));

    @Effect() removePositionCode$ = this.actions$
        .pipe(
            ofType(REMOVE_POSITION_CODE),
            map((action: RemovePositionCodeAction) => action.payload),
            switchMap(payload => this.commonService.removePositionCode(payload)),
            map(message => new RemovePositionCodeSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedPositionCodesAction({filter: 'todo', page: 1})])),);
}
