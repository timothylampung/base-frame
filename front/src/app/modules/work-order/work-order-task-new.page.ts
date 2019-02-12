import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs/index';
import {WorkOrderState} from './work-order.state';
import {NewWorkOrderTaskAction} from './work-order.action';
import {selectWorkOrderTask} from './work-order.selector';
import {WorkOrderTaskSummary} from './work-order.model';

@Component({
    selector: 'dex-work-order-task-new-page',
    template: `
        <dex-work-order-workflow-page [workOrderTask]="workOrderTask$ | async">
        </dex-work-order-workflow-page>
    `
})

export class WorkOrderTaskNewPage implements OnInit {
    workOrderTask$: Observable<WorkOrderTaskSummary>;

    constructor(public route: ActivatedRoute,
                public store: Store<WorkOrderState>) {
        this.workOrderTask$ = this.store.pipe(select(selectWorkOrderTask));
    }

    ngOnInit(): void {
        this.store.dispatch(new NewWorkOrderTaskAction());
    }
}
