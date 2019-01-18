import {AfterViewInit, Directive, ElementRef} from '@angular/core';

@Directive({
    selector: '[cngAutofocus],[autofocus]'
})
export class AutofocusDirective implements AfterViewInit {

    constructor(private el: ElementRef) {
    }

    ngAfterViewInit() {
        this.el.nativeElement.focus();

        console.log(this.el.nativeElement);
    }

}
