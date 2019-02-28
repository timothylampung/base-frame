import {catchError, map, switchMap, takeUntil, tap} from 'rxjs/operators';
import {Injectable, OnDestroy, OnInit} from '@angular/core';
import {Router, RoutesRecognized} from '@angular/router';
import {Action} from '@ngrx/store';
import {Actions, Effect} from '@ngrx/effects';
import {Observable, Subject} from 'rxjs';
import {IdentityService} from '../../services/identity.service';
import {AuthenticationService} from '../../services/authentication.service';
import {AuthorizationService} from '../../services/authorization.service';
import {AUTH_ACL, AUTH_USER, TOKEN_NAME} from './auth.constant';
import {
    AuthActionTypes,
    LoginAction,
    LoginErrorAction,
    LoginSuccessAction,
    LogoutAction,
    SetTokenAction
} from "./auth.action";
import {AuthenticatedUser} from "../../modules/identity/principals/authenticated-user.model";

@Injectable()
export class AuthEffects implements OnInit, OnDestroy {
    redirectUrl: string = null;
    unsubscribed$: Subject<void> = new Subject();

    constructor(private actions$: Actions<Action>,
                private router: Router,
                private authenticationService: AuthenticationService,
                private authorizationService: AuthorizationService,
                private identityService: IdentityService) {

    }

    ngOnInit() {
        this.router.events
            .pipe(
                takeUntil(this.unsubscribed$),
            )
            .subscribe(
                (v) => {
                    if (v instanceof RoutesRecognized) {
                        this.redirectUrl = v.state.root.queryParams.redirectTo;
                    }
                }
            );
    }

    ngOnDestroy(): void {
        this.unsubscribed$.next();
        this.unsubscribed$.complete();
    }

    @Effect()
    login(): Observable<Action> {
        return this.actions$
            .ofType(AuthActionTypes.LOGIN)
            .pipe(
                switchMap((action: LoginAction) => {
                    const username = action.username;
                    const password = action.password;
                    return this.authenticationService.login(username, password)
                        .pipe(
                            switchMap(token => {
                                return [
                                    new SetTokenAction({token: token}),
                                ];
                            }),
                            catchError(err => {
                                return [new LoginErrorAction((err))];
                            })
                        );
                })
            );
    }

    @Effect()
    setToken(): Observable<Action> {
        return this.actions$
            .ofType(AuthActionTypes.SET_TOKEN)
            .pipe(
                tap((action: SetTokenAction) => {
                    localStorage.setItem(TOKEN_NAME, action.payload.token);
                }),
                switchMap(() => [new LoginSuccessAction()])
            );
    }

    @Effect({dispatch: false})
    loginSuccess(): Observable<Action> {
        return this.actions$
            .ofType(AuthActionTypes.LOGIN_SUCCESS)
            .pipe(
                tap(() => {
                    this.populateRoles();
                    this.populateUser();
                    this.navigateOnSuccess();
                })
            );
    }

    @Effect({dispatch: false})
    logout(): Observable<Action> {
        return this.actions$
            .ofType(AuthActionTypes.LOGOUT)
            .pipe(
                tap((action: LogoutAction) => {
                    const payload = action.payload;
                    console.log(`%c`, 'background:pink', payload);
                    localStorage.removeItem(TOKEN_NAME);
                    localStorage.removeItem(AUTH_USER);
                    localStorage.removeItem(AUTH_ACL);
                    this.authorizationService.flushRoles();
                    this.router.navigate(['/login']);
                })
            );
    }

    populateRoles(): void {
        console.log('populate roles');
        this.authorizationService.flushRoles();
        this.authenticationService.roles.forEach((role: string) => {
            this.authorizationService.attachRole(role);
        });
    }

    populateUser(): void {
        console.log('populate user');
        this.identityService.findAuthenticatedUser().pipe(
            map((user: AuthenticatedUser) => {
                this.authenticationService.authenticatedUser = user;
                localStorage.setItem(AUTH_USER, JSON.stringify(user));
            }))
            .toPromise();
    }

    navigateOnSuccess(): void {
        if (this.redirectUrl) {
            this.router.navigateByUrl(this.redirectUrl);
        } else {
            this.router.navigate(['/']);
        }
    }
}
