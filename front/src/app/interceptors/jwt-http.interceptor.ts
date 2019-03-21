import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {TOKEN_NAME} from "../core/auth/auth.constant";

@Injectable({providedIn: 'root'})
export class JwtHttpInterceptor implements HttpInterceptor {
    constructor() {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let clone: HttpRequest<any>;
        clone = request.clone({
            setHeaders: {
                'Content-Type': `application/json`,
                'Authorization': `Bearer ${localStorage.getItem(TOKEN_NAME)}`
            }
        });
        return next.handle(clone);
    }
}
