import {Component, OnInit} from '@angular/core';
import {select, Store} from "@ngrx/store";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs/index";
import {LoginAction} from "../core/auth/auth.action";
import {selectAuthLoading} from "../core/auth/auth.selector";

@Component({
    selector: 'cng-login',
    templateUrl: './login.page.html',
    styleUrls: ['./login.page.css']
})
export class LoginPage implements OnInit {

    isLoading$: Observable<boolean>;
    loginForm: FormGroup;

    constructor(private store: Store<any>,
                private fb: FormBuilder) {
        this.isLoading$ = this.store.pipe(select(selectAuthLoading));
    }

    ngOnInit(): void {
        this.loginForm = this.fb.group({
                username: ['', Validators.required],
                password: ['', Validators.required]
            }
        );
    }

    login(): void {
        let username = this.loginForm.controls['username'].value;
        let password = this.loginForm.controls['password'].value;
        this.store.dispatch(new LoginAction(username, password));
    }

    forgotPassword() {
    }
}
