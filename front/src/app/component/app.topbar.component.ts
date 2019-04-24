import { Component } from '@angular/core';
import {NgModule} from '@angular/core';
import { AppComponent} from '../app.component';
import {AppShellComponent} from "../app-shell/app-shell.component";

@Component({
  selector: 'app-topbar',
  templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent {

    constructor(public app: AppShellComponent) {}
}
