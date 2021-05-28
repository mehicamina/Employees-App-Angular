import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ActivityComponent } from './components/activity/activity.component';
import { AvailabilityComponent } from './components/availability/availability.component';
import { EmployeeProjectComponent } from './components/employee-project/employee-project.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { FileNotFoundComponent } from './components/file-not-found/file-not-found.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { ProjectComponent } from './components/project/project.component';
import { RegistriesComponent } from './components/registries/registries.component';
import { StatisticComponent } from './components/statistic/statistic.component';


const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'statistic', component: StatisticComponent },
  { path: 'employee', component: EmployeeComponent },
  { path: 'project', component: ProjectComponent },
  { path: 'availability', component: AvailabilityComponent },
  { path: 'registries', component: RegistriesComponent },
  { path: '**', component: FileNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const rountingComponents = [HomePageComponent, StatisticComponent, FileNotFoundComponent, EmployeeComponent,
  ProjectComponent, RegistriesComponent, ActivityComponent, EmployeeProjectComponent ];
