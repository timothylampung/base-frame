import {Component, OnInit} from '@angular/core';
import {environment} from "../environments/environment.dev";
import * as SockJS from 'sockjs-client';
import * as Stomp from "@stomp/stompjs";
import {Notification, NotificationContext} from "./modules/notification/notification.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    private stompClient: Stomp.CompatClient = null;
    notification: Notification = {context : NotificationContext.MAINTENANCE_REQUEST, id : 0 , recieverEmail : 'tech1@spotit.my' };

    constructor() {
    }

    ngOnInit() {
        this.connect();
        this.sendMessage();
    }

    connect() {
        let webSocket = new WebSocket(environment.wsEndpoint + "/notification/websocket");
        this.stompClient = Stomp.Stomp.over(webSocket);
        const _this = this;
        this.stompClient.connect({}, function (frame) {
            _this.stompClient.subscribe('/topic/notify', function (hello) {
                console.log(hello.body)
                // _this.showGreeting(JSON.parse(hello.body).greeting);
            });
        });
    }

    sendMessage() {
        // this.stompClient.send("/spotit/send", {});
    }


    disconnect() {
        if (this.stompClient != null) {
            this.stompClient.disconnect();
        }
    }


}
