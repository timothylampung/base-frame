import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {select, Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {AutoComplete} from 'primeng/primeng';
import {AssetState} from "../asset.state";
import {selectLocations} from "./location.selector";
import {FindPagedLocationsAction} from "./location-action";

@Component({
    selector: 'dex-location-autocomplete',
    templateUrl: './location-autocomplete.component.html',
    styleUrls: ['./location-autocomplete.component.css']
})
export class LocationAutocompleteComponent implements OnInit {

    @Input() hideFloatLabel: boolean = false;
    @Input() innerFormControl: FormControl;
    location: Location;
    locations: Location[];
    @ViewChild('p') autoComplete: AutoComplete;

    constructor(private store: Store<AssetState>) {
    }

    ngOnInit() {
        this.store.pipe(select(selectLocations))
            .subscribe((locations: Location[]) => {
                this.locations = locations;
            });
    }

    search(evt) {
        this.store.dispatch(new FindPagedLocationsAction({filter: evt.query, page: 1}));
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
