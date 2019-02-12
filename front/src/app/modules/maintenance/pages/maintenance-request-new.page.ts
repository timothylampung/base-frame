import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Store} from '@ngrx/store';
import {ConfirmationService, MessageService} from 'primeng/api';
import {AppState} from '../../../core/core.state';
import {initStateMaintenanceRequest, MaintenanceRequest, MaintenanceRequestTaskSummary} from '../maintenance-request.model';
import {BreadcrumbService} from "../../../breadcrumb.service";
import {StartMaintenanceRequestTaskAction} from "../maintenance-request.action";

const MAINTENANCE_REQUEST_BASE_URI = '/maintenanceRequest/maintenanceRequest-tasks';

@Component({
    selector: 'dex-maintenance-request-new-page',
    templateUrl: './maintenance-request-new.page.html',
    styleUrls: ['./maintenance-request-new.page.css']
})
export class MaintenanceRequestNewPage implements OnInit {
    @Input() maintenanceRequestTask: MaintenanceRequestTaskSummary;
    mainForm: FormGroup;
    breadcrumbs = [
        {label: 'Maintenance Request'},
        {
            label: 'My Tasks',
            routerLink: [MAINTENANCE_REQUEST_BASE_URI + '/assigned']
        },
        {
            label: 'Maintenance Request Baru',
            routerLink: [MAINTENANCE_REQUEST_BASE_URI + '/new']
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
            maintenanceRequestNo: [null, Validators.required],
            sourceNo: [''],
            description: ['', Validators.required],
        });

        // date workaround
        // this.maintenanceRequestTask.maintenanceRequest.maintenanceRequestDate = new Date(this.maintenanceRequestTask.maintenanceRequest.maintenanceRequestDate);
        this.mainForm.patchValue(this.maintenanceRequestTask.maintenanceRequest);
    }

    draft() {
        if (this.validateDocument()) {
            this.confirmationService.confirm({
                message: 'Anda pasti semua maklumat yang dimasukkan adalah tepat?',
                acceptLabel: 'Ya',
                rejectLabel: 'Tidak',
                accept: () => {
                    console.log(this.mainForm.value);
                    let maintenanceRequest: MaintenanceRequest = {
                        ...initStateMaintenanceRequest,
                        ...this.mainForm.value,
                    };
                    this.store.dispatch(new StartMaintenanceRequestTaskAction(maintenanceRequest));
                }
            });
        }
    }

    validateDocument() {
        return true;
    }
}
