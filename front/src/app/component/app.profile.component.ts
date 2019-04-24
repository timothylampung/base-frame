import {Component} from '@angular/core';
import { AppComponent} from '../app.component';
import {trigger, state, transition, style, animate} from '@angular/animations';
import {AppShellComponent} from "../app-shell/app-shell.component";

@Component({
    selector: 'app-inline-profile',
    template: `
        <div class="user-profile">
            <a href="#" (click)="onClick($event)" id="sidebar-profile-button">
                <img src="assets/layout/images/avatar.png" alt="california-layout"/>
                <span class="sidebar-profile-name">Sapiei Abidin</span>
                <span class="sidebar-profile-role">Administrator</span>
            </a>

            <ul id="sidebar-usermenu" class="usermenu" [@menu]="active ? 'visible' : 'hidden'">
                <li #profile [ngClass]="{'menuitem-active':activeProfileItem === profile}">
                    <a href="#" (click)="onProfileItemClick($event,profile)">
                        <i class="fa fa-fw fa-user"></i>
                        <span class="topbar-item-name">Profile</span>
                    </a>
                </li>
                <li #settings [ngClass]="{'menuitem-active':activeProfileItem === settings}">
                    <a href="#" (click)="onProfileItemClick($event,settings)">
                        <i class="fa fa-fw fa-cog"></i>
                        <span class="topbar-item-name">Settings</span>
                        <i class="layout-menuitem-toggler fa fa-fw fa-angle-down"></i>
                    </a>
                    <ul>
                        <li role="menuitem">
                            <a href="#">
                                <i class="fa fa-fw fa-paint-brush"></i>
                                <span>Change Theme</span>
                            </a>
                        </li>
                        <li role="menuitem">
                            <a href="#">
                                <i class="fa fa-fw fa-star-o"></i>
                                <span>Favorites</span>
                            </a>
                        </li>
                        <li role="menuitem">
                            <a href="#">
                                <i class="fa fa-fw fa-lock"></i>
                                <span>Lock Screen</span>
                            </a>
                        </li>
                        <li role="menuitem">
                            <a href="#">
                                <i class="fa fa-fw fa-picture-o"></i>
                                <span>Wallpaper</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li #messages [ngClass]="{'menuitem-active':activeProfileItem === messages}">
                    <a href="#" (click)="onProfileItemClick($event,messages)">
                        <i class="fa fa-fw fa-envelope-o"></i>
                        <span class="topbar-item-name">Messages</span>
                        <i class="layout-menuitem-toggler fa fa-fw fa-angle-down"></i>
                    </a>
                    <ul>
                        <li role="menuitem">
                            <a href="#" class="topbar-message">
                                <img src="assets/layout/images/avatar1.png" width="25"/>
                                <span>Give me a call</span>
                            </a>
                        </li>
                        <li role="menuitem">
                            <a href="#" class="topbar-message">
                                <img src="assets/layout/images/avatar2.png" width="25"/>
                                <span>Sales reports attached</span>
                            </a>
                        </li>
                        <li role="menuitem">
                            <a href="#" class="topbar-message">
                                <img src="assets/layout/images/avatar3.png" width="25"/>
                                <span>About your invoice</span>
                            </a>
                        </li>
                        <li role="menuitem">
                            <a href="#" class="topbar-message">
                                <img src="assets/layout/images/avatar2.png" width="25"/>
                                <span>Meeting today at 10pm</span>
                            </a>
                        </li>
                        <li role="menuitem">
                            <a href="#" class="topbar-message">
                                <img src="assets/layout/images/avatar4.png" width="25"/>
                                <span>Out of office</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li #notifications [ngClass]="{'menuitem-active':activeProfileItem === notifications}">
                    <a href="#" (click)="onProfileItemClick($event,notifications)">
                        <i class="fa fa-fw fa-bell-o"></i>
                        <span class="topbar-item-name">Notifications</span>
                        <i class="layout-menuitem-toggler fa fa-fw fa-angle-down"></i>
                    </a>
                    <ul>
                        <li role="menuitem">
                            <a href="#">
                                <i class="fa fa-fw fa-tasks"></i>
                                <span>Pending tasks</span>
                            </a>
                        </li>
                        <li role="menuitem">
                            <a href="#">
                                <i class="fa fa-fw fa-calendar-check-o"></i>
                                <span>Meeting today at 3pm</span>
                            </a>
                        </li>
                        <li role="menuitem">
                            <a href="#">
                                <i class="fa fa-fw fa-download"></i>
                                <span>Download documents</span>
                            </a>
                        </li>
                        <li role="menuitem">
                            <a href="#">
                                <i class="fa fa-fw fa-plane"></i>
                                <span>Book flight</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    `,
    animations: [
        trigger('menu', [
            state('hidden', style({
                height: '0px'
            })),
            state('visible', style({
                height: '*'
            })),
            transition('visible => hidden', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)')),
            transition('hidden => visible', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)'))
        ])
    ]
})
export class AppProfileComponent {

    active: boolean;

    activeProfileItem: any;

    constructor(public app: AppShellComponent) {}

    onClick(event) {
        this.active = !this.active;
        event.preventDefault();
    }

    onProfileItemClick(event, item) {
        if (this.activeProfileItem === item) {
            this.activeProfileItem = null; } else {
            this.activeProfileItem = item; }

        event.preventDefault();
    }
}
