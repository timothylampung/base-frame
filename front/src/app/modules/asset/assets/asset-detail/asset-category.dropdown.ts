import {Component, Input, OnInit, ViewChild} from "@angular/core";
import {FormControl} from "@angular/forms";
import {SelectItem} from "primeng/api";
import {Dropdown} from "primeng/primeng";
import {AssetCategory} from "../asset-category.enum";

@Component({
    selector: 'dex-asset-category-dropdown',
    templateUrl : './asset-category.dropdown.html'

})
export class AssetCategoryDropdown implements OnInit {

    @Input() showDropdown: boolean = true;
    @Input() hideFloatLabel: false;
    @Input() innerFormControl: FormControl;
    selected: AssetCategory;
    assetCategory: SelectItem[] = [];
    @ViewChild('dd') dropdown: Dropdown;

    constructor() {
        this.assetCategory = [{disabled: true, label: '', value: null}];

    }

    ngOnInit() {

        for (let n in AssetCategory) {
            if (typeof AssetCategory[n] === 'string')
            {
                let enumName = AssetCategory[n.toString()];
                let splitted = enumName.split("_", 9999);
                console.log(splitted);
                let label = '';
                for (const str of splitted) {
                    label = label.concat(" "+str);
                }
                this.assetCategory.push({label: label, value: AssetCategory[n.toString()]});
            }
            console.log('ITEM: {}', n.toString());
        }
    }
}
