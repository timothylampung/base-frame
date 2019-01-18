import {Permission} from "./permission.model";
import {Document} from "./document.model";

export interface Record extends Document {
  recordId: number; // todo:long
  permission?: Permission;
}
