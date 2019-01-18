import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {AccountCode, AccountCodeResult} from '../modules/common/account-codes/account-code.model';
import {Department, DepartmentResult} from '../modules/common/departments/department.model';
import {Period, PeriodResult} from '../modules/common/periods/period.model';
import {PositionCode, PositionCodeResult} from '../modules/common/position-codes/position-code.model';
import {CostCenter, CostCenterResult} from "../modules/common/cost-center/cost-center.model";
import {BankCode, BankCodeResult} from "../modules/common/bank-codes/bank-code.model";

@Injectable()
export class CommonService {

    private COMMON_API: string = environment.endpoint + '/api/common';

    constructor(public http: HttpClient) {
    }

    // ===================================================================================================================
    // PERIODS
    // ===================================================================================================================

    findPagedPeriods(filter: string, page: number): Observable<PeriodResult> {
        return this.http.get<PeriodResult>(this.COMMON_API + '/periods',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            });
    }

    findPeriods(): Observable<Period[]> {
        return this.http.get<Period[]>(this.COMMON_API + '/periods');
    }

    savePeriod(code: Period) {
        return this.http.post(this.COMMON_API + '/periods', JSON.stringify(code));
    }

    updatePeriod(code: Period) {
        return this.http.put(this.COMMON_API + '/periods/' + code.code, JSON.stringify(code));
    }

    removePeriod(code: Period) {
        return this.http.delete(this.COMMON_API + '/periods/' + code.code);
    }

    // ===================================================================================================================
    // ACCOUNT CODES
    // ===================================================================================================================

    findPagedAccountCodes(filter: string, page: number): Observable<AccountCodeResult> {
        return this.http.get<AccountCodeResult>(this.COMMON_API + '/account-codes',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            });
    }

    findAccountCodes(): Observable<AccountCode[]> {
        return this.http.get<AccountCode[]>(this.COMMON_API + '/account-codes');
    }

    saveAccountCode(code: AccountCode) {
        return this.http.post(this.COMMON_API + '/account-codes', JSON.stringify(code));
    }

    updateAccountCode(code: AccountCode) {
        return this.http.put(this.COMMON_API + '/account-codes/' + code.code, JSON.stringify(code));
    }

    removeAccountCode(code: AccountCode) {
        return this.http.delete(this.COMMON_API + '/account-codes/' + code.code);
    }

    // ===================================================================================================================
    // BANK CODES
    // ===================================================================================================================

    findPagedBankCodes(filter: string, page: number): Observable<BankCodeResult> {
        return this.http.get<BankCodeResult>(this.COMMON_API + '/bank-codes',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            });
    }

    findBankCodes(): Observable<BankCode[]> {
        return this.http.get<BankCode[]>(this.COMMON_API + '/bank-codes');
    }

    saveBankCode(code: BankCode) {
        return this.http.post(this.COMMON_API + '/bank-codes', JSON.stringify(code));
    }

    updateBankCode(code: BankCode) {
        return this.http.put(this.COMMON_API + '/bank-codes/' + code.code, JSON.stringify(code));
    }

    removeBankCode(code: BankCode) {
        return this.http.delete(this.COMMON_API + '/bank-codes/' + code.code);
    }

    // ===================================================================================================================
    // DEPARTMENTS
    // ===================================================================================================================

    findPagedDepartments(filter: string, page: number): Observable<DepartmentResult> {
        return this.http.get<DepartmentResult>(this.COMMON_API + '/departments',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            });
    }

    findDepartments(): Observable<Department[]> {
        return this.http.get<Department[]>(this.COMMON_API + '/departments');
    }

    saveDepartment(code: Department) {
        return this.http.post(this.COMMON_API + '/departments', JSON.stringify(code));
    }

    updateDepartment(code: Department) {
        return this.http.put(this.COMMON_API + '/departments/' + code.code, JSON.stringify(code));
    }

    removeDepartment(code: Department) {
        return this.http.delete(this.COMMON_API + '/departments/' + code.code);
    }

    // ===================================================================================================================
    // COST CENTER
    // ===================================================================================================================

    findPagedCostCenters(filter: string, page: number): Observable<CostCenterResult> {
        return this.http.get<CostCenterResult>(this.COMMON_API + '/cost-centers',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            });
    }

    findCostCenters(): Observable<CostCenter[]> {
        return this.http.get<CostCenter[]>(this.COMMON_API + '/cost-centers');
    }

    saveCostCenter(code: CostCenter) {
        return this.http.post(this.COMMON_API + '/cost-centers', JSON.stringify(code));
    }

    updateCostCenter(code: CostCenter) {
        return this.http.put(this.COMMON_API + '/cost-centers/' + code.code, JSON.stringify(code));
    }

    removeCostCenter(code: CostCenter) {
        return this.http.delete(this.COMMON_API + '/cost-centers/' + code.code);
    }

    // ===================================================================================================================
    // POSITION CODES
    // ===================================================================================================================

    findPagedPositionCodes(filter: string, page: number): Observable<PositionCodeResult> {
        return this.http.get<PositionCodeResult>(this.COMMON_API + '/position-codes',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            });
    }

    findPositionCodes(): Observable<Position[]> {
        return this.http.get<Position[]>(this.COMMON_API + '/position-codes');
    }

    savePositionCode(code: PositionCode) {
        return this.http.post(this.COMMON_API + '/position-codes', JSON.stringify(code));
    }

    updatePositionCode(code: PositionCode) {
        return this.http.put(this.COMMON_API + '/position-codes/' + code.code, JSON.stringify(code));
    }

    removePositionCode(code: PositionCode) {
        return this.http.delete(this.COMMON_API + '/position-codes/' + code.code);
    }

}
