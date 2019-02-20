import {Component, OnInit} from "@angular/core";
import {AssetState} from "../asset.state";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectAssetResultState, selectAssets} from "./asset-selector";
import {Observable} from "rxjs";
import {FindPagedAssetsAction} from "./asset-action";
import {Asset, AssetResult} from "./asset-model";
import {FindPagedUsersAction} from "../../identity/principals/user/user.action";

@Component({
    selector: 'dex-asset-list-page',
    templateUrl: './asset-list.page.html'
})
export class AssetListPage implements OnInit {

    assets$: Observable<AssetResult>;
    searchForm: FormGroup;
    searchQuery : string = '';
    selectedRow: Asset = null;

    title = 'Assets';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Assets', routerLink: ['/administration/assets/list']}
    ];
    display : boolean = false;

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<AssetState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.assets$ = this.store.pipe(select(selectAssetResultState));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],

        });
        this.store.dispatch(new FindPagedAssetsAction({filter: '', page: 1}));
        this.assets$.subscribe(data=>{console.log(data)});
        console.log(this.searchForm)
    }

    search() {
        console.log(this.searchQuery);
        this.store.dispatch(new FindPagedAssetsAction({filter: this.searchQuery, page: 1}));
    }

    page(event) {
        // console.log(event)
        this.store.dispatch(new FindPagedAssetsAction({filter: this.searchQuery, page: event.page + 1}));
    }
}
