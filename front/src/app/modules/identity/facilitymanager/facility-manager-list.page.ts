import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {IdentityState} from "../identity.state";
import {selectFacilityManagers} from "./facility-manager.selector";
import {FindPagedFacilityManagersAction} from "./facility-manager.action";
import {FacilityManager} from "./facility-manager.model";

@Component({
    selector: 'cng-facility-Manager-list-page',
    templateUrl: './facility-manager-list.page.html'
})
export class FacilityManagerListPage implements OnInit {

    facilityManagers$: Observable<FacilityManager[]>;
    searchForm: FormGroup;
    title = 'Facility Managers';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];


    constructor(public fb: FormBuilder,
                public store: Store<IdentityState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.facilityManagers$ = this.store.pipe(select(selectFacilityManagers));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedFacilityManagersAction({filter: '%', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedFacilityManagersAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

