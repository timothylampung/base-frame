import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs/index';
import {WorkOrderState} from './work-order.state';
import {FindWorkOrderTaskByTaskIdAction} from './work-order.action';
import {selectWorkOrderTask} from './work-order.selector';
import {WorkOrder} from './work-order.model';

@Component({
    selector: 'dex-work-order-task-detail-page',
    template: `
        <dex-work-order-workflow-page [workOrderTask]="workOrderTask$ | async">
        </dex-work-order-workflow-page>
    `
})

export class WorkOrderTaskDetailPage implements OnInit {
    workOrderTask$: Observable<WorkOrder>;

    constructor(public route: ActivatedRoute,
                public store: Store<WorkOrderState>) {
        this.workOrderTask$ = this.store.pipe(select(selectWorkOrderTask));
    }

    ngOnInit(): void {
        this.route.params.subscribe((params: { taskId: string }) => {
            this.store.dispatch(new FindWorkOrderTaskByTaskIdAction({taskId: params.taskId}));
        });
    }
}
