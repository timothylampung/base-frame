import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';

import {FEATURE_NAME, reducers} from './inventory.state';
import {SharedModule} from '../../shared/shared.module';
import {SystemService} from "../../services";
import {PartListPage} from "./parts/part-list.page";
import {PartEffects} from "./parts/part-effect";
import {InventoryService} from "../../services/inventory.service";


@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            PartEffects,

        ])
    ],
    declarations: [


        PartListPage,

    ],
    exports: [

    ],
    providers: [
        SystemService,
        InventoryService,
    ]
})
export class InventoryModule {
    constructor() {
    }
}

