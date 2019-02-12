import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs/index';
import {Router} from '@angular/router';
import {ConfirmationService} from 'primeng/api';

import {BreadcrumbService} from '../../breadcrumb.service';
import {MaintenanceRequestRecordSummary} from './maintenance-request.model';
import {MaintenanceRequestState} from './maintenance-request.state';
import {selectMaintenanceRequestRecords} from './maintenance-request.selector';
import {FindArchivedMaintenanceRequestsAction} from './maintenance-request.action';

@Component({
    selector: 'dex-maintenance-request-archive-list-page',
    templateUrl: './maintenance-request-archive-list.page.html',
    styleUrls: ['./maintenance-request-archive-list.page.css']
})
export class MaintenanceRequestArchiveListPage implements OnInit {

    maintenanceRequestRecords$: Observable<MaintenanceRequestRecordSummary[]>;
    searchForm: FormGroup;
    title = 'Search Maintenance Request';
    subtitle = '';
    cols = [
        {field: 'referenceNo', header: 'Reference No'},
        {field: 'description', header: 'Description'},
    ];
    breadcrumbs = [
        {label: 'Maintenance Request'},
        {label: 'Search Maintenance Request', routerLink: ['/maintenance-request/maintenance-request-records/history']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public store: Store<MaintenanceRequestState>,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.maintenanceRequestRecords$ = this.store.pipe(select(selectMaintenanceRequestRecords));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'searchTerm': [''],
            'searchDate': [null],
        });

        this.store.dispatch(new FindArchivedMaintenanceRequestsAction({filter: '', page: 1}));
    }

    view(evt) {
        this.router.navigate(['/maintenance-request/maintenance-request-records/detail', evt.data.referenceNo]);
    }

    search() {
        this.store.dispatch(new FindArchivedMaintenanceRequestsAction({filter: this.searchForm.value.searchTerm, page: 1}));
    }

    resetSearchForm(){
        this.searchForm.reset();
    }
}

