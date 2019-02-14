import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {AssetState} from "../../asset.state";
import {Location} from "../location-model";
import {InventoryService} from "../../../../services/inventory.service";
import {SaveLocationAction} from "../location-action";

@Component({
    selector: 'dex-location-detail-page',
    templateUrl: './location-detail.page.html'
})
export class LocationDetailPage implements OnInit, OnChanges {

    private location$: Location;



    creatorForm: FormGroup;
    value: boolean;


    title = 'Locations';
    @Input() selectedRow: Location; //
    edit: boolean;
    overlayPanel: any; // todo:

    constructor(public fb: FormBuilder,
                public store: Store<AssetState>,
                public route: ActivatedRoute,
                public inventoryService: InventoryService,
                public router: Router) {
    }

    ngOnInit() {


        this.creatorForm = this.fb.group({
            code: ['', Validators.required],
            description: ['', Validators.required],
            name: ['', Validators.required],
            address: ['', Validators.required],

        });

    }


    submit() {
        console.log( this.location$);
        console.log( this.creatorForm.value);
        this.store.dispatch(new SaveLocationAction(this.creatorForm.value));

    }



    ngOnChanges(changes: SimpleChanges): void {
        console.log(changes);
        this.edit = this.selectedRow == undefined;
    }



}

