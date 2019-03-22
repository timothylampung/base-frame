import {MetaObject} from './meta-object.model';

export interface FlowData extends MetaObject {
    drafter?: string;
    draftedDate?: Date;
    drafterPosition?: string;
    registerer?: string;
    registeredDate?: Date;
    registererPosition?: string;
    checker?: string;
    checkedDate?: Date;
    checkerPosition?: string;
    approver?: string;
    approvedDate?: Date;
    approverPosition?: string;
    maintainedDate?: Date;
    maintainedPosition?: string;
}
