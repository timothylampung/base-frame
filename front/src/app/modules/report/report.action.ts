import {Action} from "@ngrx/store";

export const DOWNLOAD_REPORT = '[Report] Download Report';
export const DOWNLOAD_REPORT_SUCCESS = '[Report] Download Report Success';
export const DOWNLOAD_REPORT_ERROR = '[Report] Download Report Error';

export class DownloadReportAction implements Action {
    readonly type: string = DOWNLOAD_REPORT;

    constructor(public payload: { reportName: string, params: { [key: string]: any } }) {
        console.log('DownloadReportAction', payload);
    }
}

export class DownloadReportSuccessAction implements Action {
    readonly type: string = DOWNLOAD_REPORT_SUCCESS;

    constructor(public payload: { message: string }) {
        console.log('DownloadReportSuccessAction');
    }
}

export class DownloadReportErrorAction implements Action {
    readonly type: string = DOWNLOAD_REPORT_ERROR;

    constructor(public message: string) {
    }
}
