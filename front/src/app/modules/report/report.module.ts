import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {EffectsModule} from '@ngrx/effects';
import {StoreModule} from '@ngrx/store';
import {ReportEffects} from './report.effect';
import {ReportListPage} from "./report-list.page";
import {SharedModule} from "../../shared/shared.module";
import {ReportService} from "../../services/report.service";
import {FEATURE_NAME, reducers} from "./report.state";


@NgModule({
    imports: [
        RouterModule,
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            ReportEffects,
        ]),
    ],
    declarations: [
        ReportListPage,
    ],
    exports: [
        ReportListPage,
    ],
    entryComponents: [],
    providers: [
        ReportService
    ]
})
export class ReportModule {
}
