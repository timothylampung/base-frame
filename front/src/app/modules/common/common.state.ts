import {Department, DepartmentResult} from './departments/department.model';
import {ActionReducerMap, createFeatureSelector} from '@ngrx/store';
import {accountCodeResultReducer, accountCodesReducer} from './account-codes/account-code.reducer';
import {AccountCode, AccountCodeResult} from './account-codes/account-code.model';
import {AppState} from '../../core/core.state';
import {Period, PeriodResult} from './periods/period.model';
import {positionCodeReducer} from './position-codes/position-code.reducer';
import {PositionCodeResult} from './position-codes/position-code.model';
import {departmentResultReducer, departmentsReducer} from './departments/department.reducer';
import {periodResultReducer, periodsReducer} from './periods/period.reducer';
import {configurationResultReducer} from './configurations/configuration.reducer';
import {ConfigurationResult} from './configurations/configuration.model';
import {costCenterResultReducer, costCentersReducer} from "./cost-center/cost-center.reducer";
import {CostCenter, CostCenterResult} from "./cost-center/cost-center.model";
import {bankCodeResultReducer, bankCodesReducer} from "./bank-codes/bank-code.reducer";
import {BankCode, BankCodeResult} from "./bank-codes/bank-code.model";

export const FEATURE_NAME = 'common';
export const selectCommonState = createFeatureSelector<State, CommonState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<CommonState> = {
    accountCodeResult: accountCodeResultReducer,
    bankCodeResult: bankCodeResultReducer,
    accountCodes: accountCodesReducer,
    bankCodes: bankCodesReducer,
    configurationResult:configurationResultReducer,
    departmentResult: departmentResultReducer,
    costCenterResult: costCenterResultReducer,
    departments: departmentsReducer,
    costCenters: costCentersReducer,
    periodResult: periodResultReducer,
    periods: periodsReducer,
    positionCodeResult: positionCodeReducer
};

export interface CommonState {
    accountCodeResult: AccountCodeResult;
    bankCodeResult: BankCodeResult;
    accountCodes: AccountCode[];
    bankCodes: BankCode[];
    configurationResult: ConfigurationResult;
    departmentResult: DepartmentResult;
    costCenterResult: CostCenterResult;
    departments: Department[];
    costCenters: CostCenter[];
    periodResult: PeriodResult;
    periods: Period[];
    positionCodeResult: PositionCodeResult;
}

export interface State extends AppState {
    common: CommonState;
}
