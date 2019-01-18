import {Beneficiary} from "./beneficiary.model";
import {selectBeneficiaries} from "./beneficiary.selector";
import {FindPagedBeneficiariesAction} from "./beneficiary.action";
import {Component, OnInit} from "@angular/core";
import {Observable} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {select, Store} from "@ngrx/store";
import {IdentityState} from "../identity.state";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'cng-beneficiary-list-page',
    templateUrl: './beneficiary-list.page.html'
})
export class BeneficiaryListPage implements OnInit {

    beneficiaries$: Observable<Beneficiary[]>;
    searchForm: FormGroup;
    title = 'Senarai Penerima';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Penerima', routerLink: ['/administration/beneficiaries/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<IdentityState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.beneficiaries$ = this.store.pipe(select(selectBeneficiaries));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedBeneficiariesAction({filter: '', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedBeneficiariesAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

