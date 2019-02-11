import {Component, OnInit} from "@angular/core";
import {InventoryState} from "../inventory.state";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {Observable} from "rxjs";
import {PartCode} from "./part-code-model";
import {selectPartCodes} from "./part-code-selector";
import {FindPagedPartCodesAction} from "./part-code-action";


@Component({
    selector: 'dex-part-list-page',
    templateUrl: './part-code-list.page.html'
})
export class PartCodeListPage implements OnInit {


    partCodes$: Observable<PartCode[]>;
    searchForm: FormGroup;
    title = 'Senarai Part Codes';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Parts', routerLink: ['/administration/parts/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<InventoryState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.partCodes$ = this.store.pipe(select(selectPartCodes));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });
        this.store.dispatch(new FindPagedPartCodesAction({filter: '', page: 1}));
        this.partCodes$.subscribe(data=>{console.log(data)});
    }

    search() {
        this.store.dispatch(new FindPagedPartCodesAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

