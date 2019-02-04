import {Component, OnInit} from "@angular/core";
import {AssetState} from "../asset.state";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectAssets} from "./asset-selector";
import {Observable} from "rxjs";
import {FindPagedAssetsAction} from "./asset-action";
import {Asset} from "./asset-model";

@Component({
    selector: 'cng-asset-list-page',
    templateUrl: './asset-list.page.html'
})
export class AssetListPage implements OnInit {

    assets$: Observable<Asset[]>;
    searchForm: FormGroup;
    title = 'Senarai Asset';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Assets', routerLink: ['/administration/assets/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<AssetState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.assets$ = this.store.pipe(select(selectAssets));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });
        this.store.dispatch(new FindPagedAssetsAction({filter: '', page: 1}));
        this.assets$.subscribe(data=>{console.log(data)});
    }

    search() {
        this.store.dispatch(new FindPagedAssetsAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}
