import {ChangeDetectorRef, Component, Input} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {select, Store} from "@ngrx/store";
import {ConfirmationService, MessageService} from "primeng/api";
import {Observable, Subject} from "rxjs/index";
import {map, skip, take, takeUntil, withLatestFrom} from "rxjs/operators";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {AppState} from "../../../core/core.state";
import {MaintenanceRequestTaskSummary} from "../maintenance-request.model";
import {tap} from "rxjs/internal/operators";

@Component({
    selector: 'dex-maintenance-request-page',
    template: ''
})
export class MaintenanceRequestPage {

    @Input() maintenanceRequestTask: MaintenanceRequestTaskSummary;
    totalAmount = 0;
    destroy$ = new Subject<any>();
    mainForm: FormGroup;
    displayDialog: boolean;
    breadcrumbs = [
        {label: 'Maintenance Request'},
        {
            label: 'My Tasks',
            routerLink: ['/maintenance/maintenance-request-tasks/assigned']
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

        // todo: date workaround
        // this.maintenanceRequestTask.maintenanceRequest.requestedDate = new Date(this.maintenanceRequestTask.maintenanceRequest.requestedDate);
        this.mainForm.patchValue(this.maintenanceRequestTask.maintenanceRequest);
    }

    showDialog() {
        this.displayDialog = true;
    }

    hideDialog() {
        this.displayDialog = false;
    }

    validateDocument() {
        return true;
    }

    ngOnDestroy() {
        console.log('%cdestroy', 'background-color:red;color:#fff')
        this.destroy$.next();
        this.destroy$.complete();
    }
}
