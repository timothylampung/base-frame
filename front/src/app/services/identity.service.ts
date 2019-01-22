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
import {Staff, StaffResult} from "../modules/identity/staffs/staff.model";
import {Beneficiary, BeneficiaryResult} from "../modules/identity/beneficiary/beneficiary.model";

@Injectable()
export class IdentityService {
    private IDENTITY_API: string = environment.endpoint + '/api/identity';

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
        return this.http.post(this.IDENTITY_API + '/users', JSON.stringify(user),
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
    // STAFF
    // ===================================================================================================================

    findStaffs(): Observable<Staff[]> {
        console.log('findStaffs');
        return this.http.get<Staff[]>(this.IDENTITY_API + '/staffs');
    }

    findStaffByCode(code: string): Observable<Staff> {
        return this.http.get<Staff>(this.IDENTITY_API + '/staffs/' + code);
    }

    findPagedStaffs(filter: string, page: number): Observable<StaffResult> {
        console.log('findPagedStaffs');
        return this.http.get<StaffResult>(this.IDENTITY_API + '/staffs',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            });
    }

    saveStaff(staff: Staff): Observable<string> {
        return this.http.post(this.IDENTITY_API + '/staffs', JSON.stringify(staff),
            {observe: 'body', responseType: 'text'});
    }

    updateStaff(staff: Staff): Observable<string> {
        return this.http.put(this.IDENTITY_API + '/staffs/' + staff.code, JSON.stringify(staff),
            {observe: 'body', responseType: 'text'});
    }

    removeStaff(staff: Staff): Observable<string> {
        return this.http.delete(this.IDENTITY_API + '/staffs/' + staff.code,
            {observe: 'body', responseType: 'text'});
    }

    // ===================================================================================================================
    // BENEFICIARIES
    // ===================================================================================================================

    findBeneficiaries(): Observable<Beneficiary[]> {
        console.log('findBeneficiaries');
        return this.http.get<Beneficiary[]>(this.IDENTITY_API + '/beneficiaries');
    }

    findBeneficiaryByCode(code: string): Observable<Beneficiary> {
        return this.http.get<Beneficiary>(this.IDENTITY_API + '/beneficiaries/' + code);
    }

    findPagedBeneficiaries(filter: string, page: number): Observable<BeneficiaryResult> {
        console.log('findPagedBeneficiaries');
        return this.http.get<BeneficiaryResult>(this.IDENTITY_API + '/beneficiaries',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            });
    }

    saveBeneficiary(beneficiary: Beneficiary): Observable<string> {
        return this.http.post(this.IDENTITY_API + '/beneficiaries', JSON.stringify(beneficiary),
            {observe: 'body', responseType: 'text'});
    }

    updateBeneficiary(beneficiary: Beneficiary): Observable<string> {
        return this.http.put(this.IDENTITY_API + '/beneficiaries/' + beneficiary.code, JSON.stringify(beneficiary),
            {observe: 'body', responseType: 'text'});
    }

    removeBeneficiary(beneficiary: Beneficiary): Observable<string> {
        return this.http.delete(this.IDENTITY_API + '/beneficiaries/' + beneficiary.code,
            {observe: 'body', responseType: 'text'});
    }

    // ===================================================================================================================
    // PRIVATE METHODS
    // ===================================================================================================================
    private handleError(error: Response | any) {
        const body: any = error.json();
        return observableThrowError(body);
    }

}