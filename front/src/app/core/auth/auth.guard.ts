import {Store} from '@ngrx/store';
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthenticationService} from '../../services/authentication.service';
import {LogoutAction} from "./auth.action";

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router,
                private authenticationService: AuthenticationService,
                private store: Store<any>) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        if (this.authenticationService.isLoggedIn) {
            return true;
        } else {
            this.store.dispatch(new LogoutAction({redirectUrl: state.url}));
            return false;
        }
    }
}
