import {Injectable} from "@angular/core";
import {
    FIND_ALL_BANK_CODES, FIND_PAGED_BANK_CODES,
    FindAllBankCodesSuccessAction, FindPagedBankCodesAction,
    FindPagedBankCodesSuccessAction, REMOVE_BANK_CODE, RemoveBankCodeAction, RemoveBankCodeSuccessAction,
    SAVE_BANK_CODE,
    SaveBankCodeAction, SaveBankCodeSuccessAction, UPDATE_BANK_CODE,
    UpdateBankCodeAction, UpdateBankCodeSuccessAction
} from "./bank-code.action";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {CommonService} from "../../../services";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";


@Injectable()
export class BankCodeEffects {

    constructor(private actions$: Actions,
                private commonService: CommonService) {
    }

    @Effect()
    public findAllBankCodes$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_BANK_CODES),
            switchMap(action => this.commonService.findBankCodes()),
            map(result => new FindAllBankCodesSuccessAction(result)),);

    @Effect()
    public findPagedBankCodes$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_BANK_CODES),
            map((action: FindPagedBankCodesAction) => action.payload),
            switchMap(payload => this.commonService.findPagedBankCodes(payload.filter, payload.page)),
            map(result => new FindPagedBankCodesSuccessAction(result)),);

    @Effect() saveBankCode$ = this.actions$
        .pipe(
            ofType(SAVE_BANK_CODE),
            map((action: SaveBankCodeAction) => action.payload),
            switchMap((bankCode) => this.commonService.saveBankCode(bankCode)),
            map((message) => new SaveBankCodeSuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedBankCodesAction({filter: 'todo', page: 1})])),);

    @Effect() updateBankCode$ = this.actions$
        .pipe(
            ofType(UPDATE_BANK_CODE),
            map((action: UpdateBankCodeAction) => action.payload),
            switchMap((bankCode) => this.commonService.updateBankCode(bankCode)),
            map((bankCode) => new UpdateBankCodeSuccessAction({message: 'success'})),
            switchMap(() => this.commonService.findBankCodes()),
            map((bankCodes) => new FindPagedBankCodesAction({filter: 'todo', page: 1})));

    @Effect() removeBankCode$ = this.actions$
        .pipe(
            ofType(REMOVE_BANK_CODE),
            map((action: RemoveBankCodeAction) => action.payload),
            switchMap(payload => this.commonService.removeBankCode(payload)),
            map(message => new RemoveBankCodeSuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedBankCodesAction({filter: 'todo', page: 1})])),);
}
