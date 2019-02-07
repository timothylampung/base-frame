import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {selectCostCenters} from './cost-center.selector';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {FindPagedCostCentersAction} from './cost-center.action';
import {CommonState} from '../common.state';
import {CostCenter} from "./cost-center.model";

@Component({
    selector: 'dex-cost-center-list-page',
    templateUrl: './cost-center-list.page.html'
})
export class CostCenterListPage implements OnInit {

    costCenters$: Observable<CostCenter[]>;
    searchForm: FormGroup;
    title = 'Senarai Pusat Kos';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Pusat Kos', routerLink: ['/administration/cost-centers/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<CommonState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.costCenters$ = this.store.pipe(select(selectCostCenters));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedCostCentersAction({filter: '', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedCostCentersAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

