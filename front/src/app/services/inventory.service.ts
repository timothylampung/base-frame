import {environment} from '../../environments/environment';
import {Injectable} from "@angular/core";
import {AssetCode, AssetCodeResult} from "../modules/asset/asset-codes/asset-code-model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LocationResult} from "../modules/asset/locations/location-model";
import {Location} from "../modules/asset/locations/location-model";
import {Asset, AssetResult} from "../modules/asset/assets/asset-model";
import {Part, PartResult} from "../modules/inventory/parts/part-model";



@Injectable()
export class InventoryService {

    private PART_API: string = environment.endpoint + '/api/inventory';

    constructor(public http: HttpClient) {
    }

    //
    // // ===================================================================================================================
    // // ASSET CODES
    // // ===================================================================================================================
    //
    // findPagedAssetCodes(filter: string, page: number): Observable<AssetCodeResult> {
    //     return this.http.get<AssetCodeResult>("/assets/mock-data/asset-codes.json",
    //         {
    //             params: {
    //                 filter: filter,
    //                 page: page.toString()
    //             }
    //         }
    //         );
    // }
    //
    // findAssetCodes(): Observable<AssetCode[]> {
    //     return this.http.get<AssetCode[]>(this.ASSET_API + '/asset-codes');
    // }
    //
    // saveAssetCode(code: AssetCode) {
    //     return this.http.post(this.ASSET_API + '/asset-codes', JSON.stringify(code));
    // }
    //
    // updateAssetCode(code: AssetCode) {
    //     return this.http.put(this.ASSET_API + '/asset-codes/' + code.code, JSON.stringify(code));
    // }
    //
    // removeAssetCode(code: AssetCode) {
    //     return this.http.delete(this.ASSET_API + '/asset-codes/' + code.code);
    // }
    //
    //
    //
    // // ===================================================================================================================
    // // Location
    // // ===================================================================================================================
    //
    // findPagedLocations(filter: string, page: number): Observable<LocationResult> {
    //     return this.http.get<LocationResult>(this.ASSET_API + '/locations',
    //         {
    //             params: {
    //                 filter: filter,
    //                 page: page.toString()
    //             }
    //         }
    //     );
    // }
    //
    // findLocations(): Observable<Location[]> {
    //     return this.http.get<Location[]>(this.ASSET_API + '/locations');
    // }
    //
    // saveLocation(code: Location) {
    //     return this.http.post(this.ASSET_API + '/locations', JSON.stringify(code));
    // }
    //
    // updateLocation(code: Location) {
    //     return this.http.put(this.ASSET_API + '/locations/' + code.code, JSON.stringify(code));
    // }
    //
    // removeLocation(code: Location) {
    //     return this.http.delete(this.ASSET_API + '/locations/' + code.code);
    // }


    // ===================================================================================================================
    // Part
    // ===================================================================================================================

    findPagedParts(filter: string, page: number): Observable<PartResult> {
        return this.http.get<PartResult>(this.PART_API + '/parts',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            }
        );
    }

    findParts(): Observable<Part[]> {
        return this.http.get<Part[]>(this.PART_API + '/parts');
    }

    savePart(code: Part) {
        return this.http.post(this.PART_API + '/parts', JSON.stringify(code));
    }

    updatePart(code: Part) {
        return this.http.put(this.PART_API + '/parts/' + code.code, JSON.stringify(code));
    }

    removePart(code: Part) {
        return this.http.delete(this.PART_API + '/parts/' + code.code);
    }

}
