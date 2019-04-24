import {AppRoutes} from "../app.routes";
import {
    AccordionModule,
    AutoCompleteModule,
    BreadcrumbModule,
    ButtonModule,
    CalendarModule,
    CardModule,
    CarouselModule,
    ChartModule,
    CheckboxModule,
    ChipsModule,
    CodeHighlighterModule,
    ColorPickerModule, ConfirmationService,
    ConfirmDialogModule,
    ContextMenuModule,
    DialogModule,
    DropdownModule,
    EditorModule,
    FieldsetModule,
    FileUploadModule,
    GalleriaModule,
    GrowlModule,
    InplaceModule,
    InputMaskModule,
    InputSwitchModule,
    InputTextareaModule,
    InputTextModule,
    LightboxModule,
    ListboxModule,
    MegaMenuModule,
    MenubarModule,
    MenuModule,
    MessageModule,
    MessagesModule,
    MultiSelectModule,
    OrderListModule,
    OrganizationChartModule,
    OverlayPanelModule,
    PaginatorModule,
    PanelMenuModule,
    PanelModule,
    PasswordModule,
    PickListModule,
    ProgressBarModule,
    RadioButtonModule,
    RatingModule,
    ScrollPanelModule,
    SelectButtonModule, SidebarModule,
    SlideMenuModule,
    SliderModule,
    SpinnerModule,
    SplitButtonModule,
    StepsModule,
    TabMenuModule,
    TabViewModule,
    TerminalModule,
    TieredMenuModule,
    ToggleButtonModule,
    ToolbarModule,
    TooltipModule,
    TreeModule,
    TreeTableModule
} from "primeng/primeng";

import {DataViewModule} from "primeng/dataview";
import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";
import {CommonModule} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {NgModule, Pipe} from "@angular/core";
import {FlowStatePipe} from "../pipes/flow-state.pipe";
import {DisableControlDirective} from "../directives/disable-control.directive";
import {StatusTypePipe} from "../pipes/status-type.pipe";

const PRIMENG_MODULES = [
    AccordionModule,
    AutoCompleteModule,
    BreadcrumbModule,
    ButtonModule,
    CalendarModule,
    CardModule,
    CarouselModule,
    ChartModule,
    CheckboxModule,
    ChipsModule,
    CodeHighlighterModule,
    ConfirmDialogModule,
    ColorPickerModule,
    ContextMenuModule,
    DataViewModule,
    DialogModule,
    DropdownModule,
    EditorModule,
    FieldsetModule,
    FileUploadModule,
    GalleriaModule,
    GrowlModule,
    InplaceModule,
    SidebarModule,
    InputMaskModule,
    InputSwitchModule,
    InputTextModule,
    InputTextareaModule,
    LightboxModule,
    ListboxModule,
    MegaMenuModule,
    MenuModule,
    MenubarModule,
    MessageModule,
    MessagesModule,
    MultiSelectModule,
    OrderListModule,
    OrganizationChartModule,
    OverlayPanelModule,
    PaginatorModule,
    PanelModule,
    PanelMenuModule,
    PasswordModule,
    PickListModule,
    ProgressBarModule,
    RadioButtonModule,
    RatingModule,
    ScrollPanelModule,
    SelectButtonModule,
    SlideMenuModule,
    SliderModule,
    SpinnerModule,
    SplitButtonModule,
    StepsModule,
    TableModule,
    TabMenuModule,
    TabViewModule,
    TerminalModule,
    TieredMenuModule,
    ToastModule,
    ToggleButtonModule,
    ToolbarModule,
    TooltipModule,
    TreeModule,
    TreeTableModule,
];

const ANGULAR_MODULES = [
    CommonModule,
    ReactiveFormsModule
];

const CUSTOM_PIPES: Pipe[] = [
    FlowStatePipe,
    StatusTypePipe,
];

@NgModule({
    imports: [
        ANGULAR_MODULES,
        PRIMENG_MODULES
    ],
    exports: [
        ANGULAR_MODULES,
        PRIMENG_MODULES,
        CUSTOM_PIPES,
        DisableControlDirective
    ],
    declarations: [
        CUSTOM_PIPES,
        DisableControlDirective
    ],
    providers: [
        ConfirmationService
    ],
})
export class SharedModule {
}
