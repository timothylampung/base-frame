import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MessageService} from "primeng/api";
import {Store} from "@ngrx/store";
import {UploadStaffAction} from "./staff.action";

@Component({
    selector: 'dex-staff-uploader-dialog',
    templateUrl: './staff-uploader.dialog.html'
})

export class StaffUploaderDialog implements OnInit {

    isEdit: boolean = false;
    editorForm: FormGroup;
    showErrorMsg: boolean = false;
    errorMsg: string = '';

    @Input() showDialog: boolean;
    @Output() onUpload: EventEmitter<boolean> = new EventEmitter<boolean>();
    @Output() onClose: EventEmitter<boolean> = new EventEmitter<boolean>();

    constructor(public fb: FormBuilder,
                public messageService: MessageService,
                public store: Store<any>
    ) {
    }

    ngOnInit() {
        this.editorForm = this.fb.group({
            id: [null],
            body: [null, Validators.required],
        });
    }

    save() {
        this.onUpload.emit(true);
        this.editorForm.reset();
    }

    onShow() {
    }

    onHide() {
        this.onClose.emit(false);
    }

    get ctrl() {
        return this.editorForm.controls;
    }

    onSelectFile() {
        this.showErrorMsg = false;
    }

    handleUpload(event: any) {
        this.store.dispatch(new UploadStaffAction({file: event.files[0]}));
    }

    ngDestroy() {
        this.onClose.unsubscribe();
    }
}
