import {ErrorHandler, NgModule} from '@angular/core';
import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutes } from './app.routes';
import { BreadcrumbService } from './component/breadcrumb.service';
import {AppComponent} from "./app.component";
import {AppMenuComponent, AppSubMenuComponent} from "./component/app.menu.component";
import {AppBreadcrumbComponent} from "./component/app.breadcrumb.component";
import {AppFooterComponent} from "./component/app.footer.component";
import {NotFoundPage} from "./static/pages/not-found.page";
import {AppShellComponent} from "./app-shell/app-shell.component";
import {LoginPage} from "./login/login.page";
import {LoaderComponent} from "./services/loader/loader.component";
import {AutofocusDirective} from "./directives/autofocus.directive";
import {LoaderService} from "./services/loader/loader.service";
import {IdentityService} from "./services/identity.service";
import {JwtHttpInterceptor} from "./interceptors/jwt-http.interceptor";
import {LoaderInterceptor} from "./interceptors/loader.interceptor";
import {CoreModule} from "./core/core.module";
import {SharedModule} from "./shared/shared.module";
import {IdentityModule} from "./modules/identity/identity.module";
import {AppTopBarComponent} from "./component/app.topbar.component";
import {AppRightPanelComponent} from "./component/app.rightpanel.component";
import {AppProfileComponent} from "./component/app.profile.component";
import {AuthErrorHandler} from "./handlers/auth-error.handler";
import {CommonModule} from "./modules/common/common.module";
import {AppMegamenuComponent} from "./component/app.megamenu.component";


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
    ],
    declarations: [
        AppComponent,
        AppMenuComponent,
        AppMegamenuComponent,
        AppSubMenuComponent,
        AppTopBarComponent,
        AppFooterComponent,
        AppBreadcrumbComponent,
        AppRightPanelComponent,
        NotFoundPage,
        LoginPage,
        AppShellComponent,
        AppProfileComponent,
        LoaderComponent,
        AutofocusDirective,
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
export class AppModule { }
