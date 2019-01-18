import {FlowState} from './flow-state.enum';
import {MetaObject} from './meta-object.model';

export interface FlowObject extends MetaObject {
  flowState: FlowState;
}
