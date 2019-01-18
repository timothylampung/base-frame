import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {ActivatedRouteSnapshot} from '@angular/router';
import {TitleState} from './title.reducer';
import {ChangeTitleAction} from './title.action';

@Injectable()
export class TitleService {

    constructor(private store: Store<TitleState>) {
    }

    public changeTitle(snapshot: ActivatedRouteSnapshot) {
        let lastChild = snapshot;
        while (lastChild.children.length) {
            lastChild = lastChild.children[0];
        }
        this.store.dispatch(new ChangeTitleAction({title: lastChild.data.title}));
    }
}
