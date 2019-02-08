import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {select, Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {AutoComplete} from 'primeng/primeng';
import {selectPositionCodes} from './position-code.selector';
import {CommonState} from '../common.state';
import {PositionCode} from './position-code.model';
import {FindPagedPositionCodesAction} from './position-code.action';

@Component({
    selector: 'dex-position-code-autocomplete',
    templateUrl: './position-code-autocomplete.component.html',
    styleUrls: ['./position-code-autocomplete.component.css']
})
export class PositionCodeAutocompleteComponent implements OnInit {

    @Input() hideFloatLabel: boolean = false;
    @Input() innerFormControl: FormControl;
    positionCode: PositionCode;
    positionCodes: PositionCode[];
    @ViewChild('p') autoComplete: AutoComplete;

    constructor(private store: Store<CommonState>) {
    }

    ngOnInit() {
        this.store.pipe(select(selectPositionCodes))
            .subscribe((accountCodes: PositionCode[]) => {
                // localStorage.setItem('MPSP_ACCT_CODE', JSON.stringify(accountCodes));
                this.positionCodes = accountCodes;
            });
    }

    search(evt) {
        this.store.dispatch(new FindPagedPositionCodesAction({filter: evt.query, page: 1}));

        /*
                let targetArray = JSON.parse(localStorage.getItem('MPSP_ACCT_CODE'));
                let options = {
                    "code": evt.query,
                    "description": evt.query,
                };
                this.accountCodes = this.filter(targetArray, options);
        */
    }

    filter = (targetArray, filterOptions) => {
        const filterKeys = Object.keys(filterOptions);
        return targetArray.filter(function (eachObj) {
            return filterKeys.some(function (eachKey) {
                return eachObj[eachKey].toUpperCase().includes(filterOptions[eachKey].toUpperCase());
            });
        });
    }
}
