import {createSelector} from '@ngrx/store';
import {selectIdentityState} from "../identity.state";

export const selectActorResultState = createSelector(selectIdentityState, state => state.actorResult);
export const selectActors = createSelector(selectActorResultState, state => state.data);
export const selectActorTotalSize = createSelector(selectActorResultState, state => state.totalSize);
export const selectAllActors = createSelector(selectIdentityState, state => state.actors);
