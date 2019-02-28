import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {WorkOrderComment} from "./work-order-comment.model";

@Component({
    selector: 'dex-work-order-comment-editor-dialog',
    templateUrl: './work-order-comment-editor.dialog.html'
})

export class WorkOrderCommentEditorDialog implements OnInit {

    isEdit: boolean = false;
    editorForm: FormGroup;
    @Input() comment: WorkOrderComment;
    @Input() showDialog: boolean;
    @Output() onSave: EventEmitter<WorkOrderComment> = new EventEmitter<WorkOrderComment>();
    @Output() onClose: EventEmitter<boolean> = new EventEmitter<boolean>();
    @ViewChild('bodyField') bodyField;

    constructor(public fb: FormBuilder) {
    }

    ngOnInit() {
        this.editorForm = this.fb.group({
            id: [null],
            body: [null, Validators.required],
        });
    }

    createComment(): WorkOrderComment {
        return {
            id: null,
            body: null,
            poster: null
        };
    }

    save() {
        let comment: WorkOrderComment = {
            ...this.editorForm.value,
        };
        this.onSave.emit(comment);
        this.editorForm.reset();
        this.focusOnInput();
    }

    onShow() {
        this.editorForm.patchValue(this.createComment());
        this.focusOnInput();
    }

    onHide() {
        this.onClose.emit(false);
    }

    focusOnInput() {
        setTimeout(() => {
            this.bodyField.nativeElement.focus();
        }, 0);
    }

    get ctrl() {
        return this.editorForm.controls;
    }

    ngDestroy() {
        this.onSave.unsubscribe();
        this.onClose.unsubscribe();
    }
}
