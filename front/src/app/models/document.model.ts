import {FlowObject} from "./flow-object.model";

export interface Document extends FlowObject {
  referenceNo: string;
  sourceNo: string;
  description: string;

}
