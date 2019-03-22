import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {WebsocketService} from "./services/websocket.service";
import {NotificationContext} from "./modules/notification/notification.model";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnChanges {

    constructor(private websocketService: WebsocketService) {
    }

    ngOnInit() {
        this.websocketService.connect();
    }


    send() {
        this.websocketService.sendNotification({
            context: NotificationContext.MAINTENANCE_REQUEST,
            message: 'You Have New Maintenance Request',
            id: 0,
            recieverEmail: 'tech1@spotit.my',
            senderEmail: 'fm2@spotit.my'
        })
    }

    ngOnChanges(changes: SimpleChanges): void {
        console.log(changes)
    }

}
