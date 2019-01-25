

import {environment} from '../../environments/environment';
import {Injectable} from "@angular/core";
import {AssetCode, AssetCodeResult} from "../modules/asset/asset-codes/asset-code-model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";


@Injectable()
export class AssetService {

    private ASSET_API: string = environment.endpoint + '/api/asset';

    constructor(public http: HttpClient) {
    }


    // ===================================================================================================================
    // ASSET CODES
    // ===================================================================================================================

    findPagedAssetCodes(filter: string, page: number): Observable<AssetCodeResult> {
        return this.http.get<AssetCodeResult>("/assets/mock-data/asset-codes.json",
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            }
            );
    }

    findAssetCodes(): Observable<AssetCode[]> {
        return this.http.get<AssetCode[]>(this.ASSET_API + '/asset-codes');
    }

    saveAssetCode(code: AssetCode) {
        return this.http.post(this.ASSET_API + '/asset-codes', JSON.stringify(code));
    }

    updateAssetCode(code: AssetCode) {
        return this.http.put(this.ASSET_API + '/asset-codes/' + code.code, JSON.stringify(code));
    }

    removeAssetCode(code: AssetCode) {
        return this.http.delete(this.ASSET_API + '/asset-codes/' + code.code);
    }

}

