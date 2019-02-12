import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';
import {FEATURE_NAME, reducers} from './work-order.state';
import {SharedModule} from '../../shared/shared.module';
import {CommonService} from '../../services/common.service';
import {WorkOrderEffects} from "./orders/work-order.effect";
import {WorkOrderListPage} from "./orders/work-order-list.page";
import {SystemService} from "../../services";
import {WorkOrderService} from "../../services/work-order.service";


@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
          WorkOrderEffects
        ])
    ],
    declarations: [
       WorkOrderListPage
    ],
    exports: [

    ],
    providers: [
        SystemService,
        WorkOrderService
    ]
})
export class WorkOrderModule {
    constructor() {
    }
}

