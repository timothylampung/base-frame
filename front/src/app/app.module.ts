import {ErrorHandler, NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutes} from './app.routes';

import {AppComponent} from './app.component';
import {AppMenuComponent, AppSubMenuComponent} from './app.menu.component';
import {AppTopbarComponent} from './app.topbar.component';
import {AppFooterComponent} from './app.footer.component';
import {AppBreadcrumbComponent} from './app.breadcrumb.component';
import {AppRightpanelComponent} from './app.rightpanel.component';
import {AppInlineProfileComponent} from './app.profile.component';
import {BreadcrumbService} from './breadcrumb.service';
import {LoginPage} from './login/login.page';
import {AppShellComponent} from './app-shell/app-shell.component';
import {SharedModule} from './shared/shared.module';
import {CoreModule} from './core/core.module';
import {JwtHttpInterceptor} from './interceptors/jwt-http.interceptor';
import {AuthErrorHandler} from './handlers/auth-error.handler';
import {LoaderService} from './services/loader/loader.service';
import {LoaderInterceptor} from './interceptors/loader.interceptor';
import {LoaderComponent} from './services/loader/loader.component';
import {NotFoundPage} from './static/pages/not-found.page';
import {IdentityService} from './services/identity.service';
import {IdentityModule} from './modules/identity/identity.module';
import {CommonModule} from './modules/common/common.module';
import {AutofocusDirective} from "./directives/autofocus.directive";
import {AssetModule} from "./modules/asset/asset.module";
import {MaintenanceRequestModule} from "./modules/maintenance/maintenance-request.module";
import {InventoryModule} from "./modules/inventory/inventory.module";
import {WorkOrderModule} from "./modules/work-order/work-order.module";




@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutes,
        HttpClientModule,
        BrowserAnimationsModule,
        CoreModule,
        SharedModule,
        CommonModule,
        IdentityModule,
        AssetModule,
        MaintenanceRequestModule,
        InventoryModule,
        WorkOrderModule,
        MaintenanceRequestModule,
    ],
    declarations: [
        AppComponent,
        AppMenuComponent,
        AppSubMenuComponent,
        AppTopbarComponent,
        AppFooterComponent,
        AppBreadcrumbComponent,
        AppRightpanelComponent,
        AppInlineProfileComponent,
        NotFoundPage,
        LoginPage,
        AppShellComponent,
        LoaderComponent,
        AutofocusDirective
    ],
    providers: [
        BreadcrumbService,
        LoaderService,
        IdentityService,
        {provide: HTTP_INTERCEPTORS, useClass: JwtHttpInterceptor, multi: true},
        {provide: HTTP_INTERCEPTORS, useClass: LoaderInterceptor, multi: true},
        {provide: ErrorHandler, useClass: AuthErrorHandler}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
