import {catchError, map} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from "../../environments/environment";

@Injectable()
export class ReportService {

  private REPORT_API: string = environment.endpoint + '/servlet/report?report=';

  constructor(private http: HttpClient) {
  }

  // ===================================================================================================================
  // REPORT
  // ===================================================================================================================

  downloadReport(params: string): Observable<Blob> {
    console.log('downloadReport');
    console.log('params: ' + params);

    return this.http.get(this.REPORT_API + params,
      {
        responseType: 'blob'
      })
      .pipe(
        map((res) => {
          return new Blob([res], {type: 'application/pdf'});
        }),
        catchError(err => throwError(err))
      );
  }

  // ===================================================================================================================
  // REPORT
  // ===================================================================================================================

  private handleError(error: any) {
    console.log(error);
    // const body = error.json();
    // return Observable.throw(body);
  }
}
