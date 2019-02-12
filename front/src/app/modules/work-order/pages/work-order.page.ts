import {ChangeDetectorRef, Component, Input} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {select, Store} from "@ngrx/store";
import {ConfirmationService, MessageService} from "primeng/api";
import {Observable, Subject} from "rxjs/index";
import {map, skip, take, takeUntil, withLatestFrom} from "rxjs/operators";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {Activity} from "../activity.model";
import {AppState} from "../../../core/core.state";
import {selectActivities} from "../work-order.selector";
import {FindActivitiesAction, FindWorkOrderTransactionsAction} from "../work-order.action";
import {WorkOrderTaskSummary} from "../work-order.model";
import {tap} from "rxjs/internal/operators";

@Component({
    selector: 'dex-work-order-page',
    template: ''
})
export class WorkOrderPage {

    @Input() workOrderTask: WorkOrderTaskSummary;
    totalAmount = 0;
    activities: Activity[];
    activities$: Observable<Activity[]>;
    destroy$ = new Subject<any>();
    mainForm: FormGroup;
    displayDialog: boolean;
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

        this.activities$ = this.store.pipe(select(selectActivities));
        this.activities$.pipe(takeUntil(this.destroy$)).subscribe(activities => {
            return this.activities = activities;
        });

        // todo: date workaround
        // this.workOrderTask.workOrder.workOrderDate = new Date(this.workOrderTask.workOrder.workOrderDate);
        this.mainForm.patchValue(this.workOrderTask.workOrder);
        if (this.workOrderTask.referenceNo) {
            this.store.dispatch(
                new FindActivitiesAction({workOrder: this.workOrderTask.workOrder})
            );
        }
    }

    showDialog() {
        this.displayDialog = true;
    }

    hideDialog() {
        this.displayDialog = false;
    }

    validateDocument() {
        if (this.activities.length === 0) {
            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: `Sila masukkan item invois`
            });
            return false;
        }
        return true;
    }

    ngOnDestroy() {
        console.log('%cdestroy', 'background-color:red;color:#fff')
        this.destroy$.next();
        this.destroy$.complete();
    }
}
