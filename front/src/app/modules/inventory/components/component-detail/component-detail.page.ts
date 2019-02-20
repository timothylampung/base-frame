import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {InventoryState} from "../../inventory.state";
import {PartComponent} from "../component-model";
import {Part} from "../../parts/part-model";
import {SavePartCodeAction} from "../../part-codes/part-code-action";
import {SaveComponentAction} from "../component-action";


@Component({
    selector: 'dex-component-detail-page',
    templateUrl: './component-detail.page.html'
})
export class ComponentDetailPage implements OnInit, OnChanges {


    private components$: Component;
    elementType : 'url' | 'canvas' | 'img' | 'text' = 'text';
    qrValue : string = '';

    creatorForm: FormGroup;
    value: boolean;


    title = 'Components';
    @Input() selectedRow: PartComponent;
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
            asset: ['', Validators.required],
            partCode: ['', Validators.required],


                }

            )



    }

    submit() {
        console.log( this.components$);
        console.log( this.creatorForm.value);
        this.store.dispatch(new SaveComponentAction(this.creatorForm.value));

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

