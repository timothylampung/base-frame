import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectPartCodeResultState, selectPartCodes} from "./part-code-selector";
import {Observable} from "rxjs";
import {FindPagedPartCodesAction} from "./part-code-action";
import {PartCode, PartCodeResult} from "./part-code-model";
import {InventoryState} from "../inventory.state";
import {Location} from "../../asset/locations/location.model";
import {FindPagedUsersAction} from "../../identity/principals/user/user.action";

@Component({
    selector: 'dex-part-code-list-page',
    templateUrl: './part-code-list.page.html'
})
export class PartCodeListPage implements OnInit {

    partCodes$: Observable<PartCodeResult>;
    searchForm: FormGroup;
    searchQuery : string = '';
    selectedRow : PartCode = null;


    title = 'Part Codes';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'PartCodes', routerLink: ['/administration/part-codes/list']}
    ];
    display : boolean = false;


    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<InventoryState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.partCodes$ = this.store.pipe(select(selectPartCodeResultState));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });
        this.store.dispatch(new FindPagedPartCodesAction({filter: '', page: 1}));
        this.partCodes$.subscribe(data=>{console.log(data)});
    }

    search() {
        this.store.dispatch(new FindPagedPartCodesAction({filter: this.searchQuery, page: 1}));
    }

    page(event) {
        // console.log(event)
        this.store.dispatch(new FindPagedPartCodesAction({filter: this.searchQuery, page: event.page + 1}));
    }
}

