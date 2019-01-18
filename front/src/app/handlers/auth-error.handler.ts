import {ErrorHandler, Injectable, Injector, NgZone} from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
    providedIn: 'root'
})
export class AuthErrorHandler implements ErrorHandler {
    constructor(private injector: Injector,
                private ngZone: NgZone) {
    }

    handleError(error: any): void {
        let router = this.injector.get(Router);
        if (error.status === 401 || error.status === 403) {
            this.ngZone.run(() => {
                return router.navigate(['/login'], {
                    replaceUrl: true
                });
            });
        }
        throw error;
    }
}
