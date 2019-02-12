import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {InventoryState} from "../../inventory.state";
import {PartComponent} from "../component-model";


@Component({
    selector: 'dex-component-detail-page',
    templateUrl: './component-detail.page.html'
})
export class ComponentDetailPage implements OnInit, OnChanges {

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

    }

    ngOnChanges(changes: SimpleChanges): void {
        console.log(changes);
        this.edit = this.selectedRow == undefined;
    }


}

