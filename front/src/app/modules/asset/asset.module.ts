import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';

import {FEATURE_NAME, reducers} from './asset.state';
import {SharedModule} from '../../shared/shared.module';
import {AssetService} from '../../services/asset.service';
import {SystemService} from "../../services";
import {AssetCodeEffects} from "./asset-codes/asset-code.effect";import {AssetCodeListPage} from "./asset-codes/asset-code-list.page";
import {LocationListPage} from "./locations/location-list.page";
import {LocationEffects} from "./locations/location-effect";


@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            AssetCodeEffects,
            LocationEffects,

        ])
    ],
    declarations: [

        AssetCodeListPage,
        LocationListPage,

    ],
    exports: [

    ],
    providers: [
        AssetService,
        SystemService,
    ]
})
export class AssetModule {
    constructor() {
    }
}

