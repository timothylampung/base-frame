import {map, switchMap, tap} from "rxjs/operators";
import {Injectable} from "@angular/core";
import {Actions, Effect} from "@ngrx/effects";
import {DOWNLOAD_REPORT, DownloadReportAction, DownloadReportErrorAction} from "./report.action";
import {catchError} from "rxjs/internal/operators";
import {ReportService} from "../../services/report.service";


@Injectable()
export class ReportEffects {
    constructor(private actions$: Actions,
                private reportService: ReportService) {
    }

    // ===================================================================================================================
    // REPORT
    // ===================================================================================================================

    @Effect() downloadReport = this.actions$
        .ofType<DownloadReportAction>(DOWNLOAD_REPORT).pipe(
            map((action: DownloadReportAction) => action.payload),
            switchMap(payload => this.reportService.downloadReport(payload.reportName, payload.params)
                .pipe(
                    tap((file) => {
                        const url = URL.createObjectURL(file);
                        let pwa = window.open(url, '_blank');
                        setTimeout(() => {
                            if (!pwa || pwa.closed) {
                                alert('Please disable your Pop-up blocker and try again.');
                            }
                        }, 500);
                    }),
                    catchError(err => [new DownloadReportErrorAction(err)])
                )
            ),
        );
}
