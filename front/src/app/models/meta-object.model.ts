import {MetaState} from './meta-state.enum';

export interface MetaObject {
  id: number;
  metaState?: MetaState;
  createdDate?: Date;
  modifiedDate?: Date;
  creatorUsername?: string;
  modifierUsername?: string;
}
