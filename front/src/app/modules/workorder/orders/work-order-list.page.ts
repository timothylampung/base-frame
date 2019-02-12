import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {WorkOrderState} from "../work-order.state";
import {selectWorkOrderResultState, selectWorkOrders} from "./work-order.selector";
import {FindPagedWorkOrdersAction} from "./work-order.action";
import {WorkOrder, WorkOrderResult} from "./work-order.model";

@Component({
    selector: 'dex-work-order-list-page',
    templateUrl: './work-order-list.page.html'
})
export class WorkOrderListPage implements OnInit {

    workOrders$: Observable<WorkOrderResult>;
    searchForm: FormGroup;
    title = 'WorkOrder List';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Tahun Kewangan', routerLink: ['/work-order/work-orders/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<WorkOrderState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.workOrders$ = this.store.pipe(select(selectWorkOrderResultState));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedWorkOrdersAction({filter:'', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedWorkOrdersAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

