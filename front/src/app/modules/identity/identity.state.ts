import {ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {userResultReducer, usersReducer} from "./principals/user/user.reducer";
import {AppState} from "../../core/core.state";
import {UserResult} from "./principals/user/user-result.model";
import {User} from "./principals/user/user.model";
import {groupResultReducer} from './principals/groups/group.reducer';
import {GroupResult} from './principals/group-result.model';
import {
    staffResultReducer,
    staffsReducer,
    staffUploadedStatusReducer
} from "./staffs/staff.reducer";
import {Staff, StaffResult, StaffUploadStatus} from "./staffs/staff.model";
import {Technician, TechnicianResult} from "./technicians/technician.model";
import {technicianResultReducer, techniciansReducer} from "./technicians/technician.reducer";
import {FacilityManager, FacilityManagerResult} from "./facilitymanager/facility-manager.model";
import {
    facilityManagerResultReducer,
    facilityManagersReducer
} from "./facilitymanager/facility-manager.reducer";
import {supervisorResultReducer, supervisorsReducer} from "./supervisors/supervisor.reducer";
import {Supervisor, SupervisorResult} from "./supervisors/supervisor.model";
import {actorResultReducer, actorsReducer} from "./actors/actor.reducer";
import {Actor} from "./actors/actor.model";
import {ActorResult} from "./actors/actor-result.model";


export const FEATURE_NAME = 'identity';
export const selectIdentityState = createFeatureSelector<State, IdentityState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<IdentityState> = {
    userResult: userResultReducer,
    users: usersReducer,
    actorResult: actorResultReducer,
    actors: actorsReducer,
    groupResult: groupResultReducer,
    staffResult: staffResultReducer,
    staffs: staffsReducer,
    staffUploadStatus: staffUploadedStatusReducer,
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
    actorResult: ActorResult;
    actors: Actor[];
    groupResult: GroupResult;
    staffResult: StaffResult;
    staffs: Staff[];
    staffUploadStatus: StaffUploadStatus;
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
