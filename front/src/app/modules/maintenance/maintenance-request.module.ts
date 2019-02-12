import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';
import {CommonModule} from '../common/common.module';
import {SharedModule} from '../../shared/shared.module';
import {MaintenanceRequestEffects} from './maintenance-request.effect';
import {FEATURE_NAME, reducers} from './maintenance-request.state';
import {MaintenanceRequestAssignedTaskListPage} from './maintenance-request-assigned-task-list.page';
import {MaintenanceRequestPooledTaskListPage} from './maintenance-request-pooled-task-list.page';
import {MaintenanceRequestTaskDetailPage} from './maintenance-request-task-detail.page';
import {MaintenanceRequestWorkflowPage} from './maintenance-request-workflow.page';
import {MaintenanceRequestDraftPage} from './pages/maintenance-request-draft.page';
import {MaintenanceRequestTaskNewPage} from './maintenance-request-task-new.page';
import {MaintenanceRequestNewPage} from './pages/maintenance-request-new.page';
import {MaintenanceRequestCheckPage} from './pages/maintenance-request-check.page';
import {MaintenanceRequestArchiveListPage} from './maintenance-request-archive-list.page';
import {MaintenanceRequestArchiveDetailPage} from './maintenance-request-archive-detail.page';
import {MaintenanceRequestPage} from './pages/maintenance-request.page';
import {MaintenanceRequestService} from "../../services/maintenance-request.service";
import {MessageService} from "primeng/api";

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            MaintenanceRequestEffects
        ])
    ],
    exports: [],
    declarations: [
        MaintenanceRequestPage,
        MaintenanceRequestNewPage,
        MaintenanceRequestDraftPage,
        MaintenanceRequestCheckPage,
        MaintenanceRequestAssignedTaskListPage,
        MaintenanceRequestPooledTaskListPage,
        MaintenanceRequestTaskNewPage,
        MaintenanceRequestTaskDetailPage,
        MaintenanceRequestWorkflowPage,
        MaintenanceRequestArchiveListPage,
        MaintenanceRequestArchiveDetailPage
    ],
    entryComponents: [
        MaintenanceRequestPage,
        MaintenanceRequestNewPage,
        MaintenanceRequestDraftPage,
        MaintenanceRequestCheckPage,
    ],
    providers: [
        MaintenanceRequestService,
        MessageService,
    ]
})
export class MaintenanceRequestModule {
}
