import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {AssetState} from "../../asset.state";
import {Location} from "../location-model";
import {InventoryService} from "../../../../services/inventory.service";
import {RemoveLocationAction, SaveLocationAction, UpdateLocationAction} from "../location-action";
import {ConfirmationService, Message} from "primeng/api";

@Component({
    selector: 'dex-location-detail-page',
    templateUrl: './location-detail.page.html'
})
export class LocationDetailPage implements OnInit, OnChanges {

    private location$: Location;
    elementType : 'url' | 'canvas' | 'img' | 'text' = 'text';
    qrValue : string = '';
    msgs: Message[] = [];
    creatorForm: FormGroup;
    value: boolean;


    title = 'Locations';
    @Input() selectedRow: Location; //
    edit: boolean;
    overlayPanel: any; // todo:

    constructor(public fb: FormBuilder,
                public store: Store<AssetState>,
                public route: ActivatedRoute,
                public confirmationService: ConfirmationService,
                public inventoryService: InventoryService,
                public router: Router) {
    }

    ngOnInit() {


        this.creatorForm = this.fb.group({
            address: ['', Validators.required],
            code: ['', Validators.required],
            description: ['', Validators.required],
            name: ['', Validators.required],
            

        });

    }

    submit() {
        console.log( this.location$);
        console.log( this.creatorForm.value);
        this.store.dispatch(new SaveLocationAction(this.creatorForm.value));
        // this.store.dispatch(new UpdateLocationAction(this.creatorForm.value));
        this.creatorForm.reset();
    }

    updateLocation() {
        this.confirmationService.confirm({
            message: 'Are you sure you want to save this?',
            header: 'Save Confirmation',
            icon: 'pi pi-info-circle',
            accept: () => {
                this.msgs = [{severity:'info', summary:'Location Saved'}];
                this.store.dispatch(new UpdateLocationAction(this.selectedRow));
                console.log(this.selectedRow);
            },
            reject: () => {
                this.msgs = [{severity:'info', summary:'Save Cancelled'}];
            }
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



}

