import {map, mergeMap, switchMap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {
    FIND_ALL_ACCOUNT_CODES,
    FIND_PAGED_ACCOUNT_CODES,
    FindAllAccountCodesSuccessAction,
    FindPagedAccountCodesAction,
    FindPagedAccountCodesSuccessAction,
    REMOVE_ACCOUNT_CODE,
    RemoveAccountCodeAction,
    RemoveAccountCodeSuccessAction,
    SAVE_ACCOUNT_CODE,
    SaveAccountCodeAction,
    SaveAccountCodeSuccessAction,
    UPDATE_ACCOUNT_CODE,
    UpdateAccountCodeAction,
    UpdateAccountCodeSuccessAction
} from './account-code.action';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Action} from '@ngrx/store';
import {from, Observable} from 'rxjs';
import {CommonService} from '../../../services/common.service';

@Injectable()
export class AccountCodeEffects {

    constructor(private actions$: Actions,
                private commonService: CommonService) {
    }

    @Effect()
    public findAllAccountCodes$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_ACCOUNT_CODES),
            switchMap(action => this.commonService.findAccountCodes()),
            map(result => new FindAllAccountCodesSuccessAction(result)),);

    @Effect()
    public findPagedAccountCodes$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_ACCOUNT_CODES),
            map((action: FindPagedAccountCodesAction) => action.payload),
            switchMap(payload => this.commonService.findPagedAccountCodes(payload.filter, payload.page)),
            map(result => new FindPagedAccountCodesSuccessAction(result)),);

    @Effect() saveAccountCode$ = this.actions$
        .pipe(
            ofType(SAVE_ACCOUNT_CODE),
            map((action: SaveAccountCodeAction) => action.payload),
            switchMap((accountCode) => this.commonService.saveAccountCode(accountCode)),
            map((message) => new SaveAccountCodeSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedAccountCodesAction({filter: 'todo', page: 1})])),);

    @Effect() updateAccountCode$ = this.actions$
        .pipe(
            ofType(UPDATE_ACCOUNT_CODE),
            map((action: UpdateAccountCodeAction) => action.payload),
            switchMap((accountCode) => this.commonService.updateAccountCode(accountCode)),
            map((accountCode) => new UpdateAccountCodeSuccessAction({message: 'success'})),
            switchMap(() => this.commonService.findAccountCodes()),
            map((accountCodes) => new FindPagedAccountCodesAction({filter: 'todo', page: 1})));

    @Effect() removeAccountCode$ = this.actions$
        .pipe(
            ofType(REMOVE_ACCOUNT_CODE),
            map((action: RemoveAccountCodeAction) => action.payload),
            switchMap(payload => this.commonService.removeAccountCode(payload)),
            map(message => new RemoveAccountCodeSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedAccountCodesAction({filter: 'todo', page: 1})])),);
}
