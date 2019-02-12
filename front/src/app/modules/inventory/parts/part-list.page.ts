import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectPartResultState, selectParts} from "./part-selector";
import {Observable} from "rxjs";
import {FindPagedPartsAction} from "./part-action";
import {Part, PartResult} from "./part-model";
import {InventoryState} from "../inventory.state";

@Component({
    selector: 'dex-part-list-page',
    templateUrl: './part-list.page.html'
})
export class PartListPage implements OnInit {

    parts$: Observable<PartResult>;
    searchForm: FormGroup;
    searchQuery : string = '';

    title = 'Parts';
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
        this.parts$ = this.store.pipe(select(selectPartResultState));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });
        this.store.dispatch(new FindPagedPartsAction({filter: '', page: 1}));
        this.parts$.subscribe(data=>{console.log(data)});
    }

    search() {
        this.store.dispatch(new FindPagedPartsAction({filter: this.searchQuery, page: 1}));
    }
}

