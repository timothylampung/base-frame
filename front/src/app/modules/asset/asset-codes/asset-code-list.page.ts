import {Component, OnInit} from "@angular/core";
import {AssetState} from "../asset.state";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectAssetCodeResultState} from "./asset-code.selector";
import {Observable} from "rxjs";
import {FindPagedAssetCodesAction, RemoveAssetCodeAction} from "./asset-code.action";
import {AssetCode, AssetCodeResult} from "./asset-code-model";
import {ConfirmationService, Message} from "primeng/api";
import {Location} from "../locations/location.model";
import {RemoveLocationAction} from "../locations/location-action";

@Component({
    selector: 'dex-asset-code-list-page',
    templateUrl: './asset-code-list.page.html'
})
export class AssetCodeListPage implements OnInit {

    assetCodes$: Observable<AssetCodeResult>;
    searchForm: FormGroup;
    searchQuery: string = '';
    selectedRow: AssetCode = null;
    display : boolean = false;
    displayDelete: boolean = false;
    msgs: Message[] = [];

    title = 'Asset Codes';
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
                public router: Router,
                public confirmationService: ConfirmationService) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.assetCodes$ = this.store.pipe(select(selectAssetCodeResultState));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });
        this.store.dispatch(new FindPagedAssetCodesAction({filter: '', page: 1}));
        this.assetCodes$.subscribe(data=>{console.log(data)});
    }

    dialogClosed(result) {
        console.log(result);
        this.display = !result;
    }

    deleteLocation() {
        this.displayDelete = true;
    }

    cancelDelete() {
        this.displayDelete = false;
    }

    confirmDelete(rowData : AssetCode) {
        this.confirmationService.confirm({
            message: 'Delete asset code?',
            header: 'Delete Confirmation',
            icon: 'pi pi-info-circle',
            accept: () => {
                this.msgs = [{severity:'info', summary:'Asset code deleted'}];
                this.store.dispatch(new RemoveAssetCodeAction(rowData));
                console.log(rowData);
            },
            reject: () => {
                this.msgs = [{severity:'info', summary:'Delete cancelled'}];
            }
        });
    }

    search() {
        this.store.dispatch(new FindPagedAssetCodesAction({filter: this.searchQuery, page: 1}));
    }
    page(event) {
        // console.log(event)
        this.store.dispatch(new FindPagedAssetCodesAction({filter: this.searchQuery, page: event.page + 1}));
    }
}

