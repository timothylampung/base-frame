import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MessageService} from "primeng/api";
import {select, Store} from "@ngrx/store";
import {UploadAssetAction} from "./asset.action";
import {selectLocationUploadStatus} from "../locations/location.selector";
import {LocationUploadStatus} from "../locations/location.model";
import {selectAssetUploadStatus} from "./asset.selector";
import {AssetUploadStatus} from "./asset.model";

@Component({
    selector: 'dex-asset-uploader-dialog',
    templateUrl: './asset-uploader.dialog.html'
})

export class AssetUploaderDialog implements OnInit {

    showErrorMsg: boolean = false;
    errorMsg: string = '';

    @Input() showDialog: boolean;
    @Output() onUpload: EventEmitter<boolean> = new EventEmitter<boolean>();
    @Output() onUploaded: EventEmitter<boolean> = new EventEmitter<boolean>();
    @Output() onClose: EventEmitter<boolean> = new EventEmitter<boolean>();

    constructor(public fb: FormBuilder,
                public messageService: MessageService,
                public store: Store<any>
    ) {
    }

    ngOnInit() {
    }

    save() {
        this.onUpload.emit(true);
    }

    onHide() {
        this.onClose.emit(false);
    }

    onSelectFile() {
        this.showErrorMsg = false;
    }

    handleUpload(event: any) {
        this.store.dispatch(new UploadAssetAction({file: event.files[0]}));
        this.store.pipe(
            select(selectAssetUploadStatus))
            .subscribe((state: AssetUploadStatus) => {
                if (!state.uploaded)
                    this.onUploaded.emit(false);
            });
    }

    ngDestroy() {
        this.onClose.unsubscribe();
    }
}
