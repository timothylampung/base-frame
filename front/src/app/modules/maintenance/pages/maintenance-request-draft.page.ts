import {ChangeDetectorRef, Component, OnInit, Sanitizer} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Store} from '@ngrx/store';
import {ConfirmationService, MessageService} from 'primeng/api';
import {
    CompleteMaintenanceRequestTaskAction,
    RemoveMaintenanceRequestTaskAction,
    UpdateMaintenanceRequestAction,
} from '../maintenance-request.action';
import {MaintenanceRequestPage} from "./maintenance-request.page";
import {BreadcrumbService} from "../../../breadcrumb.service";
import {AppState} from "../../../core/core.state";
import {About} from "../../../models";
import {CommonService} from "../../../services";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
    selector: 'dex-maintenance-request-draft-page',
    templateUrl: './maintenance-request-draft.page.html',
    styleUrls: ['./maintenance-request-draft.page.css']
})
export class MaintenanceRequestDraftPage extends MaintenanceRequestPage implements OnInit {
    selectedAbout: About;
    imageToShow: any;
    cols = [
        {field: 'description', header: 'Description'},
    ];

    constructor(public breadcrumbService: BreadcrumbService,
                public messageService: MessageService,
                public _sanitizer : DomSanitizer,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public commonService : CommonService,
                public store: Store<AppState>,
                public cdr: ChangeDetectorRef) {
        super(breadcrumbService, messageService, confirmationService, fb, store, cdr);
    }

    ngOnInit() {
        super.ngOnInit()
        this.commonService.downloadFile(this.maintenanceRequestTask.request.file.fileName).subscribe(blob => {
            this.createImageFromBlob(blob);
        })
    }

    createImageFromBlob(image: Blob) {
        let reader = new FileReader();
        reader.addEventListener("load", () => {
            this.imageToShow = reader.result;
        }, false);

        if (image) {
            reader.readAsDataURL(image);
        }
    }

    approve() {
        if (this.validateDocument()) {
            this.confirmationService.confirm({
                message: 'Anda pasti semua maklumat yang dimasukkan adalah tepat?',
                acceptLabel: 'Ya',
                rejectLabel: 'Tidak',
                accept: () => {
                    this.store.dispatch(new CompleteMaintenanceRequestTaskAction({taskId: this.maintenanceRequestTask.taskId}));
                    this.store.dispatch(new UpdateMaintenanceRequestAction({
                            ...this.maintenanceRequestTask,
                            ...this.mainForm.value
                        })
                    );
                }
            });
        }
    }

    remove() {
        this.confirmationService.confirm({
            message: 'Anda pasti untuk menghapuskan work order ini?',
            acceptLabel: 'Ya',
            rejectLabel: 'Tidak',
            accept: () => {
                this.store.dispatch(
                    new RemoveMaintenanceRequestTaskAction({taskId: this.maintenanceRequestTask.taskId})
                );
            }
        });
    }

    updateMaintenanceRequest() {
        this.confirmationService.confirm({
            message: 'Anda pasti semua maklumat yang dimasukkan adalah tepat?',
            acceptLabel: 'Ya',
            rejectLabel: 'Tidak',
            accept: () => {
                this.store.dispatch(
                    new UpdateMaintenanceRequestAction({
                        ...this.maintenanceRequestTask,
                        ...this.mainForm.value
                    })
                );
            }
        });
    }

    addActivity() {
        this.showDialog();
    }

    viewAbout() {
        this.selectedAbout = null;
        // this.showAboutDialog();
    }

}
