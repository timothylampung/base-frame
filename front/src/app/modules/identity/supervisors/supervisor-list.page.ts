import {Supervisor} from "./supervisor.model";
import {selectSupervisors} from "./supervisor.selector";
import {FindPagedSupervisorsAction} from "./supervisor.action";
import {Component, OnInit} from "@angular/core";
import {Observable} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {select, Store} from "@ngrx/store";
import {IdentityState} from "../identity.state";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";

@Component({
    selector: 'cng-supervisor-list-page',
    templateUrl: './supervisor-list.page.html'
})
export class SupervisorListPage implements OnInit {

    supervisors$: Observable<Supervisor[]>;
    searchForm: FormGroup;
    title = 'Supervisors';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];


    constructor(public fb: FormBuilder,
                public store: Store<IdentityState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.supervisors$ = this.store.pipe(select(selectSupervisors));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });

        this.store.dispatch(new FindPagedSupervisorsAction({filter: '', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedSupervisorsAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

