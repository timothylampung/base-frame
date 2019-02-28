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
import {WorkOrderRegisterPage} from './pages/work-order-register.page';
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

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
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
        WorkOrderRegisterPage,
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
        WorkOrderRegisterPage,
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
