import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';

import {FEATURE_NAME, reducers} from './asset.state';
import {SharedModule} from '../../shared/shared.module';
import {AssetService} from '../../services/asset.service';
import {AccountCodeListPage} from "../common/account-codes/account-code-list.page";
import {CostCenterListPage} from "../common/cost-center/cost-center-list.page";
import {ConfigurationListPage} from "../common/configurations/configuration-list.page";
import {PeriodAutocompleteComponent} from "../common/periods/period-autocomplete.component";
import {PeriodListPage} from "../common/periods/period-list.page";
import {SystemService} from "../../services";
import {DepartmentAutocompleteComponent} from "../common/departments/department-autocomplete.component";
import {DepartmentListPage} from "../common/departments/department-list.page";
import {PositionCodeListPage} from "../common/position-codes/position-code-list.page";
import {PositionCodeAutocompleteComponent} from "../common/position-codes/position-code-autocomplete.component";
import {AssetCodeEffects} from "./asset-codes/asset-code.effect";
import {AccountCodeAutocompleteComponent} from "../common/account-codes/account-code-autocomplete.component";
import {AssetCodeListPage} from "./asset-codes/asset-code-list.page";
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

