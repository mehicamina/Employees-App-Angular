import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

import { IBar } from './bar';
import { IActivity } from './components/activity/classes/activity';
import { IDepartment } from './components/department/classes/department';
import { IemployeeProject } from './components/employee-project/classes/employeeProject';
import { SearchFields } from './components/employee/classes/searchFields';
import { IjobTitles } from './components/job-title/classes/jobTitles';
import { EmployeInfo } from './components/project/classes/employeInfo';
import { Itree } from './components/project/classes/tree';
import { IDonut } from './dount';
import { Iemployees } from './employees';
import { AvailibilityM, IMenu } from './menu';
import { IPie } from './pie';

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  private urlM = '/assets/menu/menu.json';
  private urlS = '/assets/menu/sidemenu.json';

  private urlEmployees = 'http://localhost:9000/employee';
  private urlEmployeeProject = 'http://localhost:9000/employeeproject';
  private UrlAvailibility = 'http://localhost:9000/employeeproject/getAvailibility?';
  private urlPie = '/assets/statistic/pieLocation.json';
  private urlDonut = '/assets/statistic/donutTitle.json';
  private urlBar = '/assets/statistic/barYear.json';
  private urlinf = 'http://localhost:9000/employee';
  private urlWorkStatuses = 'http://localhost:9000/employeeproject/work_statuses';
  private urlActivity = 'http://localhost:9000/activity';
  private urlDepartment = 'http://localhost:9000/department';

  headers: HttpHeaders;
  private urlTitle = ' http://localhost:9000/title';
  //login 
  private login = new BehaviorSubject<boolean>(false);
  loginC = this.login.asObservable();
  // employee id and image
  private employeeInfo = new BehaviorSubject<EmployeInfo>(new EmployeInfo());
  empInf = this.employeeInfo.asObservable();
  // tabela search
  private messageS = new BehaviorSubject<SearchFields>(new SearchFields());
  currmess = this.messageS.asObservable();

  changeMessage(mess: SearchFields) {
    this.messageS.next(mess);
  }

  changeEmployeeInfo(emess: EmployeInfo) {
    this.employeeInfo.next(emess);
  }
  loginCorrect(mess: boolean) {
    this.login.next(mess);
  }

  constructor(private http: HttpClient) {

    this.headers = new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:9000',
      'Content-Type': 'application/json'
    });

  }

  getAllTitels(): Observable<IjobTitles[]> {
    return this.http.get<IjobTitles[]>(this.urlTitle);
  }
  createJobTitle(job: any): Observable<IjobTitles[]> {
    return this.http.post<IjobTitles[]>(this.urlTitle, job, { headers: this.headers });
  }
  editJobTitle(emp: any): Observable<IjobTitles> {
    return this.http.put<IjobTitles>(this.urlTitle, emp, { headers: this.headers });
  }
  deleteJobTitle(code: string): Observable<IjobTitles> {
    return this.http.delete<IjobTitles>(this.urlTitle + '/' + code, { headers: this.headers });
  }
  getMeni(): Observable<IMenu[]> {
    return this.http.get<IMenu[]>(this.urlM);
  }
  getSide(): Observable<IMenu[]> {
    return this.http.get<IMenu[]>(this.urlS);
  }

  getPie(): Observable<IPie[]> {
    return this.http.get<IPie[]>(this.urlPie);
  }
  getDonut(): Observable<IDonut[]> {
    return this.http.get<IDonut[]>(this.urlDonut);
  }
  getBar(): Observable<IBar[]> {
    return this.http.get<IBar[]>(this.urlBar);
  }
  getEmployees(): Observable<Iemployees[]> {
    return this.http.get<Iemployees[]>(this.urlEmployees, { headers: this.headers });
  }
  getWorkStatus(): Observable<IActivity[]> {
    return this.http.get<IActivity[]>(this.urlWorkStatuses);
  }
  searchEmployees(name, lastname, phonenumb, hiredate, title): Observable<Iemployees[]> {
    let search = new HttpParams();
    if (name !== '') {
      search = search.set('first_name', name);
    }
    if (lastname !== '') {
      search = search.set('last_name', lastname);
    }
    if (phonenumb !== '') {
      search = search.set('phone_number', phonenumb);
    }
    if (hiredate != null) {
      search = search.set('hire_date', hiredate);
    }
    if (title != null) {
      search = search.set('job_title_code', title);
    }
    return this.http.get<Iemployees[]>(this.urlEmployees + '/search', { headers: this.headers, params: search });
  }

  createEmployee(emp: any): Observable<Iemployees> {
    return this.http.post<Iemployees>(this.urlEmployees, emp, { headers: this.headers });
  }
  editEmployee(emp: any): Observable<Iemployees> {
    return this.http.put<Iemployees>(this.urlEmployees, emp, { headers: this.headers });
  }
  deleteEmployee(id: number): Observable<Iemployees> {
    return this.http.delete<Iemployees>(this.urlEmployees + '/' + id.toString(), { headers: this.headers });
  }
  projectEmployee(project): Observable<Itree[]> {
    let search = new HttpParams();
    if (project !== '') {
      search = search.set('activityCode', project);
    }
    return this.http.get<Itree[]>(this.urlEmployeeProject + '/tree', { headers: this.headers, params: search });
  }

  infoEmployee(id: number): Observable<Iemployees> {
    return this.http.get<Iemployees>(this.urlEmployees + '/' + id.toString(), { headers: this.headers });
  }
  getAvailibility(param: any): Observable<AvailibilityM> {
    return this.http.get<AvailibilityM>(this.UrlAvailibility, { params: param });
  }

  getActivity(): Observable<IActivity[]> {
    return this.http.get<IActivity[]>(this.urlActivity, { headers: this.headers });
  }
  createActivity(emp: any): Observable<IActivity> {
    return this.http.post<IActivity>(this.urlActivity, emp, { headers: this.headers });
  }
  editActivity(emp: any): Observable<IActivity> {
    return this.http.put<IActivity>(this.urlActivity, emp, { headers: this.headers });
  }
  deleteActivity(code: string): Observable<IActivity> {
    return this.http.delete<IActivity>(this.urlActivity + '/' + code, { headers: this.headers });
  }
  getAllEmployees(): Observable<IemployeeProject[]> {
    return this.http.get<IemployeeProject[]>(this.urlEmployeeProject);
  }
  createEmployeeProject(emp: any): Observable<IemployeeProject> {
    return this.http.post<IemployeeProject>(this.urlEmployeeProject, emp, { headers: this.headers });
  }
  editEmployeeProject(emp: any): Observable<IemployeeProject> {
    return this.http.put<IemployeeProject>(this.urlEmployeeProject, emp, { headers: this.headers });
  }
  deleteEmployeeProject(id: number): Observable<IemployeeProject> {
    return this.http.delete<IemployeeProject>(this.urlEmployeeProject + '/' + id.toString(), { headers: this.headers });
  }
  getDepartment(): Observable<IDepartment[]> {
    return this.http.get<IDepartment[]>(this.urlDepartment, { headers: this.headers });
  }
  createDepartment(emp: any): Observable<IDepartment> {
    return this.http.post<IDepartment>(this.urlDepartment, emp, { headers: this.headers });
  }
  editDepartment(emp: any): Observable<IDepartment> {
    return this.http.put<IDepartment>(this.urlDepartment, emp, { headers: this.headers });
  }
  deleteDepartment(code: string): Observable<IDepartment> {
    return this.http.delete<IDepartment>(this.urlDepartment + '/' + code, { headers: this.headers });
  }
}
