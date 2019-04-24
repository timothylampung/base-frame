import {Pipe, PipeTransform} from '@angular/core';
import {FlowState} from "../models/flow-state.enum";

@Pipe({name: 'flowStatePipe'})
export class FlowStatePipe implements PipeTransform {

    /*
    *     NEW,                    // 1
        DRAFTED,                // 1
        REQUESTED,              // 2
        REGISTERED,             // 3
        PREPARED,               // 4
        VERIFIED,               // 5
        UPPER_VERIFIED,         // 6
        CHECKED,                // 7
        APPROVED,               // 8
        UPPER_APPROVED,         // 9
        SELECTED,               // 10
        EVALUATED,              // 11
        CANCELLED,              // 12
        PUBLISHED,              // 13
        REJECTED,               // 14
        REMOVED,                // 15
        COMPLETED,              // 16
        OFFERED,                 // 17
        ARCHIVED,               // 18
        WITHDRAW,                 //19
        GRANTED,                   //20
        DECLINED; // 21

    */
    transform(value: FlowState): any {
        if (!value) {
            return value;
        }
        switch (FlowState[value.toString()]) {
            case FlowState.NEW : {
                return 'NEW';
            }
            case FlowState.DRAFTED : {
                return 'DRAFTED';
            }
            case FlowState.REGISTERED : {
                return 'REGISTERED';
            }
            case FlowState.REQUESTED : {
                return 'REQUESTED';
            }
            case FlowState.VERIFIED : {
                return 'VERIFIED';
            }
            case FlowState.UPPER_VERIFIED : {
                return 'UPPER_VERIFIED';
            }
            case FlowState.CHECKED : {
                return 'CHECKED';
            }
            case FlowState.PREPARED : {
                return 'PREPARED';
            }
            case FlowState.APPROVED : {
                return 'APPROVED';
            }
            case FlowState.UPPER_APPROVED : {
                return 'UPPER_APPROVED';
            }
            case FlowState.SELECTED : {
                return 'SELECTED';
            }
            case FlowState.EVALUATED : {
                return 'EVALUATED';
            }
            case FlowState.CANCELLED : {
                return 'CANCELLED';
            }
            case FlowState.COMPLETED : {
                return 'COMPLETED';
            }
            case FlowState.REJECTED: {
                return 'REJECTED';
            }
                  case FlowState.ARCHIVED : {
                return 'ARCHIVED';
            }
                 case FlowState.REMOVED : {
                return 'REMOVED';
            }
            default: {
                return value;
            }
        }
    }
}

