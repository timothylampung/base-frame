import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {InventoryState} from "../../inventory.state";
import {PartCode} from "../part-code-model";
import {Part} from "../../parts/part-model";
import {SavePartAction} from "../../parts/part-action";
import {SavePartCodeAction} from "../part-code-action";


@Component({
    selector: 'dex-part-code-detail-page',
    templateUrl: './part-code-detail.page.html'
})
export class PartCodeDetailPage implements OnInit, OnChanges {

    private partCodes$: PartCode;
    elementType : 'url' | 'canvas' | 'img' | 'text' = 'text';
    qrValue : string = '';


    creatorForm: FormGroup;
    value: boolean;



    title = 'PartCodes';
    @Input() selectedRow: PartCode;
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
            description: ['', Validators.required],})



    }

    submit() {
        console.log( this.partCodes$);
        console.log( this.creatorForm.value);
        this.store.dispatch(new SavePartCodeAction(this.creatorForm.value));

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



