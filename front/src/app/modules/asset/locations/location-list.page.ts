import {Component, OnInit} from "@angular/core";
import {AssetState} from "../asset.state";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectLocationResultState} from "./location-selector";
import {Observable} from "rxjs";
import {FindPagedLocationsAction, RemoveLocationAction} from "./location-action";
import {Location, LocationResult} from "./location-model";
import {ConfirmationService, Message} from "primeng/api";

@Component({
    selector: 'dex-location-list-page',
    templateUrl: './location-list.page.html'
})
export class LocationListPage implements OnInit {

    locations$: Observable<LocationResult>;
    searchForm: FormGroup;
    searchQuery : string = '';
    selectedRow : Location = null;
    display : boolean = false;
    displayDelete: boolean = false;
    msgs: Message[] = [];

    title = 'Locations';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    breadcrumbs = [
        {label: 'Pengurusan'},
        {label: 'Locations', routerLink: ['/administration/locations/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<AssetState>,
                public route: ActivatedRoute,
                public router: Router,
                public confirmationService: ConfirmationService) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.locations$ = this.store.pipe(select(selectLocationResultState));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });
        this.store.dispatch(new FindPagedLocationsAction({filter: '', page: 1}));
        this.locations$.subscribe(data=>{console.log(data)});
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

    confirmDelete(rowData : Location) {
        this.confirmationService.confirm({
            message: 'Are you sure you want to delete this?',
            header: 'Delete Confirmation',
            icon: 'pi pi-info-circle',
            accept: () => {
                this.msgs = [{severity:'info', summary:'Location Deleted'}];
                this.store.dispatch(new RemoveLocationAction(rowData));
                console.log(rowData);
            },
            reject: () => {
                this.msgs = [{severity:'info', summary:'Delete cancelled'}];
            }
        });
    }

    search() {
        this.store.dispatch(new FindPagedLocationsAction({filter: this.searchQuery, page: 1}));
    }

    page(event) {
        // console.log(event)
        this.store.dispatch(new FindPagedLocationsAction({filter: this.searchQuery, page: event.page + 1}));
    }


}

