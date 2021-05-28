import { DatePipe } from '@angular/common';
import { Component, Inject, LOCALE_ID, OnInit } from '@angular/core';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { Iemployees } from 'src/app/employees';
import { MenuService } from 'src/app/menu.service';

import { IjobTitles } from '../../job-title/classes/jobTitles';
import { Gender } from '../classes/gender';
import { JobTitle } from '../classes/jobTitle';
import { SearchFields } from '../classes/searchFields';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})

export class TableComponent implements OnInit {
  option: string;
  employee: Iemployees;
  private gridApi;
  pressed = false;
  public titleResposne = Array<IjobTitles>();
  public activity = Array<any>();
  public department = Array<any>();
  private rowSelection;
  private rowDeselection;
  public mapa: Map<string, string>;
  public mapaInvert: Map<string, string>;
  defaultColDef: {
    resizable: true
  };
  columnDefs = [
    {
      headerName: '',
      field: 'icon',
      width: 50,
      cellRenderer: function (params) {
        return '<span><i class="fas fa-arrow-right fa-lg"></i></span>';
      },
      cellStyle: { 'background-color': '#41484a', 'text-align': 'center' }
    },
    { headerName: 'ID', field: 'id', sortable: true, filter: true, resizable: true, width: 100 },
    { headerName: 'First name', field: 'firstName', sortable: true, filter: true, resizable: true },
    { headerName: 'Last Name', field: 'lastName', sortable: true, filter: true, resizable: true },
    { headerName: 'Gender', field: 'gender', sortable: true, filter: true, resizable: true },
    { headerName: 'Birth Date', field: 'birtDate', sortable: true, filter: true, resizable: true },
    { headerName: 'Phone Number', field: 'phoneNumber', sortable: true, filter: true, resizable: true },
    { headerName: 'Hire Date', field: 'hireDate', sortable: true, filter: true, resizable: true },
    { headerName: 'Job Title', field: 'jobTitleCode', sortable: true, filter: true, resizable: true },
    { headerName: 'Email', field: 'email', sortable: true, filter: true, resizable: true },
    { headerName: 'Activity Code', field: 'activityCode', sortable: true, filter: true, resizable: true },
    { headerName: 'Activity Code', field: 'activityCode', sortable: true, filter: true, resizable: true },
    { headerName: 'Status', field: 'status', sortable: true, filter: true, resizable: true },
    { headerName: 'Modified', field: 'modified', sortable: true, filter: true, resizable: true },
    { headerName: 'Modified by', field: 'modifiedBy', sortable: true, filter: true, resizable: true },
    { headerName: 'Created', field: 'created', sortable: true, filter: true, resizable: true },
    { headerName: 'Created by', field: 'createdBy', sortable: true, filter: true, resizable: true },

  ];
  selectedRow = true;
  jobT = Array<JobTitle>();
  genderT = Array<Gender>();
  public rowData = Array<any>();
  display = false;
  displayDelete = false;
  message: SearchFields;

  showDialog(param: string) {
    this.display = true;
    this.option = param;
    if (param === 'Create') {
      this.employee = new Iemployees();
    }
  }

  changeGederAndTitleParam(param: Iemployees[]) {
    for (let i = 0; i < param.length; i++) {
      if (param[i].gender === 'M') {
        param[i].gender = 'Male';
      } else {
        param[i].gender = 'Female';
      }
      param[i].birtDate = moment(param[i].birtDate).format('YYYY-MM-DD');
      param[i].hireDate = moment(param[i].hireDate).format('YYYY-MM-DD');
      param[i].jobTitleCode = this.mapa.get(param[i].jobTitleCode);
    }
    return param;
  }
  search() {
    this.selectedRow = true;
    if (this.message.firstName || this.message.lastName || this.message.phoneNumber || this.message.hireDate || this.message.jobTitle) {

      if (this.message.hireDate != null) {
        let pom;
        pom = moment(this.message.hireDate).format('YYYY-MM-DDTHH:mm:ss.SSS');
        this.menuService.searchEmployees(this.message.firstName, this.message.lastName, this.message.phoneNumber,
          pom, this.message.jobTitle.code)
          .subscribe(data => {
            this.rowData = data['payload'];
            this.changeGederAndTitleParam(this.rowData);
          });
      } else {
        this.menuService.searchEmployees(this.message.firstName, this.message.lastName, this.message.phoneNumber,
          this.message.hireDate, this.message.jobTitle.code)
          .subscribe(data => {
            this.rowData = data['payload'];
            this.changeGederAndTitleParam(this.rowData);
          });
      }

    } else {
      this.menuService.getEmployees()
        .subscribe(data => {
          this.rowData = data['payload'];
          this.changeGederAndTitleParam(this.rowData);
        });
    }
  }

