import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {BreadcrumbService} from '../../breadcrumb.service';
import {MaintenanceRequestTaskSummary} from './maintenance-request.model';
import {MaintenanceRequestState} from './maintenance-request.state';
import {selectMaintenanceRequestPooledTasks} from './maintenance-request.selector';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {
    ClaimMaintenanceRequestTaskAction,
    CountAssignedMaintenanceRequestsAction,
    CountPooledMaintenanceRequestsAction,
    FindPooledMaintenanceRequestsAction
} from './maintenance-request.action';

@Component({
    selector: 'dex-maintenance-request-pooled-task-list-page',
    templateUrl: './maintenance-request-pooled-task-list.page.html',
    styleUrls: ['./maintenance-request-pooled-task-list.page.css']
})
export class MaintenanceRequestPooledTaskListPage implements OnInit {

    maintenanceRequestTasks$: Observable<MaintenanceRequestTaskSummary[]>;
    searchForm: FormGroup;
    selectedTasks: MaintenanceRequestTaskSummary[] = [];
    title = 'Open Tasks (MaintenanceRequest)';
    cols = [
        {field: 'referenceNo', header: 'Reference No'},
        {field: 'description', header: 'Description'},
    ];
    breadcrumbs = [
        {label: 'Maintenance Request'},
        {label: 'Open Tasks', routerLink: ['/maintenance/maintenance-request-tasks/pooled']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<MaintenanceRequestState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.maintenanceRequestTasks$ = this.store.pipe(select(selectMaintenanceRequestPooledTasks));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'searchTerm': [''],
            'postedDate': [null],
        });

        this.store.dispatch(new FindPooledMaintenanceRequestsAction({filter: '', page: 1}));
        this.store.dispatch(new CountAssignedMaintenanceRequestsAction());
        this.store.dispatch(new CountPooledMaintenanceRequestsAction());
    }

    claimTask() {
        const taskIds = this.selectedTasks.map(task => task.taskId);
        console.log(`taskid`, taskIds);
        this.store.dispatch(new ClaimMaintenanceRequestTaskAction({taskIds: taskIds}));
    }

    search() {
        this.store.dispatch(new FindPooledMaintenanceRequestsAction({filter: this.searchForm.value.searchTerm, page: 1}));
    }

    resetSearchForm() {
        this.searchForm.reset();
    }
}

