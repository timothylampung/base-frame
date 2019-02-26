import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {AssetState} from "../../asset.state";
import {Asset} from "../asset-model";
import {Part} from "../../../inventory/parts/part-model";
import {AssetCode} from "../../asset-codes/asset-code-model";

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
            description: ['', Validators.required],})


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

