import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MessageService} from "primeng/api";
import {select, Store} from "@ngrx/store";
import {UploadStaffAction} from "./staff.action";
import {selectAssetUploadStatus} from "../../asset/assets/asset.selector";
import {AssetUploadStatus} from "../../asset/assets/asset.model";
import {StaffUploadStatus} from "./staff.model";
import {selectStaffUploadStatus} from "./staff.selector";

@Component({
    selector: 'dex-staff-uploader-dialog',
    templateUrl: './staff-uploader.dialog.html'
})

export class StaffUploaderDialog implements OnInit {

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

    onHide() {
        this.onClose.emit(false);
    }

    onSelectFile() {
        this.showErrorMsg = false;
    }

    handleUpload(event: any) {
        this.store.dispatch(new UploadStaffAction({file: event.files[0]}));
        this.store.pipe(
            select(selectStaffUploadStatus))
            .subscribe((state: StaffUploadStatus) => {
                if (!state.uploaded)
                    this.onUploaded.emit(false);
            });
    }

    ngDestroy() {
        this.onClose.unsubscribe();
    }
}
