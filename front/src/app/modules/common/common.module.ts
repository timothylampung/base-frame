import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';

import {FEATURE_NAME, reducers} from './common.state';
import {SharedModule} from '../../shared/shared.module';
import {CommonService} from '../../services/common.service';
import {ConfigurationListPage} from './configurations/configuration-list.page';
import {ConfigurationEffects} from './configurations/configuration.effect';
import {SystemService} from '../../services';


@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            ConfigurationEffects,
        ])
    ],
    declarations: [
        ConfigurationListPage,

    ],
    exports: [
        ConfigurationListPage,
    ],
    providers: [
        CommonService,
        SystemService,
    ]
})
export class CommonModule {
    constructor() {
    }
}

