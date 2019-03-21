import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";

@Injectable({providedIn: 'root'})
export class JwtHttpInterceptor implements HttpInterceptor {
    constructor() {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let clone: HttpRequest<any>;
        let ignore =
            typeof req.body === "undefined"
            || req.body === null
            || req.body.toString() === "[object FormData]" // <-- This solves your problem
            || req.headers.has("Content-Type");

        if (ignore) {
            return next.handle(req);
        }

        const cloned = req.clone({
            headers: req.headers.set("Content-Type", 'application/json')
        });
        return next.handle(cloned);
    }
}
