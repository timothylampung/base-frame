import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Store} from '@ngrx/store';
import {ConfirmationService, MessageService} from 'primeng/api';
import {
    CompleteWorkOrderTaskAction,
    RemoveWorkOrderTaskAction,
    UpdateWorkOrderAction,
} from '../work-order.action';
import {WorkOrderPage} from "./work-order.page";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {AppState} from "../../../core/core.state";
import {About} from "../../../models";

@Component({
    selector: 'dex-work-order-register-page',
    templateUrl: './work-order-register.page.html',
    styleUrls: ['./work-order-register.page.css']
})
export class WorkOrderRegisterPage extends WorkOrderPage implements OnInit {
    selectedAbout: About;
    cols = [
        {field: 'description', header: 'Keterangan'},
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
                    this.store.dispatch(new CompleteWorkOrderTaskAction({taskId: this.workOrderTask.taskId}));
                    this.store.dispatch(new UpdateWorkOrderAction({
                            ...this.workOrderTask,
                            ...this.mainForm.value
                        })
                    );
                }
            });
        }
    }

    remove() {
        this.confirmationService.confirm({
            message: 'Anda pasti untuk menghapuskan work order ini?',
            acceptLabel: 'Ya',
            rejectLabel: 'Tidak',
            accept: () => {
                this.store.dispatch(
                    new RemoveWorkOrderTaskAction({taskId: this.workOrderTask.taskId})
                );
            }
        });
    }

    updateWorkOrder() {
        this.confirmationService.confirm({
            message: 'Anda pasti semua maklumat yang dimasukkan adalah tepat?',
            acceptLabel: 'Ya',
            rejectLabel: 'Tidak',
            accept: () => {
                this.store.dispatch(
                    new UpdateWorkOrderAction({
                        ...this.workOrderTask,
                        ...this.mainForm.value
                    })
                );
            }
        });
    }

    addActivity() {
        this.showCommentDialog();
    }

    viewAbout() {
        this.selectedAbout = null;
        // this.showAboutDialog();
    }

}
