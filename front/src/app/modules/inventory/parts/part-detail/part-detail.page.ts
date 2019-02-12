import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {InventoryState} from "../../inventory.state";
import {Part} from "../part-model";


@Component({
    selector: 'dex-part-detail-page',
    templateUrl: './part-detail.page.html'
})
export class PartDetailPage implements OnInit, OnChanges {

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

    }

    ngOnChanges(changes: SimpleChanges): void {
        console.log(changes);
        this.edit = this.selectedRow == undefined;
    }


}

