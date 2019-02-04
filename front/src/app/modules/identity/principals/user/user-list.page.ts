import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from "./user.model";
import {BreadcrumbService} from "../../../../breadcrumb.service";
import {IdentityState, selectIdentityState} from "../../identity.state";
import {selectUserResultState, selectUsers} from "./user.selector";
import {FindPagedUsersAction} from "./user.action";
import {UserResult} from "./user-result.model";

@Component({
    selector: 'cng-user-list-page',
    templateUrl: './user-list.page.html'
})
export class UserListPage implements OnInit {

    users$: Observable<UserResult>;
    searchForm: FormGroup;
    title = 'Users';
    cols = [
        {field: 'key', header: 'Key'},
        {field: 'value', header: 'Value'},
    ];
    searchQuery : string = '';


    constructor(public fb: FormBuilder,
                public store: Store<IdentityState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.users$ = this.store.pipe(select(selectUserResultState));
    }

    ngOnInit() {
        this.search();
    }

    search() {
        this.store.dispatch(new FindPagedUsersAction({filter: this.searchQuery, page: 1}));
    }

    page(event) {
        // console.log(event)
        this.store.dispatch(new FindPagedUsersAction({filter: this.searchQuery, page: event.page + 1}));
    }
}

