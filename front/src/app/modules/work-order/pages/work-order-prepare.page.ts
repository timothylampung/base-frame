import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {ConfirmationService, MessageService} from 'primeng/api';
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
import {Location} from "@angular/common";
import {Observable} from "rxjs";
import {WorkOrderComment} from "../work-order-comment.model";
import {CommonService} from "../../../services";
import {WorkOrder} from "../work-order.model";
import {selectWorkOrder} from "../work-order.selector";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'dex-work-order-prepare-page',
    templateUrl: './work-order-prepare.page.html',
    styleUrls: ['./work-order-prepare.page.css']
})
export class WorkOrderPreparePage extends WorkOrderPage implements OnInit {
    workOrder: WorkOrder;
    selectedAbout: About;
    imageToShow: any;

    constructor(public breadcrumbService: BreadcrumbService,
                public messageService: MessageService,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public route: ActivatedRoute,
                public commonService: CommonService,
                public location: Location,
                public store: Store<AppState>,
                public cdr: ChangeDetectorRef) {
        super(breadcrumbService, messageService, confirmationService, fb, store, cdr);
    }

    ngOnInit() {
        super.ngOnInit()
    //
    //     this.commonService.downloadFile(this.workOrderTask.workOrder.file.name).subscribe(blob => {
    //         this.createImageFromBlob(blob);
    //     })

        this.route.params.subscribe((params: { referenceNo: string }) => {
            this.store.dispatch(new FindWorkOrderByReferenceNoAction(params.referenceNo));
        });

        this.store.pipe(select(selectWorkOrder)).subscribe(workOrder => {
            this.workOrder = workOrder;
        });

        console.log(this.workOrder);
    }

    createImageFromBlob(image: Blob) {
        let reader = new FileReader();
        reader.addEventListener("load", () => {
            this.imageToShow = reader.result;
        }, false);

        if (image) {
            reader.readAsDataURL(image);
        }
    }

    check() {
        if (this.validateDocument()) {
            this.confirmationService.confirm({
                message: 'Check work order?',
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
            message: 'Delete work order?',
            acceptLabel: 'Yes',
            rejectLabel: 'No',
            accept: () => {
                this.store.dispatch(
                    new RemoveWorkOrderTaskAction({taskId: this.workOrderTask.taskId})
                );
            }
        });
    }

    updateWorkOrder() {
        this.confirmationService.confirm({
            message: 'Update work order?',
            acceptLabel: 'Yes',
            rejectLabel: 'No',
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
