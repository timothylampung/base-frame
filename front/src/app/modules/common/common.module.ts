import {AccountCodeAutocompleteComponent} from './account-codes/account-code-autocomplete.component';
import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';

import {AccountCodeEffects} from './account-codes/account-code.effect';
import {FEATURE_NAME, reducers} from './common.state';
import {SharedModule} from '../../shared/shared.module';
import {CommonService} from '../../services/common.service';
import {DepartmentAutocompleteComponent} from './departments/department-autocomplete.component';
import {PeriodAutocompleteComponent} from './periods/period-autocomplete.component';
import {DepartmentEffects} from './departments/department.effect';
import {PeriodEffects} from './periods/period.effect';
import {PositionCodeEffects} from './position-codes/position-code.effect';
import {DepartmentListPage} from './departments/department-list.page';
import {PeriodListPage} from './periods/period-list.page';
import {AccountCodeListPage} from './account-codes/account-code-list.page';
import {ConfigurationListPage} from './configurations/configuration-list.page';
import {ConfigurationEffects} from './configurations/configuration.effect';
import {SystemService} from '../../services';
import {CostCenterEffects} from "./cost-center/cost-center.effect";
import {CostCenterListPage} from "./cost-center/cost-center-list.page";
import {BankCodeEffects} from "./bank-codes/bank-code.effect";
import {BankCodeListPage} from "./bank-codes/bank-code-list.page";
import {PositionCodeListPage} from "./position-codes/position-code-list.page";
import {PositionCodeAutocompleteComponent} from "./position-codes/position-code-autocomplete.component";


@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            AccountCodeEffects,
            BankCodeEffects,
            DepartmentEffects,
            PeriodEffects,
            PositionCodeEffects,
            ConfigurationEffects,
            CostCenterEffects,
        ])
    ],
    declarations: [
        DepartmentListPage,
        CostCenterListPage,
        PeriodListPage,
        AccountCodeListPage,
        BankCodeListPage,
        ConfigurationListPage,
        PositionCodeListPage,
        DepartmentAutocompleteComponent,
        PeriodAutocompleteComponent,
        AccountCodeAutocompleteComponent,
        PositionCodeAutocompleteComponent
    ],
    exports: [
        DepartmentAutocompleteComponent,
        PeriodAutocompleteComponent,
        AccountCodeAutocompleteComponent,
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

