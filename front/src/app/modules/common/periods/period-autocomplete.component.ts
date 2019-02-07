import {selectPeriods} from './period.selector';
import {CommonState} from '../common.state';
import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {select, Store} from '@ngrx/store';
import {Period} from './period.model';
import {FindAllPeriodsAction, FindPagedPeriodsAction} from './period.action';
import {FormControl} from '@angular/forms';
import {AutoComplete} from 'primeng/primeng';
import {DATA_DEPARTMENT, DATA_PERIOD} from "../../../shared/data.constants";

@Component({
    selector: 'dex-period-autocomplete',
    templateUrl: './period-autocomplete.component.html',
    styleUrls: ['./period-autocomplete.component.css']
})
export class PeriodAutocompleteComponent implements OnInit {

    @Input() hideFloatLabel: boolean = false;
    @Input() innerFormControl: FormControl;
    period: Period;
    periods: Period[];
    @ViewChild('p') autoComplete: AutoComplete;

    constructor(private store: Store<CommonState>) {
    }

    ngOnInit() {
        this.store.pipe(select(selectPeriods))
            .subscribe((data: Period[]) => {
                localStorage.setItem(DATA_PERIOD, JSON.stringify(data));
                this.periods = data;
            });
        const data: Period[] = JSON.parse(localStorage.getItem(DATA_PERIOD));
        if (data.length === 0) { this.store.dispatch(new FindAllPeriodsAction()); }
    }

    search(evt) {
        const targetArray = JSON.parse(localStorage.getItem(DATA_PERIOD));
        const options = {
            'code': evt.query,
            'description': evt.query,
            'year': evt.query,
        };
        this.periods = this.filter(targetArray, options);
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
