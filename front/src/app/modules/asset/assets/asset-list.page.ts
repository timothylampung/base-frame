import {Component, OnInit} from "@angular/core";
import {AssetState} from "../asset.state";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectAssetResultState} from "./asset.selector";
import {Observable} from "rxjs";
import {FindPagedAssetsAction, RemoveAssetAction} from "./asset.action";
import {Asset, AssetResult} from "./asset.model";
import {ConfirmationService, Message} from "primeng/api";

@Component({
    selector: 'dex-asset-list-page',
    templateUrl: './asset-list.page.html'
})
export class AssetListPage implements OnInit {

    assets$: Observable<AssetResult>;
    searchForm: FormGroup;
    searchQuery : string = '';
    selectedRow: Asset = null;
    display : boolean = false;
    displayUpload : boolean = false;
    displayDelete: boolean = false;
    msgs: Message[] = [];

    title = 'Assets';
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
                public router: Router,
                public confirmationService: ConfirmationService) {
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
        console.log(this.assets$);
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

    confirmDelete(rowData : Asset) {
        this.confirmationService.confirm({
            message: 'Delete asset?',
            header: 'Delete Confirmation',
            icon: 'pi pi-info-circle',
            accept: () => {
                this.msgs = [{severity:'info', summary:'Asset deleted'}];
                this.store.dispatch(new RemoveAssetAction(rowData));
                console.log(rowData);
            },
            reject: () => {
                this.msgs = [{severity:'info', summary:'Delete cancelled'}];
            }
        });
    }

    search() {
        console.log(this.searchQuery);
        this.store.dispatch(new FindPagedAssetsAction({filter: this.searchQuery, page: 1}));
    }

    showDialog() {
        this.displayUpload = true;
    }

    closeDialog(){
        this.displayUpload = false;
    }

    page(event) {
        // console.log(event)
        this.store.dispatch(new FindPagedAssetsAction({filter: this.searchQuery, page: event.page + 1}));
    }
}
