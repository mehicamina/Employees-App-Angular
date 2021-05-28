import { CommonModule, DatePipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FullCalendarModule } from '@fullcalendar/angular';
import { AgGridModule } from 'ag-grid-angular';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { DragAndDropModule } from 'angular-draggable-droppable';
import { ChartsModule } from 'ng2-charts';
import { ToastrModule } from 'ngx-toastr';
import { AccordionModule } from 'primeng/accordion';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { TabMenuModule } from 'primeng/tabmenu';

import { AppRoutingModule, rountingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { ActivityComponent } from './components/activity/activity.component';
import { AvailabilityComponent } from './components/availability/availability.component';
import { DepartmentComponent } from './components/department/department.component';
import { EmployeeProjectComponent } from './components/employee-project/employee-project.component';
import { SearchComponent } from './components/employee/search/search.component';
import { TableComponent } from './components/employee/table/table.component';
import { JobTitleComponent } from './components/job-title/job-title.component';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from './components/menu/menu.component';
import { EmployeeInfoComponent } from './components/project/employee-info/employee-info.component';
import { TreeComponent } from './components/project/tree/tree.component';
import { SideComponent } from './components/side/side.component';
import { MenuService } from './menu.service';


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    SideComponent,
    rountingComponents,
    SearchComponent,
    TableComponent,
    TreeComponent,
    EmployeeInfoComponent,
    AvailabilityComponent,
    JobTitleComponent,
    ActivityComponent,
    LoginComponent,
    EmployeeProjectComponent,
    DepartmentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ChartsModule,
    AgGridModule.withComponents([]),
    AccordionModule,
    CalendarModule,
    FormsModule,
    BrowserAnimationsModule,
    DropdownModule,
    DialogModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ChartsModule,
    AgGridModule.withComponents([]),
    AccordionModule,
    CalendarModule,
    FormsModule,
    BrowserAnimationsModule,
    DropdownModule,
    DialogModule,
    CommonModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
    DragAndDropModule,
    FullCalendarModule,
    MatButtonModule,
    MatTabsModule,
    TabMenuModule,
    ToastrModule.forRoot()
    ],
  providers: [MenuService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
