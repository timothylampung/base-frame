import {Observable, throwError as observableThrowError} from 'rxjs';
import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {environment} from '../../environments/environment';
import {AuthorizationService} from './authorization.service';
import {AUTH_USER} from '../core/auth/auth.constant';
import {AuthState} from "../core/auth/auth.model";
import {LogoutAction} from "../core/auth/auth.action";
import {AuthenticatedUser} from "../modules/identity/principals/authenticated-user.model";
import {Principal} from "../modules/identity/principals/principal.model";
import {UserResult} from "../modules/identity/principals/user/user-result.model";
import {User} from "../modules/identity/principals/user/user.model";
import {GroupResult} from "../modules/identity/principals/group-result.model";
import {Group} from "../modules/identity/principals/group.model";
import {GroupMember} from "../modules/identity/principals/group-member.model";
import {Actor} from "../modules/identity/actors/actor.model";

@Injectable()
export class IdentityService {
    private IDENTITY_API: string = environment.endpoint + '/api/identity';
    private REGISTRATION_API: string = environment.endpoint + '/api/registration';

    constructor(private http: HttpClient,
                private router: Router,
                private authorizationService: AuthorizationService,
                private store: Store<AuthState>) {
    }

    logout(): void {
        this.store.dispatch(new LogoutAction());
    }

    getAuthenticatedUser(): AuthenticatedUser {
        return <AuthenticatedUser>JSON.parse(localStorage.getItem(AUTH_USER));
    }

    findAuthenticatedUser(): Observable<AuthenticatedUser> {
        return this.http.get<AuthenticatedUser>(environment.endpoint + '/api/identity/authenticated-user');
    }

    // ===================================================================================================================
    // PRINCIPAL
    // ===================================================================================================================

    findPrincipals(): Observable<Principal[]> {
        console.log('findPrincipals');
        return this.http.get<Principal[]>(this.IDENTITY_API + '/principals');
    }

    findPrincipalByName(name: string): Observable<Principal> {
        return this.http.get<Principal>(this.IDENTITY_API + '/principals/' + name);
    }

    // ===================================================================================================================
    // USER
    // ===================================================================================================================

    findPagedUsers(filter: string, page: number): Observable<UserResult> {
        console.log('findPagedUsers');
        return this.http.get<UserResult>(this.IDENTITY_API + '/users?page=' + page);
    }

    findUsers(): Observable<User[]> {
        console.log('findUsers');
        return this.http.get<User[]>(this.IDENTITY_API + '/users');
    }

    findUserByUsername(name: string): Observable<User> {
        return this.http.get<User>(this.IDENTITY_API + '/users/' + name);
    }

    saveUser(user: User): Observable<string> {
        return this.http.post(this.IDENTITY_API + '/user', JSON.stringify(user),
            {observe: 'body', responseType: 'text'});
    }

    updateUser(user: User): Observable<string> {
        return this.http.put(this.IDENTITY_API + '/users/' + user.name, JSON.stringify(user),
            {observe: 'body', responseType: 'text'});
    }

    removeUser(user: User): Observable<string> {
        return this.http.delete(this.IDENTITY_API + '/users/' + user.name,
            {observe: 'body', responseType: 'text'});
    }

    // ===================================================================================================================
    // GROUP
    // ===================================================================================================================

    findPagedGroups(filter: string, page: number): Observable<GroupResult> {
        console.log('findPagedGroups');
        const url = `${this.IDENTITY_API}/groups?filter=${filter}&page=${page}`;
        return this.http.get<GroupResult>(url);
    }

    findGroups(): Observable<Group[]> {
        console.log('findGroups');
        return this.http.get<Group[]>(this.IDENTITY_API + '/groups');
    }

    findGroupByName(name: string): Observable<Group> {
        return this.http.get<Group>(this.IDENTITY_API + '/groups/' + name);
    }

    findGroupMembers(group: Group): Observable<GroupMember[]> {
        return this.http.get<GroupMember[]>(this.IDENTITY_API + '/groups/' + group.name + '/group-members');
    }

    saveGroup(group: Group): Observable<string> {
        return this.http.post(this.IDENTITY_API + '/groups', JSON.stringify(group),
            {observe: 'body', responseType: 'text'});
    }

    updateGroup(group: Group): Observable<string> {
        return this.http.put(this.IDENTITY_API + '/groups/' + group.name, JSON.stringify(group),
            {observe: 'body', responseType: 'text'});
    }

    removeGroup(group: Group): Observable<string> {
        return this.http.delete(this.IDENTITY_API + '/groups/' + group.name,
            {observe: 'body', responseType: 'text'});
    }

    addGroupMember(group: Group, groupMember: GroupMember): Observable<string> {
        return this.http.put(this.IDENTITY_API + '/groups/' + group.name + '/add/', JSON.stringify(groupMember),
            {observe: 'body', responseType: 'text'});
    }

    // ===================================================================================================================
    // ACTOR
    // ===================================================================================================================

    findActors(): Observable<Actor[]> {
        console.log('findActors');
        return this.http.get<Actor[]>(this.IDENTITY_API + '/actors');
    }

    findActorByCode(code: string): Observable<Actor> {
        console.log('findActorByCode');
        return this.http.get<Actor>(this.IDENTITY_API + '/actors/' + code);
    }

    findActorByIdentityNo(identityNo: string): Observable<Actor> {
        console.log('findActorByIdentityNo');
        return this.http.get<Actor>(this.IDENTITY_API + '/actors/' + identityNo);
    }

    // ===================================================================================================================
    // PRIVATE METHODS
    // ===================================================================================================================
    private handleError(error: Response | any) {
        const body: any = error.json();
        return observableThrowError(body);
    }

}
