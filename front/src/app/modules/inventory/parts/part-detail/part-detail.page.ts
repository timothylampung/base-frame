import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {InventoryState} from "../../inventory.state";
import {Part} from "../part-model";
import {SaveLocationAction} from "../../../asset/locations/location-action";
import {AssetCode} from "../../../asset/asset-codes/asset-code-model";
import {SavePartAction} from "../part-action";


@Component({
    selector: 'dex-part-detail-page',
    templateUrl: './part-detail.page.html'
})
export class PartDetailPage implements OnInit, OnChanges {


    private parts$: Part;
    elementType : 'url' | 'canvas' | 'img' | 'text' = 'text';
    qrValue : string = '';



    creatorForm: FormGroup;
    value: boolean;



    title = 'Parts';
    @Input() selectedRow: Part;
    edit: boolean;
    overlayPanel: any; // todo:

    constructor(public fb: FormBuilder,
                public store: Store<InventoryState>,
                public route: ActivatedRoute,
                public router: Router) {
    }

    ngOnInit() {

        this.creatorForm = this.fb.group({
            code: ['', Validators.required],
            description: ['', Validators.required],
            partCode: ['', Validators.required]
        })

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


    submit() {
        console.log( this.parts$);
        console.log( this.creatorForm.value);
        this.store.dispatch(new SavePartAction(this.creatorForm.value));

    }


}

