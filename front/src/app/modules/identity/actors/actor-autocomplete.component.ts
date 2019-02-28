import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {select, Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {AutoComplete} from 'primeng/primeng';
import {IdentityState} from "../identity.state";
import {Actor} from "./actor.model";
import {FindPagedActorsAction} from "./actor.action";
import {selectActors} from "./actor.selector";

@Component({
    selector: 'dex-actor-autocomplete',
    templateUrl: './actor-autocomplete.component.html',
    styleUrls: ['./actor-autocomplete.component.css']
})
export class ActorAutocompleteComponent implements OnInit {

    @Input() hideFloatLabel: boolean = false;
    @Input() innerFormControl: FormControl;
    actor: Actor;
    actors: Actor[];
    @ViewChild('p') autoComplete: AutoComplete;

    constructor(private store: Store<IdentityState>) {
    }

    ngOnInit() {
        this.store.pipe(select(selectActors))
            .subscribe((actors: Actor[]) => {
                this.actors = actors;
            });
    }

    search(evt) {
        this.store.dispatch(new FindPagedActorsAction({filter: evt.query, page: 1}));
    }

    filter = (targetArray, filterOptions) => {
        const filterKeys = Object.keys(filterOptions);
        return targetArray.filter(function (eachObj) {
            return filterKeys.some(function (eachKey) {
                return eachObj[eachKey].toUpperCase().includes(filterOptions[eachKey].toUpperCase());
            });
        });
    }
}
