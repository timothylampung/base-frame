import {
    Component,
} from '@angular/core';
import {Store} from "@ngrx/store";
import {FlowState} from "../../models";
import {ReportState} from "./report.state";
import {DownloadReportAction} from "./report.action";

@Component({
    selector: 'dex-report-list-page',
    templateUrl: './report-list.page.html'
})
export class ReportListPage {

    constructor(private store: Store<ReportState>) {
    }

    public downloadReport(reportFile: string): void {
        this.store.dispatch(new DownloadReportAction({reportName: reportFile, params: []}))
    }
}
