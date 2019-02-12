import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {BreadcrumbService} from '../../breadcrumb.service';
import {ConfirmationService, MessageService} from 'primeng/api';
import {Observable, Subject} from 'rxjs';
import {AppState} from '../../core/core.state';
import {FindMaintenanceRequestByReferenceNoAction} from './maintenance-request.action';
import {MaintenanceRequest} from './maintenance-request.model';
import {selectMaintenanceRequest} from './maintenance-request.selector';
import {ActivatedRoute} from "@angular/router";
import {takeUntil} from "rxjs/internal/operators";

@Component({
    selector: 'dex-maintenance-request-archive-detail-page',
    templateUrl: './maintenance-request-archive-detail.page.html',
    styleUrls: ['./maintenance-request-archive-detail.page.css']
})
export class MaintenanceRequestArchiveDetailPage implements OnInit {
    maintenanceRequest: MaintenanceRequest;
    mainForm: FormGroup;
    totalAmount = 0;
    displayAboutDialog: boolean;
    destroy$ = new Subject<any>();
    cols = [
        {field: 'description', header: 'Description'},
    ];
    breadcrumbs = [
        {label: 'Maintenance Request'},
        {
            label: 'Search Maintenance Request',
            routerLink: ['/maintenance/maintenance-request-records/history']
        },
        {
            label: 'Maintenance Request Detail'
        }
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public messageService: MessageService,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public store: Store<AppState>,
                public cdr: ChangeDetectorRef,
                public route: ActivatedRoute) {
        this.breadcrumbService.setItems(this.breadcrumbs);
    }

    ngOnInit() {
        this.mainForm = this.fb.group({
            referenceNo: [''],
            sourceNo: [''],
            description: [''],
        });

        this.route.params.subscribe((params: { referenceNo: string }) => {
            this.store.dispatch(new FindMaintenanceRequestByReferenceNoAction(params.referenceNo));
        });
    }

    private showAboutDialog() {
        this.displayAboutDialog = true
    }

    private hideDialog() {
        this.displayAboutDialog = false;
    }

    ngOnDestroy() {
        this.destroy$.next();
        this.destroy$.complete();
    }
}
