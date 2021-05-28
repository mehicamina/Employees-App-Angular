import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { MenuService } from 'src/app/menu.service';

import { IemployeeProject } from './classes/employeeProject';

@Component({
  selector: 'app-employee-project',
  templateUrl: './employee-project.component.html',
  styleUrls: ['./employee-project.component.css']
})
export class EmployeeProjectComponent implements OnInit {
  pressed = false;
  display = false;
  displayDelete = false;
  option: string;
  employeeProject: IemployeeProject;
  activity: Array<any>;
  public rowData = Array<IemployeeProject>();
  public allEmployes = Array<any>();
  public mapForConvert = new Map<number, string>();
  public mapForConvertInvert = new Map<string, number>();
  private rowSelection;
  selectedRow = true;
  public employee: IemployeeProject;
  private gridApi;
  defaultColDef: {
    resizable: true
  };
  columnDefs = [
    {
      headerName: '',
      field: "icon",
      width: 50,
      cellRenderer: function (params) {
        return '<span><i class="fas fa-arrow-right fa-lg"></i></span>';
      },
      cellStyle: { 'background-color': '#41484a', 'text-align': 'center' }
    },
    { headerName: 'id', field: 'id', sortable: true, filter: true, resizable: true },
    { headerName: 'Employee', field: 'employeeId', sortable: true, filter: true, resizable: true },
    { headerName: 'Activity code', field: 'activityCode', sortable: true, filter: true, resizable: true },
    { headerName: 'Valid to', field: 'validTo', sortable: true, filter: true, resizable: true },
    { headerName: 'Valid from', field: 'validFrom', sortable: true, filter: true, resizable: true },
    { headerName: 'Modified by', field: 'modifiedBy', sortable: true, filter: true, resizable: true },
    { headerName: 'Modified', field: 'modified', sortable: true, filter: true, resizable: true },
    { headerName: 'Created by', field: 'createdBy', sortable: true, filter: true, resizable: true },
    { headerName: 'Created', field: 'created', sortable: true, filter: true, resizable: true }
  ];

  constructor(private menuService: MenuService, private toastr: ToastrService) {
    this.rowSelection = 'single';
  }

  showDialog(param: string) {
    this.display = true;
    this.option = param;
    if (param === 'Create') {
      this.employee = new IemployeeProject();
    }
  }
  showDialogDelete() {
    this.displayDelete = true;
  }
  cancle() {
    this.displayDelete = false;
    this.selectedRow = true;
  }
  newEmployeeProject() {
    this.pressed = true;
    if (this.employee.employeeId && this.employee.activityCode && this.employee.validFrom
      && this.employee.validTo) {
      this.employee.validFrom = moment(this.employee.validFrom).format('YYYY-MM-DDTHH:mm:ss.SSS');
      this.employee.validTo = moment(this.employee.validTo).format('YYYY-MM-DDTHH:mm:ss.SSS');
      const request = { entity: this.employee };
      this.menuService.createEmployeeProject(request)
        .subscribe(data => {
          this.toastr.success('You have succesfuly addad a new employee project');
          this.display = false;
          this.employee = new IemployeeProject();
          this.menuService.getEmployees().subscribe(data => {
            this.allEmployes = data['payload'];
            for (let k = 0; k < this.allEmployes.length; k++) {
              this.mapForConvert.set(this.allEmployes[k].id, this.allEmployes[k].firstName + " " + this.allEmployes[k].lastName);
              this.mapForConvertInvert.set(this.allEmployes[k].firstName + " " + this.allEmployes[k].lastName, this.allEmployes[k].id);
            }
            this.rowData = [];
            this.menuService.getAllEmployees()
              .subscribe(data => {
                this.rowData = data['payload'];
                for (let j = 0; j < this.rowData.length; j++) {
                  this.rowData[j].employeeId = this.mapForConvert.get(Number(this.rowData[j].employeeId));
                }
                this.convertor(this.rowData);
              });
          });
        });
    }
  }
  onGridReady(params) {
    this.gridApi = params.api;
  }
  onSelectionChanged() {
    const selectedRows = this.gridApi.getSelectedRows();
    this.selectedRow = false;
    selectedRows.forEach((selectedRow, index) => {
      this.employee.activityCode = selectedRow.activityCode;
      this.employee.id = selectedRow.id;
      this.employee.employeeId = selectedRow.employeeId;
      this.employee.modified = selectedRow.modified;
      this.employee.modifiedBy = selectedRow.modifiedBy;
      this.employee.status = selectedRow.status;
      this.employee.validFrom = selectedRow.validFrom;
      this.employee.validTo = selectedRow.validTo;
      this.employee.created = selectedRow.created;
      this.employee.createdBy = selectedRow.createdBy;

    });

  }
  convertor(param: IemployeeProject[]) {
    for (let i = 0; i <= param.length; i++) {
      param[i].created = param[i].created.substr(0, 10);
      if (param[i].modified !== undefined) {
        param[i].modified = param[i].modified.substr(0, 10);
      }
      if (param[i].modifiedBy !== undefined) {
        param[i].modifiedBy = param[i].modifiedBy.substr(0, 10);
      }
      if (param[i].validFrom !== undefined) {
        param[i].validFrom = param[i].validFrom.substr(0, 10);
      }
      if (param[i].validTo !== undefined) {
        param[i].validTo = param[i].validTo.substr(0, 10);
      }

    }

  }

