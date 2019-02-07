import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {CommonState} from '../common.state';
import {AccountCode} from './account-code.model';
import {selectAccountCodes} from './account-code.selector';
import {FindPagedAccountCodesAction} from './account-code.action';

@Component({
    selector: 'dex-account-code-list-page',
    templateUrl: './account-code-list.page.html'
})
export class AccountCodeListPage implements OnInit {

    accountCodes$: Observable<AccountCode[]>;
    searchForm: FormGroup;
    title = 'Account Code List';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Jabatan', routerLink: ['/administration/account-codes/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<CommonState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.accountCodes$ = this.store.pipe(select(selectAccountCodes));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedAccountCodesAction({filter: '', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedAccountCodesAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

