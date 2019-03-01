import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Store} from '@ngrx/store';
import {ConfirmationService, MessageService} from 'primeng/api';
import {WorkOrderActivity} from '../work-order-activity.model';
import {
    AddWorkOrderCommentAction,
    CompleteWorkOrderTaskAction,
    RemoveWorkOrderTaskAction, StartWorkOrderLogAction, StopWorkOrderLogAction,
    UpdateWorkOrderAction,
} from '../work-order.action';
import {WorkOrderPage} from "./work-order.page";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {AppState} from "../../../core/core.state";
import {About} from "../../../models";
import {Observable} from "rxjs";
import {Location} from "@angular/common";
import {WorkOrderComment} from "../work-order-comment.model";

@Component({
    selector: 'dex-work-order-verify-page',
    templateUrl: './work-order-verify.page.html',
    styleUrls: ['./work-order-verify.page.css']
})
export class WorkOrderVerifyPage extends WorkOrderPage implements OnInit {
    selectedAbout: About;

    constructor(public breadcrumbService: BreadcrumbService,
                public messageService: MessageService,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public location: Location,
                public store: Store<AppState>,
                public cdr: ChangeDetectorRef) {
        super(breadcrumbService, messageService, confirmationService, fb, store, cdr);
    }

    ngOnInit() {
        super.ngOnInit()
    }

    complete() {
        if (this.validateDocument()) {
            this.confirmationService.confirm({
                message: 'Are you sure?',
                acceptLabel: 'Yes',
                rejectLabel: 'No',
                accept: () => {
                    this.store.dispatch(new CompleteWorkOrderTaskAction({taskId: this.workOrderTask.taskId}));
                    // this.store.dispatch(new UpdateWorkOrderAction({
                    //         ...this.workOrderTask,
                    //         ...this.mainForm.value
                    //     })
                    // );
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

    addComment() {
        this.showCommentDialog();
    }

    startLog() {
        this.store.dispatch(
            new StartWorkOrderLogAction(this.workOrderTask.workOrder)
        );
    }

    stopLog() {
        this.store.dispatch(
            new StopWorkOrderLogAction(this.workOrderTask.workOrder)
        );
    }

    onSaveComment(comment: WorkOrderComment) {
        console.log(JSON.stringify(this.workOrderTask.workOrder));
        this.store.dispatch(
            new AddWorkOrderCommentAction({
                workOrder: this.workOrderTask.workOrder,
                comment: comment
            })
        );
        this.hideCommentDialog();
    }

    viewAbout() {
        this.selectedAbout = null;
        // this.showAboutDialog();
    }

    goBack() {
        this.location.back()
    }

}