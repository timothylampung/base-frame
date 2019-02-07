import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {selectConfigurations} from './configuration.selector';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {Configuration} from './configuration.model';
import {FindPagedConfigurationsAction} from './configuration.action';
import {CommonState} from '../common.state';

@Component({
    selector: 'dex-configuration-list-page',
    templateUrl: './configuration-list.page.html'
})
export class ConfigurationListPage implements OnInit {

    configurations$: Observable<Configuration[]>;
    searchForm: FormGroup;
    title = 'Configuration List';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Administration'},
        {label: 'Configurations', routerLink: ['/administration/configurations/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<CommonState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.configurations$ = this.store.pipe(select(selectConfigurations));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedConfigurationsAction({filter: '', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedConfigurationsAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

