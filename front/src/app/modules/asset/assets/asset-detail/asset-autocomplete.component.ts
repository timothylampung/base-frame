import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {select, Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {AutoComplete} from 'primeng/primeng';
import {Asset} from "../asset-model";
import {AssetState} from "../../asset.state";
import {selectAssets} from "../asset-selector";
import {FindPagedAssetsAction} from "../asset-action";

@Component({
    selector: 'dex-asset-autocomplete',
    templateUrl: './asset-autocomplete.component.html',
})
export class AssetAutocompleteComponent implements OnInit {

    @Input() hideFloatLabel: boolean = false;
    @Input() innerFormControl: FormControl;
    @Input() label : string;
    asset: Asset;
    assets: Asset[];
    @ViewChild('p') autoComplete: AutoComplete;

    constructor(private store: Store<AssetState>) {
    }

    ngOnInit() {
        this.store.pipe(select(selectAssets))
            .subscribe((assets: Asset[]) => {
                this.assets = assets;
            });
    }

    search(evt) {
        this.store.dispatch(new FindPagedAssetsAction({filter: evt.query, page: 1}));
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
