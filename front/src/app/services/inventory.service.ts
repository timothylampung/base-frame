import {environment} from '../../environments/environment';
import {Injectable} from "@angular/core";
import {AssetCode, AssetCodeResult} from "../modules/asset/asset-codes/asset-code-model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LocationResult} from "../modules/asset/locations/location-model";
import {Location} from "../modules/asset/locations/location-model";
import {Asset, AssetResult} from "../modules/asset/assets/asset-model";
import {Part, PartResult} from "../modules/inventory/parts/part-model";
import {PartComponent, ComponentResult} from "../modules/inventory/components/component-model";
import {PartCode, PartCodeResult} from "../modules/inventory/part-codes/part-code-model";



@Injectable()
export class InventoryService {

    private INVENTORY_API: string = environment.endpoint + '/api/inventory';

    constructor(public http: HttpClient) {
    }


    // ===================================================================================================================
    // PART CODES
    // ===================================================================================================================

    findPagedPartCodes(filter: string, page: number): Observable<PartCodeResult> {
        return this.http.get<PartCodeResult>(this.INVENTORY_API + '/part-codes',
            {
                params: {
                    filter: filter,
                    page: page.toString()
                }
            }
            );
    }

    findPartCodes(): Observable<PartCode[]> {
        return this.http.get<PartCode[]>(this.INVENTORY_API + '/part-codes');
    }

    savePartCode(code: PartCode) {
        return this.http.post(this.INVENTORY_API + '/part-codes', JSON.stringify(code));
    }

    updatePartCode(code: PartCode) {
        return this.http.put(this.INVENTORY_API + '/part-codes/' + code.code, JSON.stringify(code));
    }

    removePartCode(code: PartCode) {
        return this.http.delete(this.INVENTORY_API + '/part-codes/' + code.code);
    }



    // ===================================================================================================================
    // PartComponent
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

    findComponents(): Observable<PartComponent[]> {
        return this.http.get<PartComponent[]>(this.INVENTORY_API + '/components');
    }

    saveComponent(code: PartComponent) {
        return this.http.post(this.INVENTORY_API + '/components', JSON.stringify(code));
    }

    updateComponent(code: PartComponent) {
        return this.http.put(this.INVENTORY_API + '/components/' + code.code, JSON.stringify(code));
    }

    removeComponent(code: PartComponent) {
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

