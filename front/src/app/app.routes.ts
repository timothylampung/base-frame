import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {LoginPage} from './login/login.page';
import {AppShellComponent} from './app-shell/app-shell.component';
import {AuthGuard} from './core/auth/auth.guard';
import {NotFoundPage} from './static/pages/not-found.page';
import {DepartmentListPage} from './modules/common/departments/department-list.page';
import {PeriodListPage} from './modules/common/periods/period-list.page';
import {AccountCodeListPage} from './modules/common/account-codes/account-code-list.page';
import {UserListPage} from './modules/identity/principals/user/user-list.page';
import {ConfigurationListPage} from './modules/common/configurations/configuration-list.page';
import {GroupListPage} from './modules/identity/principals/groups/group-list.page';
import {CostCenterListPage} from "./modules/common/cost-center/cost-center-list.page";
import {BankCodeListPage} from "./modules/common/bank-codes/bank-code-list.page";
import {StaffListPage} from "./modules/identity/staffs/staff-list.page";
import {BeneficiaryListPage} from "./modules/identity/beneficiary/beneficiary-list.page";
import {PositionCodeListPage} from "./modules/common/position-codes/position-code-list.page";
import {AssetCodeListPage} from "./modules/asset/asset-codes/asset-code-list.page";

export const routes: Routes = [
    {path: 'login', component: LoginPage},
    {
        path: '',
        component: AppShellComponent,
        canActivate: [AuthGuard],
        children: [
            {path: 'asset',
                children: [
                    {
                        path: 'asset-codes/list',
                        component: AssetCodeListPage,
                    },
                ]
            },
            {
                path: 'administration',
                children: [
                    {
                        path: 'staffs/list',
                        component: StaffListPage,
                    },
                    {
                        path: 'beneficiaries/list',
                        component: BeneficiaryListPage,
                    },
                    {
                        path: 'users/list',
                        component: UserListPage,
                    },
                    {
                        path: 'groups/list',
                        component: GroupListPage,
                    },
                    {
                        path: 'configurations/list',
                        component: ConfigurationListPage,
                    },
                    {
                        path: 'departments/list',
                        component: DepartmentListPage,
                    },
                    {
                        path: 'cost-centers/list',
                        component: CostCenterListPage,
                    },
                    {
                        path: 'periods/list',
                        component: PeriodListPage,
                    },
                    {
                        path: 'account-codes/unprocessed',
                        component: AccountCodeListPage,
                    },
                    {
                        path: 'bank-codes/list',
                        component: BankCodeListPage,
                    },
                    {
                        path: 'position-codes/list',
                        component: PositionCodeListPage,
                    },


                ]

            }
        ]
    },

    {path: 'asset',
        children: [
            {
                path: 'asset-codes/list',
                component: AssetCodeListPage,
            },
            ]
    },

    {path: '**', component: NotFoundPage}
];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes);
