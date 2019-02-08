import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';

import {FEATURE_NAME, reducers} from './inventory.state';
import {SharedModule} from '../../shared/shared.module';
import {SystemService} from "../../services";
import {PartListPage} from "./parts/part-list.page";
import {PartEffects} from "./parts/part-effect";
import {InventoryService} from "../../services/inventory.service";
import {ComponentListPage} from "./components/component-list.page";
import {ComponentEffects} from "./components/component-effect";
import {PartCodeEffects} from "./part-codes/part-code-effect";
import {PartCodeListPage} from "./part-codes/part-code-list.page";


@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            PartEffects,
            ComponentEffects,
            PartCodeEffects,

        ])
    ],
    declarations: [


        PartListPage,
        ComponentListPage,
        PartCodeListPage

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

