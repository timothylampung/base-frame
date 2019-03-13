import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {BreadcrumbService} from '../../breadcrumb.service';
import {WorkOrderTaskSummary} from './work-order.model';
import {WorkOrderState} from './work-order.state';
import {selectWorkOrderAssignedTasks} from './work-order.selector';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {
    CountAssignedWorkOrdersAction,
    CountPooledWorkOrdersAction,
    FindAssignedWorkOrdersAction,
    ReleaseWorkOrderTaskAction
} from './work-order.action';
import {CommonService} from "../../services";

@Component({
    selector: 'dex-work-order-assigned-task-list-page',
    templateUrl: './work-order-assigned-task-list.page.html',
    styleUrls: ['./work-order-assigned-task-list.page.css']
})
export class WorkOrderAssignedTaskListPage implements OnInit {

    imageToShow: any;
    workOrderTasks$: Observable<WorkOrderTaskSummary[]>;
    searchForm: FormGroup;
    title = 'My Tasks (WorkOrder)';
    cols = [
        {field: 'referenceNo', header: 'Reference No'},
        {field: 'description', header: 'Description'},
    ];
    breadcrumbs = [
        {label: 'Work Order'},
        {label: 'My Tasks', routerLink: ['/work-order/work-order-tasks/assigned']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<WorkOrderState>,
                public commonService : CommonService,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.workOrderTasks$ = this.store.pipe(select(selectWorkOrderAssignedTasks));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'searchTerm': [''],
            'workOrderDate': [null],
        });

        this.store.dispatch(new FindAssignedWorkOrdersAction({filter: '', page: 1}));
        this.store.dispatch(new CountAssignedWorkOrdersAction());
        this.store.dispatch(new CountPooledWorkOrdersAction());

        this.searchForm.get('searchTerm').valueChanges
            .subscribe(v => {
            });

    }

    onHover(workOrder : WorkOrderTaskSummary){
        console.log(workOrder)
        this.commonService.downloadFile(workOrder.workOrder.file.fileName).subscribe(blob => {
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
        this.router.navigate(['/work-order/work-order-tasks/new']);
    }

    viewTask(taskId: string) {
        this.router.navigate(['/work-order/work-order-tasks/', taskId]);
    }

    releaseTask(taskId: string) {
        this.store.dispatch(new ReleaseWorkOrderTaskAction({taskId: taskId}));
    }

    search() {
        this.store.dispatch(new FindAssignedWorkOrdersAction({filter: this.searchForm.value.searchTerm, page: 1}));
    }

    resetSearchForm() {
        this.searchForm.reset();
    }
}

