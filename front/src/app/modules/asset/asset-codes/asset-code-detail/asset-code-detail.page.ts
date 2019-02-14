import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {AssetState} from "../../asset.state";
import {AssetCode} from "../asset-code-model";
import {Location} from "../../locations/location-model";
import {SaveLocationAction} from "../../locations/location-action";
import {SaveAssetCodeAction} from "../asset-code.action";

@Component({
    selector: 'dex-asset-code-detail-page',
    templateUrl: './asset-code-detail.page.html'
})
export class AssetCodeDetailPage implements OnInit, OnChanges {


    private assetCodes$: AssetCode;



    creatorForm: FormGroup;
    value: boolean;


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

        this.creatorForm = this.fb.group({
            code: ['', Validators.required],
            description: ['', Validators.required],


        });

    }

    ngOnChanges(changes: SimpleChanges): void {
        console.log(changes);
        this.edit = this.selectedRow == undefined;
    }


    submit() {
        console.log( this.assetCodes$);
        console.log( this.creatorForm.value);
        this.store.dispatch(new SaveAssetCodeAction(this.creatorForm.value));

    }


}

