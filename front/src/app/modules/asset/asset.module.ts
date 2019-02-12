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
import {AssetListPage} from "./assets/asset-list.page";
import {AssetEffects} from "./assets/asset-effect";
import {LocationDetailPage} from "./locations/location-detail/location-detail.page";


@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            AssetEffects,
            AssetCodeEffects,
            LocationEffects,

        ])
    ],
    declarations: [


        AssetListPage,
        AssetCodeListPage,
        LocationListPage,
        LocationDetailPage,

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

