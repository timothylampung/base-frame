import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Store} from '@ngrx/store';
import {ConfirmationService, MessageService} from 'primeng/api';
import {AppState} from '../../../core/core.state';
import {WorkOrder, WorkOrderTaskSummary} from '../work-order.model';
import {BreadcrumbService} from "../../../breadcrumb.service";

const INVOICE_BASE_URI = '/workOrder/workOrder-tasks';

@Component({
    selector: 'dex-work-order-detail-page',
    templateUrl: './work-order-detail.page.html',
    styleUrls: ['./work-order-detail.page.css']
})
export class WorkOrderDetailPage implements OnInit {
    @Input() workOrderTask: WorkOrderTaskSummary;
    mainForm: FormGroup;
    breadcrumbs = [
        {label: 'Work Order'},
        {
            label: 'My Tasks',
            routerLink: [INVOICE_BASE_URI + '/assigned']
        },
        {
            label: 'Work Order Detail',
            routerLink: [INVOICE_BASE_URI + '/:taskId']
        }
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public messageService: MessageService,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public store: Store<AppState>,
                public cdr: ChangeDetectorRef) {
        this.breadcrumbService.setItems(this.breadcrumbs);
    }

    ngOnInit() {
        this.mainForm = this.fb.group({
            referenceNo: [''],
            workOrderNo: [null, Validators.required],
            sourceNo: [''],
            description: ['', Validators.required],
        });

        // date workaround
        // this.workOrderTask.workOrder.workOrderDate = new Date(this.workOrderTask.workOrder.workOrderDate);
        this.mainForm.patchValue(this.workOrderTask.workOrder);
    }

    validateDocument() {
        return true;
    }
}
