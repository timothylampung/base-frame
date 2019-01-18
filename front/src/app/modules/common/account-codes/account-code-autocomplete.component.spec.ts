/* tslint:disable:no-unused-variable */
import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AccountCodeAutocompleteComponent} from './account-code-autocomplete.component';

describe('AccountCodeAutocompleteComponent', () => {
    let component: AccountCodeAutocompleteComponent;
    let fixture: ComponentFixture<AccountCodeAutocompleteComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [AccountCodeAutocompleteComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(AccountCodeAutocompleteComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
