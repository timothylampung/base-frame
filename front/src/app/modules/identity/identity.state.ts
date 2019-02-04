import {ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {userResultReducer, usersReducer} from "./principals/user/user.reducer";
import {AppState} from "../../core/core.state";
import {UserResult} from "./principals/user/user-result.model";
import {User} from "./principals/user/user.model";
import {groupResultReducer} from './principals/groups/group.reducer';
import {GroupResult} from './principals/group-result.model';
import {staffResultReducer, staffsReducer} from "./staffs/staff.reducer";
import {Staff, StaffResult} from "./staffs/staff.model";
import {Technician, TechnicianResult} from "./technicians/technician.model";
import {technicianResultReducer, techniciansReducer} from "./technicians/technician.reducer";
import {FacilityManager, FacilityManagerResult} from "./facilitymanager/facility-manager.model";
import {facilityManagerResultReducer, facilityManagersReducer} from "./facilitymanager/facility-manager.reducer";
import {supervisorResultReducer, supervisorsReducer} from "./supervisors/supervisor.reducer";
import {Supervisor, SupervisorResult} from "./supervisors/supervisor.model";


export const FEATURE_NAME = 'identity';
export const selectIdentityState = createFeatureSelector<State, IdentityState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<IdentityState> = {
    userResult: userResultReducer,
    users: usersReducer,
    groupResult: groupResultReducer,
    staffResult: staffResultReducer,
    staffs: staffsReducer,
    technicianResult: technicianResultReducer,
    technicians: techniciansReducer,
    facilityManagerResult: facilityManagerResultReducer,
    facilityManagers: facilityManagersReducer,
    supervisorResult: supervisorResultReducer,
    supervisors: supervisorsReducer,
};

export interface IdentityState {
    userResult: UserResult;
    users: User[];
    groupResult: GroupResult;
    staffResult: StaffResult;
    staffs: Staff[];
    supervisorResult: SupervisorResult;
    supervisors: Supervisor[];
    technicianResult: TechnicianResult,
    technicians: Technician[],   
    facilityManagerResult: FacilityManagerResult,
    facilityManagers: FacilityManager[],
}

export interface State extends AppState {
    identity: IdentityState;
}
