import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {AuthGuard} from "./core/auth/auth.guard";
import {AppShellComponent} from "./app-shell/app-shell.component";
import {UserListPage} from "./modules/identity/principals/user/user-list.page";
import {GroupListPage} from "./modules/identity/principals/groups/group-list.page";
import {LoginPage} from "./login/login.page";

export const routes: Routes = [
    {path: 'login', component: LoginPage},
    {
        path: '',
        component: AppShellComponent,
        // canActivate: [AuthGuard],
        children: [
            {
                path: 'administration',
                children: [
                    {
                        path: 'users/list',
                        component: UserListPage,
                    },
                    {
                        path: 'groups/list',
                        component: GroupListPage,
                    }
                ]
            },
        ]
    }

];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes, {useHash: true});
