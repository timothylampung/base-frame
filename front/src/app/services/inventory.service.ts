import {environment} from '../../environments/environment';
import {Injectable} from "@angular/core";
import {AssetCode, AssetCodeResult} from "../modules/asset/asset-codes/asset-code-model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LocationResult} from "../modules/asset/locations/location-model";
import {Location} from "../modules/asset/locations/location-model";
import {Asset, AssetResult} from "../modules/asset/assets/asset-model";
import {Part, PartResult} from "../modules/inventory/parts/part-model";
import {Component, ComponentResult} from "../modules/inventory/components/component-model";



@Injectable()
export class InventoryService {

    private INVENTORY_API: string = environment.endpoint + '/api/inventory';

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
    // ===================================================================================================================
    // Component
    // ===================================================================================================================

    findPagedComponents(filter: string, page: number): Observable<ComponentResult> {
        return this.http.get<ComponentResult>(this.INVENTORY_API + '/components',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            }
        );
    }

    findComponents(): Observable<Component[]> {
        return this.http.get<Component[]>(this.INVENTORY_API + '/components');
    }

    saveComponent(code: Component) {
        return this.http.post(this.INVENTORY_API + '/components', JSON.stringify(code));
    }

    updateComponent(code: Component) {
        return this.http.put(this.INVENTORY_API + '/components/' + code.code, JSON.stringify(code));
    }

    removeComponent(code: Component) {
        return this.http.delete(this.INVENTORY_API + '/components/' + code.code);
    }


    // ===================================================================================================================
    // Part
    // ===================================================================================================================

    findPagedParts(filter: string, page: number): Observable<PartResult> {
        return this.http.get<PartResult>(this.INVENTORY_API + '/parts',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            }
        );
    }

    findParts(): Observable<Part[]> {
        return this.http.get<Part[]>(this.INVENTORY_API + '/parts');
    }

    savePart(code: Part) {
        return this.http.post(this.INVENTORY_API + '/parts', JSON.stringify(code));
    }

    updatePart(code: Part) {
        return this.http.put(this.INVENTORY_API + '/parts/' + code.code, JSON.stringify(code));
    }

    removePart(code: Part) {
        return this.http.delete(this.INVENTORY_API + '/parts/' + code.code);
    }

}

