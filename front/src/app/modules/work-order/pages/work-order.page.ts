import {ChangeDetectorRef, Component, Input} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {select, Store} from "@ngrx/store";
import {ConfirmationService, MessageService} from "primeng/api";
import {Observable, Subject} from "rxjs/index";
import {map, skip, take, takeUntil, withLatestFrom} from "rxjs/operators";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {WorkOrderActivity} from "../work-order-activity.model";
import {AppState} from "../../../core/core.state";
import {
    selectWorkOrderActivities,
    selectWorkOrderComments,
    selectWorkOrderLogs
} from "../work-order.selector";
import {WorkOrderTaskSummary} from "../work-order.model";
import {tap} from "rxjs/internal/operators";
import {
    FindWorkOrderActivitiesAction,
    FindWorkOrderCommentsAction,
    FindWorkOrderLogsAction
} from "../work-order.action";
import {WorkOrderLog} from "../work-order-log.model";
import {WorkOrderComment} from "../work-order-comment.model";

@Component({
    selector: 'dex-work-order-page',
    template: ''
})
export class WorkOrderPage {

    @Input() workOrderTask: WorkOrderTaskSummary;
    activities$: Observable<WorkOrderActivity[]>;
    logs$: Observable<WorkOrderLog[]>;
    comments$: Observable<WorkOrderComment[]>;
    destroy$ = new Subject<any>();
    mainForm: FormGroup;
    displayCommentDialog: boolean;
    breadcrumbs = [
        {label: 'Work Order'},
        {
            label: 'My Tasks',
            routerLink: ['/work-order/work-order-tasks/assigned']
        }
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public messageService: MessageService,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public store: Store<AppState>,
                public cdr: ChangeDetectorRef) {
    }

    ngOnInit() {
        this.breadcrumbService.setItems(this.breadcrumbs);

        this.mainForm = this.fb.group({
            referenceNo: [''],
            sourceNo: [''],
            description: ['', Validators.required],
        });

        this.activities$ = this.store.pipe(select(selectWorkOrderActivities));
        this.comments$ = this.store.pipe(select(selectWorkOrderComments));
        this.logs$ = this.store.pipe(select(selectWorkOrderLogs));
        this.mainForm.patchValue(this.workOrderTask.workOrder);

        if (this.workOrderTask.referenceNo) {
            this.store.dispatch(
                new FindWorkOrderCommentsAction({workOrder: this.workOrderTask.workOrder})
            );
            this.store.dispatch(
                new FindWorkOrderLogsAction({workOrder: this.workOrderTask.workOrder})
            );
        }
    }

    showCommentDialog() {
        this.displayCommentDialog = true;
    }

    hideCommentDialog() {
        this.displayCommentDialog = false;
    }

    validateDocument() {
        return true;
    }

    ngOnDestroy() {
        this.destroy$.next();
        this.destroy$.complete();
    }
}
