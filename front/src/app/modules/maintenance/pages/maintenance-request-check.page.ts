import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Store} from '@ngrx/store';
import {ConfirmationService, MessageService} from 'primeng/api';
import {
    CompleteMaintenanceRequestTaskAction,
    RemoveMaintenanceRequestTaskAction,
    UpdateMaintenanceRequestAction,
} from '../maintenance-request.action';
import {MaintenanceRequestPage} from "./maintenance-request.page";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {AppState} from "../../../core/core.state";
import {About} from "../../../models";

@Component({
    selector: 'dex-maintenance-request-check-page',
    templateUrl: './maintenance-request-check.page.html',
    styleUrls: ['./maintenance-request-check.page.css']
})
export class MaintenanceRequestCheckPage extends MaintenanceRequestPage implements OnInit {
    selectedAbout: About;
    cols = [
        {field: 'description', header: 'Description'},
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public messageService: MessageService,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public store: Store<AppState>,
                public cdr: ChangeDetectorRef) {
        super(breadcrumbService, messageService, confirmationService, fb, store, cdr);
    }

    ngOnInit() {
        super.ngOnInit()
    }

    approve() {
        if (this.validateDocument()) {
            this.confirmationService.confirm({
                message: 'Anda pasti semua maklumat yang dimasukkan adalah tepat?',
                acceptLabel: 'Ya',
                rejectLabel: 'Tidak',
                accept: () => {
                    this.store.dispatch(new CompleteMaintenanceRequestTaskAction({taskId: this.maintenanceRequestTask.taskId}));
                    this.store.dispatch(new UpdateMaintenanceRequestAction({
                            ...this.maintenanceRequestTask,
                            ...this.mainForm.value
                        })
                    );
                }
            });
        }
    }

    remove() {
        this.confirmationService.confirm({
            message: 'Anda pasti untuk menghapuskan maintenance request ini?',
            acceptLabel: 'Ya',
            rejectLabel: 'Tidak',
            accept: () => {
                this.store.dispatch(
                    new RemoveMaintenanceRequestTaskAction({taskId: this.maintenanceRequestTask.taskId})
                );
            }
        });
    }

    updateMaintenanceRequest() {
        this.confirmationService.confirm({
            message: 'Anda pasti semua maklumat yang dimasukkan adalah tepat?',
            acceptLabel: 'Ya',
            rejectLabel: 'Tidak',
            accept: () => {
                this.store.dispatch(
                    new UpdateMaintenanceRequestAction({
                        ...this.maintenanceRequestTask,
                        ...this.mainForm.value
                    })
                );
            }
        });
    }

    addActivity() {
        this.showDialog();
    }

    viewAbout() {
        this.selectedAbout = null;
        // this.showAboutDialog();
    }

}
