import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {AssetState} from "../../asset.state";
import {AssetCode} from "../asset-code-model";
import {SaveAssetCodeAction} from "../asset-code.action";
import {all} from "codelyzer/walkerFactory/walkerFn";

@Component({
    selector: 'dex-asset-code-detail-page',
    templateUrl: './asset-code-detail.page.html'
})
export class AssetCodeDetailPage implements OnInit, OnChanges {

    private assetCode$: AssetCode;
    elementType : 'url' | 'canvas' | 'img' | 'text' = 'text';
    qrValue : string = '';

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

    submit() {
        console.log( this.assetCode$);
        console.log( this.creatorForm.value);
        this.store.dispatch(new SaveAssetCodeAction(this.creatorForm.value));
        this.creatorForm.reset();

    }



    ngOnChanges(changes: SimpleChanges): void {
        console.log(changes);
        this.edit = this.selectedRow == undefined;
        if(this.selectedRow!=undefined){
            this.qrValue = this.selectedRow.code;
            this.creatorForm.patchValue(this.selectedRow);
        } else {
            if(this.creatorForm!=undefined){
                this.creatorForm.reset();
            }
        }
    }


}

