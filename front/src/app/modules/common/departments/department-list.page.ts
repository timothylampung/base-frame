import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {selectDepartments} from './department.selector';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {FindPagedDepartmentsAction} from './department.action';
import {Department} from '../../common/departments/department.model';
import {CommonState} from '../common.state';

@Component({
    selector: 'dex-department-list-page',
    templateUrl: './department-list.page.html'
})
export class DepartmentListPage implements OnInit {

    departments$: Observable<Department[]>;
    searchForm: FormGroup;
    title = 'Department List';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Jabatan', routerLink: ['/administration/departments/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<CommonState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.departments$ = this.store.pipe(select(selectDepartments));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedDepartmentsAction({filter: '', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedDepartmentsAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

