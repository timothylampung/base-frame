import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {BreadcrumbService} from '../../breadcrumb.service';
import {ConfirmationService, MessageService} from 'primeng/api';
import {Observable, Subject} from 'rxjs';
import {WorkOrderActivity} from './work-order-activity.model';
import {AppState} from '../../core/core.state';
import {FindWorkOrderByReferenceNoAction, FindWorkOrderActivitiesAction} from './work-order.action';
import {WorkOrder} from './work-order.model';
import {selectWorkOrder, selectWorkOrderActivities} from './work-order.selector';
import {ActivatedRoute} from "@angular/router";
import {takeUntil} from "rxjs/internal/operators";

@Component({
    selector: 'dex-work-order-archive-detail-page',
    templateUrl: './work-order-archive-detail.page.html',
    styleUrls: ['./work-order-archive-detail.page.css']
})
export class WorkOrderArchiveDetailPage implements OnInit {
    workOrder: WorkOrder;
    activities$: Observable<WorkOrderActivity[]>;
    selectedItem: WorkOrderActivity;
    mainForm: FormGroup;
    totalAmount = 0;
    displayAboutDialog: boolean;
    destroy$ = new Subject<any>();
    cols = [
        {field: 'description', header: 'Description'},
    ];
    breadcrumbs = [
        {label: 'Work Order'},
        {
            label: 'Search Work Order',
            routerLink: ['/work-order/work-order-records/detail/:referenceNo']
        },
        {
            label: 'Butiran Work Order'
        }
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public messageService: MessageService,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public store: Store<AppState>,
                public cdr: ChangeDetectorRef,
                public route: ActivatedRoute) {
        this.breadcrumbService.setItems(this.breadcrumbs);
    }

    ngOnInit() {
        this.mainForm = this.fb.group({
            referenceNo: [''],
            workOrderNo: [null],
            sourceNo: [''],
            description: [''],
        });

        this.activities$ = this.store.pipe(select(selectWorkOrderActivities));

        this.route.params.subscribe((params: { referenceNo: string }) => {
            this.store.dispatch(new FindWorkOrderByReferenceNoAction(params.referenceNo));
        });

        this.store.pipe(select(selectWorkOrder)).subscribe(workOrder => {
                this.workOrder = workOrder;
        });
    }

    private showAboutDialog() {
        this.displayAboutDialog = true
    }

    private hideDialog() {
        this.displayAboutDialog = false;
    }

    ngOnDestroy() {
        this.destroy$.next();
        this.destroy$.complete();
    }
}
