import {Component, ViewChild, AfterViewInit} from '@angular/core';
import {AppComponent} from '../app.component';
import {ScrollPanel} from 'primeng/primeng';
import {AppShellComponent} from "../app-shell/app-shell.component";

@Component({
    selector: 'app-rightpanel',
    templateUrl: './app.rightpanel.component.html'
})
export class AppRightPanelComponent implements AfterViewInit {

    @ViewChild('scrollRightPanel') rightPanelMenuScrollerViewChild: ScrollPanel;

    constructor(public app: AppShellComponent) {}

    ngAfterViewInit() {
        setTimeout(() => {this.rightPanelMenuScrollerViewChild.moveBar(); }, 100);
    }

    onTabChange(event) {
        setTimeout(() => {this.rightPanelMenuScrollerViewChild.moveBar(); }, 450);
    }
}
