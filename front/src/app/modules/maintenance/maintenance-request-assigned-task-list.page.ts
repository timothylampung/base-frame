import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {BreadcrumbService} from '../../breadcrumb.service';
import {MaintenanceRequestTaskSummary} from './maintenance-request.model';
import {MaintenanceRequestState} from './maintenance-request.state';
import {selectMaintenanceRequestAssignedTasks} from './maintenance-request.selector';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {
    CountAssignedMaintenanceRequestsAction,
    CountPooledMaintenanceRequestsAction,
    FindAssignedMaintenanceRequestsAction,
    ReleaseMaintenanceRequestTaskAction
} from './maintenance-request.action';
import {CommonService} from "../../services";

@Component({
    selector: 'dex-maintenance-request-assigned-task-list-page',
    templateUrl: './maintenance-request-assigned-task-list.page.html',
    styleUrls: ['./maintenance-request-assigned-task-list.page.css']
})
export class MaintenanceRequestAssignedTaskListPage implements OnInit {
    imageToShow: any;
    maintenanceRequestTasks$: Observable<MaintenanceRequestTaskSummary[]>;
    searchForm: FormGroup;
    title = 'My Tasks (MaintenanceRequest)';
    cols = [
        {field: 'referenceNo', header: 'Reference No'},
        {field: 'description', header: 'Description'},
    ];
    breadcrumbs = [
        {label: 'Maintenance Request'},
        {label: 'My Tasks', routerLink: ['/maintenance/maintenance-request-tasks/assigned']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<MaintenanceRequestState>,
                public commonService : CommonService,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.maintenanceRequestTasks$ = this.store.pipe(select(selectMaintenanceRequestAssignedTasks));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'searchTerm': [''],
            'maintenanceRequestDate': [null],
        });

        this.store.dispatch(new FindAssignedMaintenanceRequestsAction({filter: '', page: 1}));
        this.store.dispatch(new CountAssignedMaintenanceRequestsAction());
        this.store.dispatch(new CountPooledMaintenanceRequestsAction());

        this.searchForm.get('searchTerm').valueChanges
            .subscribe(v => {
            });

    }

    onHover(task : MaintenanceRequestTaskSummary){
        console.log(task)
        this.commonService.downloadFile(task.request.file.fileName).subscribe(blob => {
            this.createImageFromBlob(blob);
        })
    }

    createImageFromBlob(image: Blob) {
        let reader = new FileReader();
        reader.addEventListener("load", () => {
            this.imageToShow = reader.result;
        }, false);

        if (image) {
            reader.readAsDataURL(image);
        }
    }

    createTask() {
        this.router.navigate(['/maintenance/maintenance-request-tasks/new']);
    }

    viewTask(taskId: string) {
        this.router.navigate(['/maintenance/maintenance-request-tasks/', taskId]);
    }

    releaseTask(taskId: string) {
        this.store.dispatch(new ReleaseMaintenanceRequestTaskAction({taskId: taskId}));
    }

    search() {
        this.store.dispatch(new FindAssignedMaintenanceRequestsAction({filter: this.searchForm.value.searchTerm, page: 1}));
    }

    resetSearchForm() {
        this.searchForm.reset();
    }
}

