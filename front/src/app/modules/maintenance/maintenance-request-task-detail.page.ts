import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs/index';
import {MaintenanceRequestState} from './maintenance-request.state';
import {FindMaintenanceRequestTaskByTaskIdAction} from './maintenance-request.action';
import {selectMaintenanceRequestTask} from './maintenance-request.selector';
import {MaintenanceRequest} from './maintenance-request.model';

@Component({
    selector: 'dex-maintenance-request-task-detail-page',
    template: `
        <dex-maintenance-request-workflow-page [maintenanceRequestTask]="maintenanceRequestTask$ | async">
        </dex-maintenance-request-workflow-page>
    `
})

export class MaintenanceRequestTaskDetailPage implements OnInit {
    maintenanceRequestTask$: Observable<MaintenanceRequest>;

    constructor(public route: ActivatedRoute,
                public store: Store<MaintenanceRequestState>) {
        this.maintenanceRequestTask$ = this.store.pipe(select(selectMaintenanceRequestTask));
    }

    ngOnInit(): void {
        this.route.params.subscribe((params: { taskId: string }) => {
            this.store.dispatch(new FindMaintenanceRequestTaskByTaskIdAction({taskId: params.taskId}));
        });
    }
}
