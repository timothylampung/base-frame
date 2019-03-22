import {
    Component,
    ComponentFactoryResolver,
    ComponentRef,
    Input,
    SimpleChange,
    ViewChild,
    ViewContainerRef
} from '@angular/core';
import {MaintenanceRequestTaskSummary} from './maintenance-request.model';
import {MaintenanceRequestDraftPage} from './pages/maintenance-request-draft.page';
import {MaintenanceRequestNewPage} from './pages/maintenance-request-new.page';
import {MaintenanceRequestCheckPage} from "./pages/maintenance-request-check.page";
import {Store} from "@ngrx/store";
import {MaintenanceRequestState} from "./maintenance-request.state";
import {SelectMaintenanceRequestAction} from "./maintenance-request.action";
import {FlowState} from "../../models";

@Component({
    selector: 'dex-maintenance-request-workflow-page',
    templateUrl: './maintenance-request-workflow.page.html'
})
export class MaintenanceRequestWorkflowPage {

    @ViewChild('taskPanel', {read: ViewContainerRef})
    taskPanel: ViewContainerRef;
    componentRef: ComponentRef<any>;
    @Input() maintenanceRequestTask: MaintenanceRequestTaskSummary;

    constructor(public cfr: ComponentFactoryResolver,
                public store: Store<MaintenanceRequestState>) {
    }

    ngOnChanges(changes: { [propName: string]: SimpleChange }) {
        let componentFactory;
        if (changes['maintenanceRequestTask']) {
            if (this.maintenanceRequestTask && this.maintenanceRequestTask.flowState !== null) {
                console.log('task flowState: ' + this.maintenanceRequestTask.flowState);

                if (this.componentRef) {
                    this.componentRef.destroy();
                }

                // note: workaround
                if (this.maintenanceRequestTask.request) {
                    this.maintenanceRequestTask.request.requestedDate = new Date(this.maintenanceRequestTask.request.requestedDate);
                }

                if (this.maintenanceRequestTask.flowState === FlowState.DRAFTED) {
                    componentFactory = this.cfr.resolveComponentFactory(MaintenanceRequestDraftPage);
                } else if (this.maintenanceRequestTask.flowState === FlowState.CHECKED) {
                    componentFactory = this.cfr.resolveComponentFactory(MaintenanceRequestCheckPage);
                } else {
                    componentFactory = this.cfr.resolveComponentFactory(MaintenanceRequestNewPage);
                }

                this.componentRef = this.taskPanel.createComponent(componentFactory);
                this.componentRef.instance.maintenanceRequestTask = this.maintenanceRequestTask;

                this.store.dispatch(new SelectMaintenanceRequestAction(this.maintenanceRequestTask.request))
            }
        }
    }

    ngOnDestroy() {
        if (this.componentRef) {
            this.componentRef.destroy();
        }
    }
}
