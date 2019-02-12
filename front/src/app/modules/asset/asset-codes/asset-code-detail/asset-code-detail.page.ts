import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {AssetState} from "../../asset.state";
import {AssetCode} from "../asset-code-model";

@Component({
    selector: 'dex-asset-code-detail-page',
    templateUrl: './asset-code-detail.page.html'
})
export class AssetCodeDetailPage implements OnInit, OnChanges {

    title = 'Asset Codes';
    @Input() selectedRow: AssetCode;
    edit: boolean;
    overlayPanel: any; // todo:

    constructor(public fb: FormBuilder,
                public store: Store<AssetState>,
                public route: ActivatedRoute,
                public router: Router) {
    }

    ngOnInit() {

    }

    ngOnChanges(changes: SimpleChanges): void {
        console.log(changes);
        this.edit = this.selectedRow == undefined;
    }


}

