import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';
import {CommonModule} from '../common/common.module';
import {SharedModule} from '../../shared/shared.module';
import {WorkOrderEffects} from './work-order.effect';
import {FEATURE_NAME, reducers} from './work-order.state';
import {WorkOrderService} from '../../services/work-order.service';
import {WorkOrderAssignedTaskListPage} from './work-order-assigned-task-list.page';
import {WorkOrderPooledTaskListPage} from './work-order-pooled-task-list.page';
import {WorkOrderTaskDetailPage} from './work-order-task-detail.page';
import {WorkOrderWorkflowPage} from './work-order-workflow.page';
import {WorkOrderVerifyPage} from './pages/work-order-verify.page';
import {WorkOrderPreparePage} from './pages/work-order-prepare.page';
import {WorkOrderTaskNewPage} from './work-order-task-new.page';
import {WorkOrderNewPage} from './pages/work-order-new.page';
import {WorkOrderCheckPage} from './pages/work-order-check.page';
import {WorkOrderArchiveListPage} from './work-order-archive-list.page';
import {WorkOrderArchiveDetailPage} from './work-order-archive-detail.page';
import {WorkOrderPage} from './pages/work-order.page';
import {MessageService} from "primeng/api";
import {WorkOrderDetailPage} from "./pages/work-order-detail.page";
import {WorkOrderCommentEditorDialog} from "./work-order-comment-editor.dialog";
import {AssetModule} from "../asset/asset.module";
import {IdentityModule} from "../identity/identity.module";

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        AssetModule,
        IdentityModule,
        StoreModule.forFeature(FEATURE_NAME, reducers),
        EffectsModule.forFeature([
            WorkOrderEffects
        ])
    ],
    exports: [],
    declarations: [
        WorkOrderPage,
        WorkOrderNewPage,
        WorkOrderDetailPage,
        WorkOrderPreparePage,
        WorkOrderVerifyPage,
        WorkOrderCheckPage,
        WorkOrderAssignedTaskListPage,
        WorkOrderPooledTaskListPage,
        WorkOrderTaskNewPage,
        WorkOrderTaskDetailPage,
        WorkOrderWorkflowPage,
        WorkOrderArchiveListPage,
        WorkOrderArchiveDetailPage,
        WorkOrderCommentEditorDialog,
    ],
    entryComponents: [
        WorkOrderPage,
        WorkOrderNewPage,
        WorkOrderPreparePage,
        WorkOrderVerifyPage,
        WorkOrderCheckPage,
        WorkOrderCommentEditorDialog,
    ],
    providers: [
        WorkOrderService,
        MessageService,
    ]
})
export class WorkOrderModule {
}
