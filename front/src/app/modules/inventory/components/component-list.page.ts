import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectComponentResultState, selectComponents} from "./component-selector";
import {Observable} from "rxjs";
import {FindPagedComponentsAction} from "./component-action";
import {ComponentResult} from "./component-model";
import {InventoryState} from "../inventory.state";

@Component({
    selector: 'dex-component-list-page',
    templateUrl: './component-list.page.html'
})
export class ComponentListPage implements OnInit {

    components$: Observable<ComponentResult>;
    searchForm: FormGroup;
    searchQuery : string = '';

    title = 'Components';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Components', routerLink: ['/administration/components/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<InventoryState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.components$ = this.store.pipe(select(selectComponentResultState));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });
        this.store.dispatch(new FindPagedComponentsAction({filter: '', page: 1}));
        this.components$.subscribe(data=>{console.log(data)});
    }

    search() {
        this.store.dispatch(new FindPagedComponentsAction({filter: this.searchQuery, page: 1}));
    }
}

