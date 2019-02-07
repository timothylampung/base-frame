import {Component, OnInit} from "@angular/core";
import {AssetState} from "../asset.state";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectAssetCodes} from "./asset-code.selector";
import {Observable} from "rxjs";
import {FindPagedAssetCodesAction} from "./asset-code.action";
import {AssetCode} from "./asset-code-model";

@Component({
    selector: 'dex-asset-code-list-page',
    templateUrl: './asset-code-list.page.html'
})
export class AssetCodeListPage implements OnInit {

    assetCodes$: Observable<AssetCode[]>;
    searchForm: FormGroup;
    title = 'Senarai Kod Asset';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Kod Asset', routerLink: ['/administration/asset-codes/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<AssetState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.assetCodes$ = this.store.pipe(select(selectAssetCodes));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });
        this.store.dispatch(new FindPagedAssetCodesAction({filter: '', page: 1}));
        this.assetCodes$.subscribe(data=>{console.log(data)});
    }

    search() {
        this.store.dispatch(new FindPagedAssetCodesAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

