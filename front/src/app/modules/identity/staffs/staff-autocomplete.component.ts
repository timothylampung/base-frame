import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {select, Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {AutoComplete} from 'primeng/primeng';
import {IdentityState} from "../identity.state";
import {FindPagedStaffsAction} from "./staff.action";
import {selectStaffs} from "./staff.selector";
import {Staff} from "./staff.model";

@Component({
    selector: 'dex-staff-autocomplete',
    templateUrl: './staff-autocomplete.component.html',
})
export class StaffAutocompleteComponent implements OnInit {

    @Input() hideFloatLabel: boolean = false;
    @Input() innerFormControl: FormControl;
    @Input() label : string;
    actor: Staff;
    actors: Staff[];
    @ViewChild('p') autoComplete: AutoComplete;

    constructor(private store: Store<IdentityState>) {
    }

    ngOnInit() {
        this.store.pipe(select(selectStaffs))
            .subscribe((staff: Staff[]) => {
                this.actors = staff;
            });
    }

    search(evt) {
        this.store.dispatch(new FindPagedStaffsAction({filter: evt.query, page: 1}));
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
