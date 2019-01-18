import {Component, OnDestroy} from '@angular/core';
import {BreadcrumbService} from './breadcrumb.service';
import {Subscription} from 'rxjs';
import {MenuItem} from 'primeng/primeng';
import {AuthState} from "./core/auth/auth.model";
import {Store} from "@ngrx/store";
import {LogoutAction} from "./core/auth/auth.action";

@Component({
    selector: 'app-breadcrumb',
    templateUrl: './app.breadcrumb.component.html'
})
export class AppBreadcrumbComponent implements OnDestroy {

    subscription: Subscription;

    items: MenuItem[];

    constructor(public breadcrumbService: BreadcrumbService,
                public store: Store<AuthState>) {
        this.subscription = breadcrumbService.itemsHandler.subscribe(response => {
            this.items = response;
        });
    }

    logout() {
        this.store.dispatch(new LogoutAction())
    }

    ngOnDestroy() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }
}
