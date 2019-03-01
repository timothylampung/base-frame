import {catchError, map} from "rxjs/operators";
import {Injectable} from "@angular/core";
import {Observable, throwError} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class ReportService {

    private REPORT_API: string = environment.endpoint + '/servlet/report?report=';

    constructor(private http: HttpClient) {
    }

    // ===================================================================================================================
    // REPORT
    // ===================================================================================================================

    downloadReport(reportName: string, params: { [key: string]: any }): Observable<Blob> {
        console.log('reportName: ' + reportName + '  params: ', params);
        let params_ = this.buildParams(reportName, params);
        return this.http.get(this.REPORT_API + params_,
            {
                responseType: 'blob' as 'json'
            })
            .pipe(
                map((res) => {
                    return new Blob([res], {type: 'application/pdf'});
                }),
                catchError(err => throwError(err))
            );
    }

    private buildParams(reportName: string, params): string {
        return reportName + '&' + Object.entries(params).map(p => p.join('=')).join('&');
    }
}
