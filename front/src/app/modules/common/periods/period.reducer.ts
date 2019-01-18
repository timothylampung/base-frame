import {initialStatePeriodResult, Period, PeriodResult} from './period.model';
import {
    FIND_ALL_PERIODS_SUCCESS,
    FIND_PAGED_PERIODS_SUCCESS,
    FindAllPeriodsSuccessAction,
    FindPagedPeriodsSuccessAction
} from './period.action';

export function periodResultReducer(state = initialStatePeriodResult, action: FindPagedPeriodsSuccessAction): PeriodResult {
    switch (action.type) {
        case FIND_PAGED_PERIODS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function periodsReducer(state = [], action: FindAllPeriodsSuccessAction): Period[] {
    switch (action.type) {
        case FIND_ALL_PERIODS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
