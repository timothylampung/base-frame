import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {InventoryState} from "../../inventory.state";
import {PartCode} from "../part-code-model";


@Component({
    selector: 'dex-part-code-detail-page',
    templateUrl: './part-code-detail.page.html'
})
export class PartCodeDetailPage implements OnInit, OnChanges {

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

    }

    ngOnChanges(changes: SimpleChanges): void {
        console.log(changes);
        this.edit = this.selectedRow == undefined;
    }


}

