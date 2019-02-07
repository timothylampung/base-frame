import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {Period} from '../../common/periods/period.model';
import {CommonState} from "../common.state";
import {selectPeriods} from "./period.selector";
import {FindPagedPeriodsAction} from "./period.action";

@Component({
    selector: 'dex-period-list-page',
    templateUrl: './period-list.page.html'
})
export class PeriodListPage implements OnInit {

    periods$: Observable<Period[]>;
    searchForm: FormGroup;
    title = 'Period List';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Tahun Kewangan', routerLink: ['/administration/periods/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<CommonState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.periods$ = this.store.pipe(select(selectPeriods));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedPeriodsAction({filter:'', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedPeriodsAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

