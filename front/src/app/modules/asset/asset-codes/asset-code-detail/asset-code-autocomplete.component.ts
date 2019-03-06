import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {select, Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {AutoComplete} from 'primeng/primeng';
import {FindPagedAssetCodesAction} from "../asset-code.action";
import {selectAssetCodes} from "../asset-code.selector";
import {AssetState} from "../../asset.state";
import {AssetCode} from "../asset-code-model";


@Component({
    selector: 'dex-asset-code-autocomplete',
    templateUrl: './asset-code-autocomplete.component.html',
    styleUrls: ['./asset-code-autocomplete.component.css']
})
export class AssetCodeAutocompleteComponent implements OnInit {

    @Input() hideFloatLabel: boolean = false;
    @Input() innerFormControl: FormControl;
    assetCode: AssetCode;
    assetCodes: AssetCode[];
    @ViewChild('p') autoComplete: AutoComplete;

    constructor(private store: Store<AssetState>) {
    }

    ngOnInit() {
        this.store.pipe(select(selectAssetCodes))
            .subscribe((assetCodes: AssetCode[]) => {
                this.assetCodes = assetCodes;
            });
    }

    search(evt) {
        this.store.dispatch(new FindPagedAssetCodesAction({filter: evt.query, page: 1}));
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
