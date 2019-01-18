import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {ReferenceNo} from '../modules/system/reference-nos/reference-no.model';
import {Configuration} from '../modules/system/configurations/configuration.model';
import {ConfigurationResult} from '../modules/common/configurations/configuration.model';

@Injectable()
export class SystemService {

  private SYSTEM_API: string = environment.endpoint + '/api/system';

  constructor(private http: HttpClient) {
  }

  // ===================================================================================================================
  // REFERENCE NO
  // ===================================================================================================================

  findReferenceNos(): Observable<ReferenceNo[]> {
    return this.http.get<ReferenceNo[]>(this.SYSTEM_API + '/reference-nos');
  }

  findReferenceNoByCode(code: string): Observable<ReferenceNo> {
    return this.http.get<ReferenceNo>(this.SYSTEM_API + '/reference-nos/' + code);
  }

  saveReferenceNo(code: ReferenceNo) {
    return this.http.post(this.SYSTEM_API + '/reference-nos', JSON.stringify(code));
  }

  updateReferenceNo(code: ReferenceNo) {
    return this.http.put(this.SYSTEM_API + '/reference-nos/' + code.code, JSON.stringify(code));
  }

  removeReferenceNo(code: ReferenceNo) {
    return this.http.delete(this.SYSTEM_API + '/reference-nos/' + code.code);
  }

  // ===================================================================================================================
  // REFERENCE NO
  // ===================================================================================================================

    findPagedConfigurations(filter: string, page: number): Observable<ConfigurationResult> {
        const url = `${this.SYSTEM_API}/configurations?filter=${filter}&page=${page}`;
        return this.http.get<ConfigurationResult>(url);
    }

  findConfigurations(): Observable<Configuration[]> {
    return this.http.get<Configuration[]>(this.SYSTEM_API + '/configurations');
  }

  findConfigurationByKey(key: string): Observable<Configuration> {
    return this.http.get<Configuration>(this.SYSTEM_API + '/configurations/' + key);
  }

  saveConfiguration(key: Configuration) {
    return this.http.post(this.SYSTEM_API + '/configurations', JSON.stringify(key));
  }

  updateConfiguration(key: Configuration) {
    return this.http.put(this.SYSTEM_API + '/configurations/' + key.key, JSON.stringify(key));
  }

  removeConfiguration(key: Configuration) {
    return this.http.delete(this.SYSTEM_API + '/configurations/' + key.key);
  }
}
