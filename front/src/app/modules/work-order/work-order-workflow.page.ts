import {
    Component,
    ComponentFactoryResolver,
    ComponentRef,
    Input,
    SimpleChange,
    ViewChild,
    ViewContainerRef
} from '@angular/core';
import {WorkOrderTaskSummary} from './work-order.model';
import {WorkOrderPreparePage} from './pages/work-order-prepare.page';
import {WorkOrderVerifyPage} from './pages/work-order-verify.page';
import {WorkOrderNewPage} from './pages/work-order-new.page';
import {WorkOrderCheckPage} from "./pages/work-order-check.page";
import {Store} from "@ngrx/store";
import {WorkOrderState} from "./work-order.state";
import {SelectWorkOrderAction} from "./work-order.action";
import {FlowState} from "../../models";

@Component({
    selector: 'dex-work-order-workflow-page',
    templateUrl: './work-order-workflow.page.html'
})
export class WorkOrderWorkflowPage {

    @ViewChild('taskPanel', {read: ViewContainerRef})
    taskPanel: ViewContainerRef;
    componentRef: ComponentRef<any>;
    @Input() workOrderTask: WorkOrderTaskSummary;

    constructor(public cfr: ComponentFactoryResolver,
                public store: Store<WorkOrderState>) {
    }

    ngOnChanges(changes: { [ propName: string]: SimpleChange }) {
        let componentFactory;
        if (changes['workOrderTask']) {
            if (this.workOrderTask && this.workOrderTask.flowState !== null) {
                console.log('task flowState: ' + this.workOrderTask.flowState);

                // resetN
                if (this.componentRef) {
                    this.componentRef.destroy();
                }

                if (this.workOrderTask.flowState === FlowState.PREPARED) {
                    componentFactory = this.cfr.resolveComponentFactory(WorkOrderPreparePage);
                } else if (this.workOrderTask.flowState === FlowState.CHECKED) {
                    componentFactory = this.cfr.resolveComponentFactory(WorkOrderCheckPage);
                } else if (this.workOrderTask.flowState === FlowState.VERIFIED) {
                    componentFactory = this.cfr.resolveComponentFactory(WorkOrderVerifyPage);
                } else {
                    componentFactory = this.cfr.resolveComponentFactory(WorkOrderNewPage);
                }

                this.componentRef = this.taskPanel.createComponent(componentFactory);
                this.componentRef.instance.workOrderTask = this.workOrderTask;

                this.store.dispatch(new SelectWorkOrderAction(this.workOrderTask.workOrder))
            }
        }
    }

    ngOnDestroy() {
        if (this.componentRef) {
            this.componentRef.destroy();
        }
    }
}
