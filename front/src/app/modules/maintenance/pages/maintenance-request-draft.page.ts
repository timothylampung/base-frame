import {ChangeDetectorRef, Component, OnInit, Sanitizer} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
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
import {Router} from "@angular/router";

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
                public _sanitizer: DomSanitizer,
                public confirmationService: ConfirmationService,
                public fb: FormBuilder,
                public commonService: CommonService,
                public router: Router,
                public store: Store<AppState>,
                public cdr: ChangeDetectorRef) {
        super(breadcrumbService, messageService, confirmationService, fb, store, cdr);
    }

    ngOnInit() {
        super.ngOnInit()
        console.log(this.maintenanceRequestTask);
        console.log(this.mainForm.value);

        this.mainForm = this.fb.group({
            referenceNo: [''],
            sourceNo: [''],
            description: [{value: '', disabled: true}, Validators.required],
            requester: [],
            requestedDate: [],
            delegator: [],
            verifier: [],
            location: [],
            asset: [],
            remark: [],
            reporter: [],
            delegated: [],
        });

        this.mainForm.patchValue(this.maintenanceRequestTask.request);

        // this.commonService.downloadFile(this.maintenanceRequestTask.request.file.fileName).subscribe(blob => {
        //     this.createImageFromBlob(blob);
        // })
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
                message: 'Approve MaintenanceRequest?',
                acceptLabel: 'Yes',
                rejectLabel: 'No',
                accept: () => {

                    if (this.maintenanceRequestTask.assignee != null) {
                        this.maintenanceRequestTask.request.delegated = true;
                        this.mainForm.value.delegated = true;
                    } else
                        return null;
                    this.store.dispatch(new CompleteMaintenanceRequestTaskAction({taskId: this.maintenanceRequestTask.taskId}));
                    this.store.dispatch(new UpdateMaintenanceRequestAction({
                            ...this.maintenanceRequestTask,
                            ...this.mainForm.value,


                        })
                    );
                    console.log(this.maintenanceRequestTask);
                    this.mainForm.patchValue(this.maintenanceRequestTask);
                    console.log(this.mainForm.value);


                }
            });
        }
    }

    remove() {
        this.confirmationService.confirm({
            message: 'Delete work order?',
            acceptLabel: 'Yes',
            rejectLabel: 'No',
            accept: () => {
                this.store.dispatch(
                    new RemoveMaintenanceRequestTaskAction({taskId: this.maintenanceRequestTask.taskId})
                );
            }
        });
    }

    updateMaintenanceRequest() {
        console.log('this is a test');

        this.store.dispatch(
            new UpdateMaintenanceRequestAction({
                ...this.maintenanceRequestTask,
                ...this.mainForm.value
            })
        );

        // this.confirmationService.confirm({
        //     message: 'Update work order?',
        //     acceptLabel: 'Yes',
        //     rejectLabel: 'No',
        //     accept: () => {
        //     }
        // });
    }

    addActivity() {
        this.showDialog();
    }

    viewAbout() {
        this.selectedAbout = null;
        // this.showAboutDialog();
    }

}
