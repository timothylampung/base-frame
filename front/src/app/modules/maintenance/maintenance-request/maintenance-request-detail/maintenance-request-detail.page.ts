import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {MaintenanceRequestState} from "../../maintenance-request.state";
import {MaintenanceRequest} from "../maintenance-request.model";

@Component({
    selector: 'dex-maintenance-request-detail-page',
    templateUrl: './maintenance-request-detail.page.html'
})
export class MaintenanceRequestDetailPage implements OnInit, OnChanges {

    title = 'Maintenance Requests';
    @Input() selectedRow: MaintenanceRequest;
    edit: boolean;

    constructor(public fb: FormBuilder,
                public store: Store<MaintenanceRequestState>,
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

