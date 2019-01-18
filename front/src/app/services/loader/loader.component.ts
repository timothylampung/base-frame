import {Component, OnInit} from '@angular/core';
import {RouteConfigLoadEnd, RouteConfigLoadStart, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {LoaderState} from "./loader-state.interface";
import {LoaderService} from "./loader.service";

@Component({
    selector: 'cng-loader',
    templateUrl: './loader.component.html',
    styleUrls: ['./loader.component.scss']
})
export class LoaderComponent implements OnInit {
    show = false;

    private subscription: Subscription;

    constructor(private loaderService: LoaderService,
                private router: Router) {
    }

    ngOnInit() {
        this.subscription = this.loaderService.loaderState
            .subscribe((state: LoaderState) => {
                this.show = state.show;
            });

        this.router.events
            .subscribe(evt => {
                if (evt instanceof RouteConfigLoadStart) {
                    this.show = true;
                } else if (evt instanceof RouteConfigLoadEnd) {
                    this.show = false;
                }
            })

    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }
}
