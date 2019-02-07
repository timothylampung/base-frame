import {Component, OnInit} from "@angular/core";
import {AssetState} from "../asset.state";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {selectLocationResultState, selectLocations} from "./location-selector";
import {Observable} from "rxjs";
import {FindPagedLocationsAction} from "./location-action";
import {Location, LocationResult} from "./location-model";

@Component({
    selector: 'dex-location-list-page',
    templateUrl: './location-list.page.html'
})
export class LocationListPage implements OnInit {

    locations$: Observable<LocationResult>;
    searchForm: FormGroup;
    searchQuery : string = '';

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
                public router: Router) {
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

    search() {
        this.store.dispatch(new FindPagedLocationsAction({filter: this.searchQuery, page: 1}));
    }
}

