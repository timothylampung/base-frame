import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {selectStaffs} from './staff.selector';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {FindPagedStaffsAction} from './staff.action';
import {Staff} from "./staff.model";
import {IdentityState} from "../identity.state";
import {MenuItem} from "primeng/api";

@Component({
    selector: 'cng-staff-list-page',
    templateUrl: './staff-list.page.html'
})
export class StaffListPage implements OnInit {

    staffs$: Observable<Staff[]>;
    searchForm: FormGroup;
    title = 'Staffs';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];

    constructor(public fb: FormBuilder,
                public store: Store<IdentityState>,
                public route: ActivatedRoute,
                public router: Router) {

        this.staffs$ = this.store.pipe(select(selectStaffs));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedStaffsAction({filter: '', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedStaffsAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

