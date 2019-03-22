import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment.dev";
import * as Stomp from "@stomp/stompjs";
import {Notification, NotificationContext} from "../modules/notification/notification.model";
import {TOKEN_NAME} from "../core/auth/auth.constant";


@Injectable()
export class WebsocketService {

    private stompClient: Stomp.CompatClient;
    private notification: Notification = {
        context: NotificationContext.MAINTENANCE_REQUEST,
        id: 0,
        recieverEmail: 'tech1@spotit.my',
        senderEmail : ''
    };


    constructor() {
    }

    connect() {
        let webSocket = new WebSocket(environment.wsEndpoint + "/notification/websocket");
        this.stompClient = Stomp.Stomp.over(webSocket);
        const _this = this;
        this.stompClient.connect({}, function (frame) {
            _this.stompClient.subscribe('/topic/notify', function (hello) {
                console.log(JSON.parse(hello.body))
                // _this.showGreeting(JSON.parse(hello.body).greeting);
            });
        });
    }

    sendNotification(notification: Notification) {
        this.stompClient.send("/spotit/notification", {'Authorization': `Bearer ${localStorage.getItem(TOKEN_NAME)}`}, JSON.stringify(notification));
    }




    disconnect() {
        if (this.stompClient != null) {
            this.stompClient.disconnect();
        }
    }

}
