import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';
import {SharedModule} from "../../shared/shared.module";
import {FEATURE_NAME, reducers} from "./maintenance-request.state";
import {MaintenanceRequestEffects} from "./maintenance-request/maintenance-request.effect";
import {MaintenanceRequestListPage} from "./maintenance-request/maintenance-request-list.page";
import {MaintenanceRequestService} from "../../services/maintenance-request.service";
import {MaintenanceRequestDetailPage} from "./maintenance-request/maintenance-request-detail/maintenance-request-detail.page";


@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            MaintenanceRequestEffects,
        ])
    ],
    declarations: [
        MaintenanceRequestListPage,
        MaintenanceRequestDetailPage
    ],
    exports: [],
    providers: [MaintenanceRequestService]
})
export class MaintenanceRequestModule {
    constructor() {
    }
}

