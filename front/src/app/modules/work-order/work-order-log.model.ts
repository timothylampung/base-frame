import {User} from "../identity/principals/user/user.model";

export interface WorkOrderLog {
    startTime: Date;
    stopTime: Date;
    logger: User;
}
