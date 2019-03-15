import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ConfirmationService, MessageService} from 'primeng/api';
import {WorkOrderActivity} from '../work-order-activity.model';
import {
    AddWorkOrderCommentAction,
    CompleteWorkOrderTaskAction, FindWorkOrderByReferenceNoAction,
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
import {WorkOrder} from "../work-order.model";
import {selectWorkOrder} from "../work-order.selector";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'dex-work-order-check-page',
    templateUrl: './work-order-check.page.html',
    styleUrls: ['./work-order-check.page.css']
})
export class WorkOrderCheckPage extends WorkOrderPage implements OnInit {
    selectedAbout: About;
    workOrder: WorkOrder;


    constructor(public breadcrumbService: BreadcrumbService,
                public messageService: MessageService,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public route: ActivatedRoute,
                public location: Location,
                public store: Store<AppState>,
                public cdr: ChangeDetectorRef) {
        super(breadcrumbService, messageService, confirmationService, fb, store, cdr);
    }

    ngOnInit() {
        super.ngOnInit()
        this.route.params.subscribe((params: { referenceNo: string }) => {
            this.store.dispatch(new FindWorkOrderByReferenceNoAction(params.referenceNo));
        });

        this.store.pipe(select(selectWorkOrder)).subscribe(workOrder => {
            this.workOrder = workOrder;
        });

        console.log(this.workOrder);
    }

    verify() {
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
