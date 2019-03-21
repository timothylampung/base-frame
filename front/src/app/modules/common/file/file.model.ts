import {MetaObject} from "../../../models";

export interface File extends MetaObject{
    fileName: string;
    fileType: string;
    fileLocation: string;
}
