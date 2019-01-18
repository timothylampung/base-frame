import {NgModule, Optional, SkipSelf} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {CommonModule} from '@angular/common';
import {HttpModule} from '@angular/http';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {JwtModule} from '@auth0/angular-jwt';
import {AuthEffects} from './auth/auth.effect';
import {TitleService} from './title/title.service';
import {AuthenticationService} from '../services/authentication.service';
import {AuthorizationService} from '../services/authorization.service';
import {reducers} from './core.state';
import {TOKEN_NAME} from './auth/auth.constant';
import {AuthGuard} from './auth/auth.guard';
import {IdentityService} from "../services/identity.service";

@NgModule({
    imports: [
        // angular
        CommonModule,
        HttpModule,
        HttpClientModule,

        // ngrx
        StoreModule.forRoot(reducers),
        EffectsModule.forRoot([AuthEffects]),
        StoreDevtoolsModule.instrument({maxAge: 10}),

        // 3rd party
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient]
            }
        }),

        JwtModule.forRoot({
            config: {
                tokenGetter: tokenGetter,
                whitelistedDomains: ['localhost:8080', '209.97.162.230:8080'],
                throwNoTokenError: false
            }
        }),

    ],
    declarations: [],
    providers: [
        AuthGuard,
        IdentityService,
        AuthenticationService,
        AuthorizationService,
        TitleService
    ],
    exports: [
        TranslateModule
    ]
})
export class CoreModule {
    constructor(@Optional()
                @SkipSelf()
                    parentModule: CoreModule) {
        if (parentModule) {
            throw new Error('CoreModule is already loaded. Import only in AppModule');
        }
    }
}

export function tokenGetter() {
    return localStorage.getItem(TOKEN_NAME);
}

export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http, './assets/i18n/');
}
