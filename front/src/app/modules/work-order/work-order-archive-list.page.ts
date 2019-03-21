import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs/index';
import {Router} from '@angular/router';
import {ConfirmationService} from 'primeng/api';

import {BreadcrumbService} from '../../breadcrumb.service';
import {WorkOrderRecordSummary} from './work-order.model';
import {WorkOrderState} from './work-order.state';
import {selectWorkOrderRecords} from './work-order.selector';
import {FindArchivedWorkOrdersAction} from './work-order.action';

@Component({
    selector: 'dex-work-order-archive-list-page',
    templateUrl: './work-order-archive-list.page.html',
    styleUrls: ['./work-order-archive-list.page.css']
})
export class WorkOrderArchiveListPage implements OnInit {

    workOrderRecords$: Observable<WorkOrderRecordSummary[]>;
    searchForm: FormGroup;
    title = 'Search Work Order';
    subtitle = '';
    cols = [
        {field: 'referenceNo', header: 'Reference No'},
        {field: 'description', header: 'Description'},
    ];
    breadcrumbs = [
        {label: 'Work Order'},
        {label: 'Search Work Order', routerLink: ['/work-order/work-order-records/history']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public store: Store<WorkOrderState>,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.workOrderRecords$ = this.store.pipe(select(selectWorkOrderRecords));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'searchTerm': [''],
            'searchDate': [null],
        });

        this.store.dispatch(new FindArchivedWorkOrdersAction({filter: '', page: 1}));
    }

    view(evt) {
        this.router.navigate(['/work-order/work-order-records/detail', evt.data.referenceNo]);
    }

    search() {
        this.store.dispatch(new FindArchivedWorkOrdersAction({filter: this.searchForm.value.searchTerm, page: 1}));
    }

    resetSearchForm(){
        this.searchForm.reset();
    }
}

