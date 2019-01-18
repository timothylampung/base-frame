import {catchError, map} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';
import {throwError} from 'rxjs';
import {JwtHelperService} from '@auth0/angular-jwt';
import {TOKEN_AUTH_PASSWORD, TOKEN_AUTH_USERNAME} from '../core/auth/auth.constant';
import {environment} from '../../environments/environment';
import {AuthenticatedUser} from "../modules/identity/principals/authenticated-user.model";

@Injectable()
export class AuthenticationService {
    static AUTH_TOKEN = '/oauth/token';

    private _roles: string[];
    private _authenticatedUser: AuthenticatedUser;
    public token: string;
    public parsedToken: any;

    constructor(private http: Http,
                private jwtHelper: JwtHelperService) {
    }

    login(username: string, password: string) {
        const body = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&grant_type=password`;
        const headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        headers.append('Authorization', 'Basic ' + btoa(TOKEN_AUTH_USERNAME + ':' + TOKEN_AUTH_PASSWORD));

        console.log(environment.endpoint + AuthenticationService.AUTH_TOKEN);
        return this.http.post(environment.endpoint + AuthenticationService.AUTH_TOKEN, body, {headers})
            .pipe(
                map(res => res.json()),
                map((res) => {
                    console.log('res; ' + res);
                    if (res.access_token) {
                        this.token = res.access_token;
                        this.parseRoles();
                        return this.token;
                    }
                    console.log('res; ' + res);
                    return null;
                }),
                catchError(err => throwError(err))
            );
    }

    get isLoggedIn() {
        const token = this.jwtHelper.tokenGetter();
        if (!token) return false;

        return !this.jwtHelper.isTokenExpired();
    }

    get authenticatedUser(): AuthenticatedUser {
        return this._authenticatedUser;
    }

    set authenticatedUser(value: AuthenticatedUser) {
        this._authenticatedUser = value;
    }

    get roles(): string[] {
        return this._roles || [];
    }

    parseRoles(): void {
        this.parsedToken = this.jwtHelper.decodeToken(this.token);
        console.log('parseRoles: ' + JSON.stringify(this.parsedToken));
        this._roles = this.parsedToken.authorities;
        console.log('role: ' + this._roles);
    }
}
