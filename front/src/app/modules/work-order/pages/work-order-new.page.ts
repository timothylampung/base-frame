import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Store} from '@ngrx/store';
import {ConfirmationService, MessageService} from 'primeng/api';
import {AppState} from '../../../core/core.state';
import {initStateWorkOrder, WorkOrder, WorkOrderTaskSummary} from '../work-order.model';
import {BreadcrumbService} from "../../../breadcrumb.service";
import {StartWorkOrderTaskAction} from "../work-order.action";

const INVOICE_BASE_URI = '/workOrder/workOrder-tasks';

@Component({
    selector: 'dex-work-order-new-page',
    templateUrl: './work-order-new.page.html',
    styleUrls: ['./work-order-new.page.css']
})
export class WorkOrderNewPage implements OnInit {
    @Input() workOrderTask: WorkOrderTaskSummary;
    mainForm: FormGroup;
    breadcrumbs = [
        {label: 'Work Order'},
        {
            label: 'My Tasks',
            routerLink: [INVOICE_BASE_URI + '/assigned']
        },
        {
            label: 'New Work Order',
            routerLink: [INVOICE_BASE_URI + '/new']
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
            remarks: ['', Validators.required],
            requestedDate: [''],
            requester: ['', Validators.required],
            asset: ['', Validators.required],
            location: ['', Validators.required],
        });

        // date workaround
        // this.workOrderTask.workOrder.workOrderDate = new Date(this.workOrderTask.workOrder.workOrderDate);
        this.mainForm.patchValue(this.workOrderTask.workOrder);
    }

    draft() {
        if (this.validateDocument()) {
            this.confirmationService.confirm({
                message: 'Submit New Work Order?',
                acceptLabel: 'Yes',
                rejectLabel: 'No',
                accept: () => {
                    console.log(this.mainForm.value);
                    let workOrder: WorkOrder = {
                        ...initStateWorkOrder,
                        ...this.mainForm.value,
                    };
                    this.store.dispatch(new StartWorkOrderTaskAction(workOrder));
                }
            });
        }
    }

    goBack() {
        if (this.validateDocument()) {
            this.confirmationService.confirm({
                message: 'Cancel submission?',
                acceptLabel: 'Yes',
                rejectLabel: 'No',
                accept: () => {
                    window.history.back();
                }
            })
        }
    }

    validateDocument() {
        return true;
    }
}
