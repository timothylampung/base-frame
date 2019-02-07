import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {FindPagedMaintenanceRequestsAction} from "./maintenance-request.action";
import {MaintenanceRequestState, selectMaintenanceRequest} from "../maintenance-request.state";
import {MaintenanceRequest, MaintenanceRequestResult} from "./maintenance-request.model";
import {selectMaintenanceRequestResult} from "./maintenance-request.selector";

@Component({
    selector: 'dex-maintenance-request-list-page',
    templateUrl: './maintenance-request-list.page.html'
})
export class MaintenanceRequestListPage implements OnInit {

    facilityManagers$: Observable<MaintenanceRequestResult>;
    searchForm: FormGroup;
    title = 'Maintenance Requests';
    searchQuery: string = '';
    selectedRow : MaintenanceRequest = null;

    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
        {field: 'value', header: 'Value'},
    ];
    display : boolean =false;


    constructor(public fb: FormBuilder,
                public store: Store<MaintenanceRequestState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.facilityManagers$ = this.store.pipe(select(selectMaintenanceRequestResult));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });
        this.search();
    }

    search() {
        console.log(this.searchQuery);
        this.store.dispatch(new FindPagedMaintenanceRequestsAction({filter: this.searchQuery, page: 1}));
    }

}

