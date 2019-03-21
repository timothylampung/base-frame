import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {AssetState} from "../../asset.state";
import {Asset} from "../asset-model";
import {SaveAssetAction} from "../asset-action";

@Component({
    selector: 'dex-asset-detail-page',
    templateUrl: './asset-detail.page.html'
})
export class AssetDetailPage implements OnInit, OnChanges {

    private asset$: Asset;
    elementType : 'url' | 'canvas' | 'img' | 'text' = 'text';
    qrValue : string = '';
    creatorForm: FormGroup;
    value: boolean;

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

        this.creatorForm = this.fb.group({
            code: ['', Validators.required],
            description: ['', Validators.required],
            category: ['', Validators.required],
            cost: ['', Validators.required],
            quantity: ['', Validators.required],
            location: ['', Validators.required],
            assetCode: ['', Validators.required],

        })
    }

    submit() {
        console.log( this.asset$);
        console.log( this.creatorForm.value);
        this.store.dispatch(new SaveAssetAction(this.creatorForm.value));
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

