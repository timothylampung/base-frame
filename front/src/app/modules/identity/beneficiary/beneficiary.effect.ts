import {IdentityService} from "../../../services/identity.service";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {
    FIND_ALL_BENEFICIARIES,
    FIND_PAGED_BENEFICIARIES,
    FindAllBeneficiariesSuccessAction,
    FindPagedBeneficiariesAction,
    FindPagedBeneficiariesSuccessAction, REMOVE_BENEFICIARY, RemoveBeneficiaryAction, RemoveBeneficiarySuccessAction,
    SAVE_BENEFICIARY,
    SaveBeneficiaryAction,
    SaveBeneficiarySuccessAction, UPDATE_BENEFICIARY, UpdateBeneficiaryAction, UpdateBeneficiarySuccessAction
} from "./beneficiary.action";
import {from, Observable} from "rxjs";
import {Action} from "@ngrx/store";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {Injectable} from "@angular/core";


@Injectable()
export class BeneficiaryEffects {

    constructor(private actions$: Actions,
                private identityService: IdentityService) {
    }

    @Effect()
    public findAllBeneficiaries$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_ALL_BENEFICIARIES),
            switchMap(payload => this.identityService.findBeneficiaries()),
            map(result => new FindAllBeneficiariesSuccessAction(result)),);

    @Effect()
    public findPagedBeneficiaries$: Observable<Action> = this.actions$
        .pipe(
            ofType(FIND_PAGED_BENEFICIARIES),
            map((action: FindPagedBeneficiariesAction) => action.payload),
            switchMap(payload => this.identityService.findPagedBeneficiaries(payload.filter, payload.page)),
            map(result => new FindPagedBeneficiariesSuccessAction(result)),);

    @Effect() saveBeneficiary$ = this.actions$
        .pipe(
            ofType(SAVE_BENEFICIARY),
            map((action: SaveBeneficiaryAction) => action.payload),
            switchMap((beneficiary) => this.identityService.saveBeneficiary(beneficiary)),
            map((message) => new SaveBeneficiarySuccessAction({message: 'success'})),
            mergeMap((action) => from([action, new FindPagedBeneficiariesAction({filter: 'todo', page: 1})])),);

    @Effect() updateBeneficiary$ = this.actions$
        .pipe(
            ofType(UPDATE_BENEFICIARY),
            map((action: UpdateBeneficiaryAction) => action.payload),
            switchMap((beneficiary) => this.identityService.updateBeneficiary(beneficiary)),
            map((beneficiary) => new UpdateBeneficiarySuccessAction({message: 'success'})),
            switchMap(() => this.identityService.findBeneficiaries()),
            map((beneficiaries) => new FindPagedBeneficiariesAction({filter: 'todo', page: 1})));

    @Effect() removeBeneficiary$ = this.actions$
        .pipe(
            ofType(REMOVE_BENEFICIARY),
            map((action: RemoveBeneficiaryAction) => action.payload),
            switchMap(payload => this.identityService.removeBeneficiary(payload)),
            map(message => new RemoveBeneficiarySuccessAction({message: 'success'})),
            mergeMap(action => from([action, new FindPagedBeneficiariesAction({filter: 'todo', page: 1})])),);
}
