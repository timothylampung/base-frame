import {environment} from '../../environments/environment';
import {Injectable} from "@angular/core";
import {AssetCode, AssetCodeResult} from "../modules/asset/asset-codes/asset-code-model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LocationResult} from "../modules/asset/locations/location-model";
import {Location} from "../modules/asset/locations/location-model";
import {Asset, AssetResult} from "../modules/asset/assets/asset-model";



@Injectable()
export class AssetService {

    private ASSET_API: string = environment.endpoint + '/api/asset';

    constructor(public http: HttpClient) {
    }


    // ===================================================================================================================
    // ASSET CODES
    // ===================================================================================================================

    findPagedAssetCodes(filter: string, page: number): Observable<AssetCodeResult> {
        return this.http.get<AssetCodeResult>(this.ASSET_API + '/asset-codes',
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

    saveAssetCode(assetCode: AssetCode) {
        return this.http.post(this.ASSET_API + '/asset-codes', JSON.stringify(assetCode));
    }

    updateAssetCode(code: AssetCode) {
        return this.http.put(this.ASSET_API + '/asset-codes/' + code.code, JSON.stringify(code));
    }

    removeAssetCode(code: AssetCode) {
        return this.http.delete(this.ASSET_API + '/asset-codes/' + code.code);
    }



    // ===================================================================================================================
    // Location
    // ===================================================================================================================

    findPagedLocations(filter: string, page: number): Observable<LocationResult> {
        return this.http.get<LocationResult>(this.ASSET_API + '/locations',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            }
        );
    }

    findLocations(): Observable<Location[]> {
        return this.http.get<Location[]>(this.ASSET_API + '/locations');
    }

    saveLocation(location: Location) {
        return this.http.post(this.ASSET_API + '/locations' , JSON.stringify(location));
    }

    updateLocation(code: Location) {
        return this.http.put(this.ASSET_API + '/locations/' + code.code, JSON.stringify(code));
    }

    removeLocation(code: Location) {
        return this.http.delete(this.ASSET_API + '/locations/' + code.code);
    }


    // ===================================================================================================================
    // Asset
    // ===================================================================================================================

    findPagedAssets(filter: string, page: number): Observable<AssetResult> {
        console.log(filter + " " + page)
        return this.http.get<AssetResult>(this.ASSET_API + '/assets?filter='+filter+"&page="+page);
    }

    findAssets(): Observable<Asset[]> {
        console.log("AssetService.findAssets()")
        return this.http.get<Asset[]>(this.ASSET_API + '/assets');
    }

    saveAsset(asset: Asset) {
        return this.http.post(this.ASSET_API + '/assets'  , JSON.stringify(asset));
    }

    updateAsset(asset: Asset) {
        return this.http.put(this.ASSET_API + '/assets/' + asset.code, JSON.stringify(asset));
    }

    removeAsset(asset: Asset) {
        return this.http.delete(this.ASSET_API + '/assets/' + asset.code);
    }

}

