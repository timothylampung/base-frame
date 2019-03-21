import {Pipe, PipeTransform} from "@angular/core";
import {AssetCategory} from "../modules/asset/assets/asset-category.enum";

@Pipe({name: 'assetCategoryPipe'})
export class AssetCategoryPipe implements PipeTransform {

    transform(value: AssetCategory): any {
        if (!value) {
            return value;
        }
        switch (AssetCategory[value.toString()]) {
            case AssetCategory.BUILDING : {
                return 'BUILDING';
            }
            case AssetCategory.ELECTRICAL : {
                return 'ELECTRICAL';
            }
            case AssetCategory.FURNITURE : {
                return 'FURNITURE';
            }
            case AssetCategory.PLUMBING : {
                return 'PLUMBING';
            }
            default: {
                return value;
            }
        }
    }
}
