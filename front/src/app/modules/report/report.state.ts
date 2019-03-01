import {ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {AppState} from "../../core/core.state";

export const FEATURE_NAME = 'report';
export const selectReportState = createFeatureSelector<State, ReportState>(FEATURE_NAME);

export const reducers: ActionReducerMap<ReportState> = {};

export interface ReportState {
}

export interface State extends AppState {
    report: ReportState;
}
