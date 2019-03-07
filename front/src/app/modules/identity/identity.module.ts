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
import {SupervisorListPage} from "./supervisors/supervisor-list.page";
import {TechnicianListPage} from "./technicians/technician-list.page";
import {TechnicianEffects} from "./technicians/technician.effect";
import {FacilityManagerListPage} from "./facilitymanager/facility-manager-list.page";
import {FacilityManagerEffects} from "./facilitymanager/facility-manager.effect";
import {SupervisorEffects} from "./supervisors/supervisor.effect";
import {ActorAutocompleteComponent} from "./actors/actor-autocomplete.component";



@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            UserEffect,
            GroupEffect,
            StaffEffects,
            TechnicianEffects,
            FacilityManagerEffects,
            SupervisorEffects,
        ])
    ],
    declarations: [
        ActorAutocompleteComponent,
        UserListPage,
        GroupListPage,
        StaffListPage,
        GroupListPage,
        SupervisorListPage,
        TechnicianListPage,
        FacilityManagerListPage
    ],
    exports: [
        ActorAutocompleteComponent,
    ],
    providers: [IdentityService]
})
export class IdentityModule {
    constructor() {
    }
}

