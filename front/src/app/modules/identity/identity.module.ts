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
import {CommonModule} from "../common/common.module";
import {UserCreatorDialog} from "./principals/user/dialog/user-creator.dialog";
import {MessageService} from "primeng/api";
import {SystemService} from "../../services";



@NgModule({
    imports: [
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            UserEffect,
            GroupEffect,
        ]),
        CommonModule,
    ],
    declarations: [
        UserListPage,
        UserCreatorDialog,
        GroupListPage,
    ],
    entryComponents:[
    ],
    exports: [

    ],
    providers: [
        IdentityService,
        MessageService,
        SystemService,
    ]
})
export class IdentityModule {
    constructor() {
    }
}

