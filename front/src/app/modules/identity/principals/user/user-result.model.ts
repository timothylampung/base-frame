import {User} from './user.model';

export interface UserResult {
    totalSize: number;
    data: User[];
}
