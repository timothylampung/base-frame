import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MessageService} from "primeng/api";
import {select, Store} from "@ngrx/store";
import {UploadLocationAction} from "./location-action";
import {LocationUploadStatus} from "./location.model";
import {Observable} from "rxjs";
import {selectLocationUploadStatus} from "./location.selector";

@Component({
    selector: 'dex-location-uploader-dialog',
    templateUrl: './location-uploader.dialog.html'
})

export class LocationUploaderDialog implements OnInit {

    showErrorMsg: boolean = false;
    errorMsg: string = '';
    locationUploadStatus$: Observable<LocationUploadStatus>;

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
        this.store.dispatch(new UploadLocationAction({file: event.files[0]}));
        this.store.pipe(
            select(selectLocationUploadStatus))
            .subscribe((state: LocationUploadStatus) => {
                if (!state.uploaded)
                    this.onUploaded.emit(false);
            });
    }

    ngDestroy() {
        this.onClose.unsubscribe();
    }
}