  editEmployeeProject() {
    this.pressed = true;
    console.log(this.mapForConvertInvert.get(this.employee.employeeId));

    this.employee.employeeId = (this.mapForConvertInvert.get(this.employee.employeeId)).toString();
    if (this.employee.employeeId && this.employee.activityCode && this.employee.validFrom
      && this.employee.validTo) {
      this.employee.validFrom = moment(this.employee.validFrom).format('YYYY-MM-DDTHH:mm:ss.SSS');
      this.employee.validTo = moment(this.employee.validTo).format('YYYY-MM-DDTHH:mm:ss.SSS');
      this.employee.created = moment(this.employee.created).format('YYYY-MM-DDTHH:mm:ss.SSS');
      this.employee.modified = null;
      this.employee.modifiedBy = localStorage.getItem('userName');
      const request = { entity: this.employee };
      this.menuService.editEmployeeProject(request)
        .subscribe(data => {
          this.toastr.success('You have succesfuly edited an employee project');
          this.display = false;
          this.employee = new IemployeeProject();
          this.menuService.getEmployees().subscribe(data => {
            this.allEmployes = data['payload'];
            for (let k = 0; k < this.allEmployes.length; k++) {
              this.mapForConvert.set(this.allEmployes[k].id, this.allEmployes[k].firstName + " " + this.allEmployes[k].lastName);
              this.mapForConvertInvert.set(this.allEmployes[k].firstName + " " + this.allEmployes[k].lastName, this.allEmployes[k].id);
            }
            this.rowData = [];
            this.menuService.getAllEmployees()
              .subscribe(data => {
                this.rowData = data['payload'];
                for (let j = 0; j < this.rowData.length; j++) {
                  this.rowData[j].employeeId = this.mapForConvert.get(Number(this.rowData[j].employeeId));
                }
                this.employee = new IemployeeProject();
                this.convertor(this.rowData);
              });
          });
        });
    }
  }
  deleteEmployeeProject() {
    this.menuService.deleteEmployeeProject(this.employee.id)
      .subscribe(data => {
        this.toastr.success('You have succesfuly deleted selected employee');
        this.displayDelete = false;
        this.employee = new IemployeeProject();
        this.menuService.getEmployees().subscribe(data => {
          this.allEmployes = data['payload'];
          for (let k = 0; k < this.allEmployes.length; k++) {
            this.mapForConvert.set(this.allEmployes[k].id, this.allEmployes[k].firstName + " " + this.allEmployes[k].lastName);
            this.mapForConvertInvert.set(this.allEmployes[k].firstName + " " + this.allEmployes[k].lastName, this.allEmployes[k].id);
          }
          this.rowData = [];
          this.menuService.getAllEmployees()
            .subscribe(data => {
              this.rowData = data['payload'];
              for (let j = 0; j < this.rowData.length; j++) {
                this.rowData[j].employeeId = this.mapForConvert.get(Number(this.rowData[j].employeeId));
              }
              this.convertor(this.rowData);
            });

        });
      }
      );
  }
  ngOnInit() {
    this.employee = new IemployeeProject();
    this.menuService.getEmployees().subscribe(data => {
      this.allEmployes = data['payload'];
      console.log(data['payload']);
      for (let k = 0; k < this.allEmployes.length; k++) {
        this.mapForConvert.set(this.allEmployes[k].id, this.allEmployes[k].firstName + " " + this.allEmployes[k].lastName);
        this.mapForConvertInvert.set(this.allEmployes[k].firstName + ' ' + this.allEmployes[k].lastName, this.allEmployes[k].id);
      }
      this.rowData = [];
      this.menuService.getAllEmployees()
        .subscribe(data1 => {
          this.rowData = data1['payload'];
          for (let j = 0; j < this.rowData.length; j++) {
            this.rowData[j].employeeId = this.mapForConvert.get(Number(this.rowData[j].employeeId));
          }
          this.convertor(this.rowData);
        });
    });
    this.menuService.getActivity()
      .subscribe(data => {
        this.activity = data['payload'];
      });
  }

}
