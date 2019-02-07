import {Component, OnInit} from "@angular/core";
import {CommonState} from "../common.state";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {BankCode} from "./bank-code.model";
import {selectBankCodes} from "./bank-code.selector";
import {Observable} from "rxjs";
import {FindPagedBankCodesAction} from "./bank-code.action";

@Component({
    selector: 'dex-bank-code-list-page',
    templateUrl: './bank-code-list.page.html'
})
export class BankCodeListPage implements OnInit {

    bankCodes$: Observable<BankCode[]>;
    searchForm: FormGroup;
    title = 'Senarai Kod Bank';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Kod Bank', routerLink: ['/administration/bank-codes/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<CommonState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.bankCodes$ = this.store.pipe(select(selectBankCodes));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedBankCodesAction({filter: '', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedBankCodesAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

