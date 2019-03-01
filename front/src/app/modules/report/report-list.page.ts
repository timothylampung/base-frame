import {
    Component,
} from '@angular/core';
import {Store} from "@ngrx/store";
import {FlowState} from "../../models";

@Component({
    selector: 'dex-report-list-page',
    templateUrl: './report-list.page.html'
})
export class ReportListPage {

    public downloadReport(): void {
    }
}
