import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {ApplicationSuccess} from "../models/application-sucess.model";

@Injectable()
export class CommonService {

    private COMMON_API: string = environment.endpoint + '/api/common';

    constructor(public http: HttpClient) {
    }

    // ===================================================================================================================
    // PERIODS
    // ===================================================================================================================

}
