import {CHANGE_TITLE_SUCCESS, ChangeTitleSuccessAction} from './title.action';

export type TitleState = string;

const initialState: TitleState = 'N/A';

export function titleReducer(state = initialState, action: ChangeTitleSuccessAction): TitleState {
  switch (action.type) {
    case CHANGE_TITLE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