  constructor(private menuService: MenuService, public datepipe: DatePipe, @Inject(LOCALE_ID) locale: string,
    private toastr: ToastrService) {
    this.rowSelection = 'single';
    this.genderT = [
      { name: 'Male', code: 'M' },
      { name: 'Female', code: 'F' }
    ];
  }
  changeToCodes(gender: any) {
    if (gender === 'Male') {
      this.employee.gender = 'M';
    }
    if (gender === 'Female') {
      this.employee.gender = 'F';
    }
  }
  onSelectionChanged() {
    const selectedRows = this.gridApi.getSelectedRows();
    this.selectedRow = false;
    selectedRows.forEach((selectedRow, index) => {
      this.employee.id = selectedRow.id;
      this.employee.firstName = selectedRow.firstName;
      this.employee.lastName = selectedRow.lastName;
      this.employee.phoneNumber = selectedRow.phoneNumber;
      this.employee.email = selectedRow.email;
      this.employee.status = selectedRow.status;
      this.employee.birtDate = selectedRow.birtDate;
      this.employee.jobTitleCode = this.mapaInvert.get(selectedRow.jobTitleCode);
      this.changeToCodes(selectedRow.gender);
      this.employee.hireDate = selectedRow.hireDate;
      this.employee.activityCode = selectedRow.activityCode;
      this.employee.departmentCode = selectedRow.departmentCode;
      this.employee.created = selectedRow.created;
    });
  }
  onGridReady(params) {
    this.gridApi = params.api;
  }
  newEmployee() {
    this.pressed = true;
    if (this.employee.firstName && this.employee.lastName && this.employee.gender && this.employee.birtDate
      && this.employee.hireDate && this.employee.jobTitleCode && this.employee.activityCode && this.employee.departmentCode) {
      this.employee.createdBy = localStorage.getItem('userName');
      this.employee.birtDate = moment(this.employee.birtDate).format('YYYY-MM-DDTHH:mm:ss.SSS');
      this.employee.hireDate = moment(this.employee.hireDate).format('YYYY-MM-DDTHH:mm:ss.SSS');
      const request = { entity: this.employee };
      this.menuService.createEmployee(request)
        .subscribe(data => {
          this.toastr.success('You have succesfuly addad a new employee');
          this.display = false;
          this.menuService.getEmployees()
            .subscribe(data => {
              this.rowData = data['payload'];
              this.changeGederAndTitleParam(this.rowData);
            });
        });

    }
  }
  editEmployee() {
    this.pressed = true;
    if (this.employee.firstName && this.employee.lastName && this.employee.gender && this.employee.birtDate
      && this.employee.hireDate && this.employee.jobTitleCode && this.employee.activityCode && this.employee.departmentCode) {
      this.employee.modifiedBy = localStorage.getItem('userName');
      this.employee.birtDate = moment(this.employee.birtDate).format('YYYY-MM-DDTHH:mm:ss.SSS');
      this.employee.hireDate = moment(this.employee.hireDate).format('YYYY-MM-DDTHH:mm:ss.SSS');
      const request = { entity: this.employee };
      this.menuService.editEmployee(request)
        .subscribe(data => {
          this.toastr.success('You have succesfuly edited selected employee!');
          this.display = false;
          this.search();
        });
    }
  }
  showDialogDelete() {
    this.displayDelete = true;
  }
  deleteEmploye() {
    this.menuService.deleteEmployee(this.employee.id)
      .subscribe(data => {
        this.toastr.success('You have succesfuly deleted selected employee');
        this.displayDelete = false;
        this.search();
      }
      );
  }
  cancle() {
    this.displayDelete = false;
    this.selectedRow = true;
  }
  ngOnInit() {
    this.mapaInvert = new Map<string, string>();
    this.mapa = new Map<string, string>();
    this.menuService.getAllTitels().subscribe(data => {
      this.titleResposne = data['payload'];
      for (let i = 0; i < this.titleResposne.length; i++) {
        this.mapa.set(this.titleResposne[i].code, this.titleResposne[i].name);
        this.mapaInvert.set(this.titleResposne[i].name, this.titleResposne[i].code);
        const newJobTitle = new JobTitle();
        newJobTitle.name = this.titleResposne[i].name;
        newJobTitle.code = this.titleResposne[i].code;
        this.jobT.push(newJobTitle);
      }
    });
    this.employee = new Iemployees();
    this.menuService.getEmployees()
      .subscribe(data => {
        this.rowData = data['payload'];
        this.changeGederAndTitleParam(this.rowData);
        this.menuService.currmess.subscribe(mess => {
          this.message = mess;
          this.search();
        });
      });
    this.menuService.getActivity()
      .subscribe(data => {
        this.activity = data['payload'];
      });
    this.menuService.getDepartment()
      .subscribe(data => {
        this.department = data['payload'];
      });
  }
}
