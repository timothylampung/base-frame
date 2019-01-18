import {CommonState} from "../common.state";
import {FormBuilder, FormGroup} from "@angular/forms";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectPositionCodes} from "./position-code.selector";
import {FindPagedPositionCodesAction} from "./position-code.action";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable} from "rxjs";
import {PositionCode} from "./position-code.model";
import {Component, OnInit} from "@angular/core";

@Component({
    selector: 'cng-position-code-list-page',
    templateUrl: './position-code-list.page.html'
})
export class PositionCodeListPage implements OnInit {

    positionCodes$: Observable<PositionCode[]>;
    searchForm: FormGroup;
    title = 'Senarai Kod Posisi';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Kod Posisi', routerLink: ['/administration/position-codes/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<CommonState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.positionCodes$ = this.store.pipe(select(selectPositionCodes));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedPositionCodesAction({filter: '', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedPositionCodesAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

