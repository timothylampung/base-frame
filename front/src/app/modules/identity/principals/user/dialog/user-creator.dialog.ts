import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {User} from "../user.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Store} from "@ngrx/store";
import {IdentityState} from "../../../identity.state";
import {SaveUserAction} from "../user.action";

@Component({
    selector: 'dex-user-creator-dialog',
    templateUrl: './user-creator.dialog.html'
})

export class UserCreatorDialog implements OnInit {

    public test: string = 'User';

    creatorForm: FormGroup;
    @Input() user: User;
    @Output() onSubmit: EventEmitter<boolean> = new EventEmitter<boolean>();

    constructor(public fb: FormBuilder,
                private store: Store<IdentityState>) {
    }

    ngOnInit():void{
        this.creatorForm = this.fb.group({
            name: ['', Validators.required],
            realName: ['', Validators.required],
            password: ['', Validators.required],
            email: ['', Validators.required],
        });
    }

    submit() {
        this.store.dispatch(new SaveUserAction(this.creatorForm.value));
        this.onSubmit.emit(true);
        this.creatorForm.reset();
    }
}
