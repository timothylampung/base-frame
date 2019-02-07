import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {select, Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {Group} from '../group.model';
import {selectGroups} from './group.selector';
import {FindPagedGroupsAction} from './group.action';
import {BreadcrumbService} from '../../../../breadcrumb.service';
import {IdentityState} from '../../identity.state';


@Component({
    selector: 'dex-group-list-page',
    templateUrl: './group-list.page.html'
})
export class GroupListPage implements OnInit {

    groups$: Observable<Group[]>;
    searchForm: FormGroup;
    title = 'Group List';
    cols = [
        {field: 'name', header: 'Name'},
        {field: 'memberCount', header: 'Member Count'},
    ];
    breadcrumbs = [
        {label: 'Administration'},
        {label: 'Groups', routerLink: ['/administration/groups/list']}
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public fb: FormBuilder,
                public store: Store<IdentityState>,
                public route: ActivatedRoute,
                public router: Router) {
        this.breadcrumbService.setItems(this.breadcrumbs);
        this.groups$ = this.store.pipe(select(selectGroups));
    }

    ngOnInit() {
        this.searchForm = this.fb.group({
            'keyword': [''],
        });
        this.store.dispatch(new FindPagedGroupsAction({filter: '', page: 1}));
    }

    search() {
        this.store.dispatch(new FindPagedGroupsAction({filter: this.searchForm.value.keyword, page: 1}));
    }
}

