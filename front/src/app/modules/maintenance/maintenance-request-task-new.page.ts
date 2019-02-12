import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs/index';
import {MaintenanceRequestState} from './maintenance-request.state';
import {NewMaintenanceRequestTaskAction} from './maintenance-request.action';
import {selectMaintenanceRequestTask} from './maintenance-request.selector';
import {MaintenanceRequestTaskSummary} from './maintenance-request.model';

@Component({
    selector: 'dex-maintenance-request-task-new-page',
    template: `
        <dex-maintenance-request-workflow-page [maintenanceRequestTask]="maintenanceRequestTask$ | async">
        </dex-maintenance-request-workflow-page>
    `
})

export class MaintenanceRequestTaskNewPage implements OnInit {
    maintenanceRequestTask$: Observable<MaintenanceRequestTaskSummary>;

    constructor(public route: ActivatedRoute,
                public store: Store<MaintenanceRequestState>) {
        this.maintenanceRequestTask$ = this.store.pipe(select(selectMaintenanceRequestTask));
    }

    ngOnInit(): void {
        this.store.dispatch(new NewMaintenanceRequestTaskAction());
    }
}
