import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {BreadcrumbService} from '../../breadcrumb.service';
import {WorkOrderTaskSummary} from './work-order.model';
import {WorkOrderState} from './work-order.state';
import {selectWorkOrderPooledTasks} from './work-order.selector';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {
    ClaimWorkOrderTaskAction,
    CountAssignedWorkOrdersAction,
    CountPooledWorkOrdersAction,
    FindPooledWorkOrdersAction
} from './work-order.action';

@Component({
    selector: 'dex-work-order-pooled-task-list-page',
    templateUrl: './work-order-pooled-task-list.page.html',
    styleUrls: ['./work-order-pooled-task-list.page.css']
})
export class WorkOrderPooledTaskListPage implements OnInit {

    workOrderTasks$: Observable<WorkOrderTaskSummary[]>;
    searchForm: FormGroup;
    selectedTasks: WorkOrderTaskSummary[] = [];
    title = 'Open Tasks (WorkOrder)';
    cols = [
        {field: 'referenceNo', header: 'Reference No'},
        {field: 'description', header: 'Description'},
    ];
    breadcrumbs = [
        {label: 'Work Order'},
        {label: 'Open Tasks', routerLink: ['/work-order/work-order-tasks/pooled']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<WorkOrderState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.workOrderTasks$ = this.store.pipe(select(selectWorkOrderPooledTasks));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'searchTerm': [''],
            'postedDate': [null],
        });

        this.store.dispatch(new FindPooledWorkOrdersAction({filter: '', page: 1}));
        this.store.dispatch(new CountAssignedWorkOrdersAction());
        this.store.dispatch(new CountPooledWorkOrdersAction());
    }

    claimTask() {
        const taskIds = this.selectedTasks.map(task => task.taskId);
        console.log(`taskid`, taskIds);
        this.store.dispatch(new ClaimWorkOrderTaskAction({taskIds: taskIds}));
    }

    search() {
        this.store.dispatch(new FindPooledWorkOrdersAction({filter: this.searchForm.value.searchTerm, page: 1}));
    }

    resetSearchForm() {
        this.searchForm.reset();
    }
}

