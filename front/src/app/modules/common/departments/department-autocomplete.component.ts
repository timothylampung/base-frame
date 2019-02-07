import {selectDepartments} from './department.selector';
import {CommonState} from '../common.state';
import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {select, Store} from '@ngrx/store';
import {Department} from './department.model';
import {FindAllDepartmentsAction} from './department.action';
import {FormControl} from '@angular/forms';
import {AutoComplete} from 'primeng/primeng';
import {DATA_DEPARTMENT} from "../../../shared/data.constants";

@Component({
    selector: 'dex-department-autocomplete',
    templateUrl: './department-autocomplete.component.html',
    styleUrls: ['./department-autocomplete.component.css']
})
export class DepartmentAutocompleteComponent implements OnInit {

    @Input() hideFloatLabel: boolean = false;
    @Input() innerFormControl: FormControl;
    department: Department;
    departments: Department[];
    @ViewChild('p') autoComplete: AutoComplete;

    constructor(private store: Store<CommonState>) {
    }

    ngOnInit() {
        this.store.pipe(select(selectDepartments))
            .subscribe((data: Department[]) => {
                localStorage.setItem(DATA_DEPARTMENT, JSON.stringify(data));
                this.departments = data;
            });
        const data: Department[] = JSON.parse(localStorage.getItem(DATA_DEPARTMENT));
        if (data.length === 0) { this.store.dispatch(new FindAllDepartmentsAction()); }
    }

    search(evt) {
        const targetArray = JSON.parse(localStorage.getItem(DATA_DEPARTMENT));
        const options = {
            'code': evt.query,
            'description': evt.query,
        };
        this.departments = this.filter(targetArray, options);
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
