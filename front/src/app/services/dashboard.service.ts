import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class DashboardService {

  private DASHBOARD_API: string = environment.endpoint + '/api/dashboard';

  constructor(private http: HttpClient) {
  }


}
