import {Component, OnDestroy} from '@angular/core';
import {BreadcrumbService} from './breadcrumb.service';
import {Subscription} from 'rxjs';
import {MenuItem} from 'primeng/primeng';
import {AuthState} from "./core/auth/auth.model";
import {Store} from "@ngrx/store";
import {LogoutAction} from "./core/auth/auth.action";
import {ActivatedRouteSnapshot, NavigationEnd, Router, UrlSegment} from "@angular/router";

@Component({
    selector: 'app-breadcrumb',
    templateUrl: './app.breadcrumb.component.html'
})
export class AppBreadcrumbComponent implements OnDestroy {

    subscription: Subscription;

    items: MenuItem[];

    constructor(public breadcrumbService: BreadcrumbService,
                public router: Router,
                public store: Store<AuthState>) {

        this.subscription = breadcrumbService.itemsHandler.subscribe(response => {
            this.items = response;
        });

        this.router.events.subscribe(event => {
                if (event instanceof NavigationEnd) {
                    this.items = [];
                    this.parseRoute(this.router.routerState.snapshot.root);
                }
            }

        )

    }

    logout() {
        this.store.dispatch(new LogoutAction())
    }

    ngOnDestroy() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }


    private parseRoute(node: ActivatedRouteSnapshot) {
        console.log(node)
        if (node.data['breadcrumb']) {
            if(node.url.length){
                let urlSegments: UrlSegment[] = [];
                node.pathFromRoot.forEach(routerState => {
                    urlSegments = urlSegments.concat(routerState.url);
                });
                let url = urlSegments.map(urlSegment => {
                    return urlSegment.path;
                }).join('/');

                if(node.params.name){
                    console.log(node.params.name);
                    this.items.push({
                        label: node.params.name,
                        url: '/' + url
                    })
                }else{
                    this.items= node.data['breadcrumb'];
                }
            }
        }
        if (node.firstChild) {
            this.parseRoute(node.firstChild);
        }
    }

}
