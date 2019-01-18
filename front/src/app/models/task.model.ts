import {Document} from './document.model';

export interface Task extends Document {
    taskId: string;
    taskName: string;
    assignee: string;
}
