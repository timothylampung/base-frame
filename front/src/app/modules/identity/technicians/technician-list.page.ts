import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {selectTechnicians} from './technician.selector';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {FindPagedTechniciansAction} from './technician.action';
import {Technician} from "./technician.model";
import {IdentityState} from "../identity.state";

@Component({
    selector: 'cng-technician-list-page',
    templateUrl: './technician-list.page.html'
})
export class TechnicianListPage implements OnInit {

    technicians$: Observable<Technician[]>;
    searchForm: FormGroup;
    title = 'Senarai Kakitangan';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Senarai Kakitangan', routerLink: ['/administration/technicians/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<IdentityState>,
                public route: ActivatedRoute,
                public router: Router) {

        this.breadcrumbService.setItems(this.breadcrumbs);
        this.technicians$ = this.store.pipe(select(selectTechnicians));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedTechniciansAction({filter: '%', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedTechniciansAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

