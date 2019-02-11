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
import {SupervisorListPage} from "./modules/identity/supervisors/supervisor-list.page";
import {PositionCodeListPage} from "./modules/common/position-codes/position-code-list.page";
import {AssetCodeListPage} from "./modules/asset/asset-codes/asset-code-list.page";
import {TechnicianListPage} from "./modules/identity/technicians/technician-list.page";
import {LocationListPage} from "./modules/asset/locations/location-list.page";
import {AssetListPage} from "./modules/asset/assets/asset-list.page";
import {FacilityManagerListPage} from "./modules/identity/facilitymanager/facility-manager-list.page";
import {MaintenanceRequestListPage} from "./modules/maintenance/maintenance-request/maintenance-request-list.page";
import {PartListPage} from "./modules/inventory/parts/part-list.page";
import {ComponentListPage} from "./modules/inventory/components/component-list.page";
import {PartCodeListPage} from "./modules/inventory/part-codes/part-code-list.page";

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
                    {
                        path: 'locations/list',
                        component: LocationListPage,
                    },

                    {
                        path: 'assets/list',
                        component: AssetListPage,
                    },
                ]
            },

            {path: 'inventory',
                children: [
                    {
                        path: 'parts/list',
                        component: PartListPage,

                    },
                    {
                        path: 'part-codes/list',
                        component: PartCodeListPage,

                    },
                    {
                        path: 'components/list',
                        component: ComponentListPage,

                    },


                ]
            },



            {
              path : 'maintenance-request',
                children : [
                    {
                        path : 'maintenance-request/list',
                        component : MaintenanceRequestListPage,
                        data: {
                            breadcrumb: [
                                {label: 'maintenance-request', routerLink: null},
                                {label: 'maintenance-request', routerLink: ['/maintenance-request/maintenance-request/list']}
                            ]
                        }

                    }
                ]
            },
            {
                path: 'administration',
                children: [
                    {
                        path: 'staffs/list',
                        component: StaffListPage,
                        data: {
                            breadcrumb: [
                                {label: 'administration', routerLink: null},
                                {label: 'staffs', routerLink: ['/administration/staffs/list']}
                            ]
                        }

                    },
                    {
                        path: 'technicians/list',
                        component: TechnicianListPage,
                        data: {
                            breadcrumb: [
                                {label: 'administration', routerLink: null},
                                {label: 'technicians', routerLink: ['/administration/technicians/list']}
                            ]
                        }
                    },
                    {
                        path: 'facility-managers/list',
                        component: FacilityManagerListPage,
                        data: {
                            breadcrumb: [
                                {label: 'administration', routerLink: null},
                                {label: 'facility-managers', routerLink: ['/administration/facility-managers/list']}
                            ]
                        }
                    },
                    {
                        path: 'supervisors/list',
                        component: SupervisorListPage,
                        data: {
                            breadcrumb: [
                                {label: 'administration', routerLink: null},
                                {label: 'supervisors', routerLink: ['/administration/supervisors/list']}
                            ]
                        }
                    },
                    {
                        path: 'users/list',
                        component: UserListPage,
                        data: {
                            breadcrumb: [
                                {label: 'administration', routerLink: null},
                                {label: 'users', routerLink: ['/administration/users/list']}
                            ]
                        }
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

    {
        path: 'asset',
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
