import {
    FIND_PAGED_CONFIGURATIONS_SUCCESS,
    FindPagedConfigurationsSuccessAction
} from './configuration.action';
import {ConfigurationResult} from './configuration.model';

const initialState: ConfigurationResult = {
    data: [], totalSize: 0
};

export function configurationResultReducer(state = initialState, action: FindPagedConfigurationsSuccessAction): ConfigurationResult {
    switch (action.type) {
        case FIND_PAGED_CONFIGURATIONS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}
