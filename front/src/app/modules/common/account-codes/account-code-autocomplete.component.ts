import {CommonState} from '../common.state';
import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {select, Store} from '@ngrx/store';
import {AccountCode} from './account-code.model';
import {FindAllAccountCodesAction} from './account-code.action';
import {FormControl} from '@angular/forms';
import {AutoComplete} from 'primeng/primeng';
import {DATA_ACCOUNT_CODE} from "../../../shared/data.constants";
import {selectAllAccountCodes} from "./account-code.selector";

@Component({
    selector: 'dex-account-code-autocomplete',
    templateUrl: './account-code-autocomplete.component.html',
    styleUrls: ['./account-code-autocomplete.component.css']
})
export class AccountCodeAutocompleteComponent implements OnInit {

    @Input() hideFloatLabel = false;
    @Input() innerFormControl: FormControl;
    accountCode: AccountCode;
    accountCodes: AccountCode[];
    @ViewChild('p') autoComplete: AutoComplete;

    constructor(private store: Store<CommonState>) {
    }

    ngOnInit() {
        this.store.pipe(select(selectAllAccountCodes))
            .subscribe((accountCodes: AccountCode[]) => {
                localStorage.setItem(DATA_ACCOUNT_CODE, JSON.stringify(accountCodes));
                this.accountCodes = accountCodes;
            });

        const data: AccountCode[] = JSON.parse(localStorage.getItem(DATA_ACCOUNT_CODE));
        if (data.length === 0) {
            this.store.dispatch(new FindAllAccountCodesAction());
        }
    }

    search(evt) {
        const targetArray = JSON.parse(localStorage.getItem(DATA_ACCOUNT_CODE));
        const options = {
            'code': evt.query,
            'description': evt.query,
        };

        this.accountCodes = this.filter(targetArray, options);
    }

    filter = (targetArray, filterOptions) => {
        const filterKeys = Object.keys(filterOptions);
        return targetArray.filter(function (eachObj) {
            return filterKeys.some(function (eachKey) {
                console.log(eachObj[eachKey]);
                if (eachObj[eachKey]) {
                    return eachObj[eachKey].toUpperCase().includes(filterOptions[eachKey].toUpperCase());
                }
            });
        });
    }
}
