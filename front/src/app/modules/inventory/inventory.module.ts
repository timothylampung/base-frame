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
import {ComponentDetailPage} from "./components/component-detail/component-detail.page";
import {LocationDetailPage} from "../asset/locations/location-detail/location-detail.page";
import {PartCodeDetailPage} from "./part-codes/part-code-detail/part-code-detail.page";
import {PartDetailPage} from "./parts/part-detail/part-detail.page";
import {NgxQRCodeModule} from "ngx-qrcode2";


@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            PartEffects,
            ComponentEffects,
            PartCodeEffects,

        ]),
        NgxQRCodeModule

    ],
    declarations: [


        PartListPage,
        ComponentListPage,
        PartCodeListPage,
        ComponentDetailPage,
        PartCodeDetailPage,
        PartDetailPage,

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

