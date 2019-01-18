import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';
import {SharedModule} from "../../shared/shared.module";
import {FEATURE_NAME, reducers} from "./identity.state";
import {UserEffect} from "./principals/user/user.effect";
import {UserListPage} from "./principals/user/user-list.page";
import {IdentityService} from "../../services/identity.service";
import {GroupListPage} from './principals/groups/group-list.page';
import {GroupEffect} from './principals/groups/group.effect';
import {StaffListPage} from "./staffs/staff-list.page";
import {StaffEffects} from "./staffs/staff.effect";
import {BeneficiaryListPage} from "./beneficiary/beneficiary-list.page";
import {BeneficiaryEffects} from "./beneficiary/beneficiary.effect";



@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            UserEffect,
            GroupEffect,
            StaffEffects,
            BeneficiaryEffects,
        ])
    ],
    declarations: [
        UserListPage,
        GroupListPage,
        StaffListPage,
        GroupListPage,
        BeneficiaryListPage,
    ],
    exports: [

    ],
    providers: [IdentityService]
})
export class IdentityModule {
    constructor() {
    }
}

