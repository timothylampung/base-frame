import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {AssetState} from "../../asset.state";
import {Asset} from "../asset-model";

@Component({
    selector: 'dex-asset-detail-page',
    templateUrl: './asset-detail.page.html'
})
export class AssetDetailPage implements OnInit, OnChanges {

    title = 'Assets';
    @Input() selectedRow: Asset;
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

