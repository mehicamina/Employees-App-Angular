import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { IDepartment } from 'src/app/components/department/classes/department';
import { MenuService } from 'src/app/menu.service';

import { DepartmentSection } from './classes/departmentSection';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit {

  option: string;
  private gridApi;
  public rowData = Array<any>();
  departmentNameValidation = false;
  codeValidation = false;

  display = false;
  selectedRow = true;
  displayDelete = false;

  private rowSelection;
  private rowDeselection;

  departmentT = Array<DepartmentSection>();

  department: IDepartment;

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
    { headerName: 'Code', field: 'code', sortable: true, filter: true, resizable: true },
    { headerName: 'Name', field: 'name', sortable: true, filter: true, resizable: true },
    { headerName: 'Short Name', field: 'shortName', sortable: true, filter: true, resizable: true },
    { headerName: 'Status', field: 'status', sortable: true, filter: true, resizable: true },
    { headerName: 'Description', field: 'description', sortable: true, filter: true, resizable: true, width: 400 },
    { headerName: 'Modified', field: 'modified', sortable: true, filter: true, resizable: true },
    { headerName: 'Modified by', field: 'modifiedBy', sortable: true, filter: true, resizable: true },
    { headerName: 'Created', field: 'created', sortable: true, filter: true, resizable: true },
    { headerName: 'Created by', field: 'createdBy', sortable: true, filter: true, resizable: true },
  ];

  showDialogDelete() {
    this.displayDelete = true;
  }

  changeTypeParam(param: any) {
    for (let i = 0; i < param.length; i++) {
      param[i].created = moment(param[i].created).format('YYYY-MM-DD');
      param[i].modified = moment(param[i].modified).format('YYYY-MM-DD');
    }
    return param;
  }

  onSelectionChanged() {
    const selectedRows = this.gridApi.getSelectedRows();
    this.selectedRow = false;
    selectedRows.forEach((selectedRow, index) => {
      this.department.description = selectedRow.description;
      this.department.name = selectedRow.name;
      this.department.shortName = selectedRow.shortName;
      this.department.status = selectedRow.status;
      this.department.code = selectedRow.code;
    });
  }

  createDepartment() {
    console.log(this.department);
    if (!this.department.name) {
      this.departmentNameValidation = true;
    }
    if (!this.department.code) {
      this.codeValidation = true;
    }
    if (this.department.name && this.department.code) {
      this.department.createdBy = localStorage.getItem('userName');
      const request = { entity: this.department };
      this.menuService.createDepartment(request)
        .subscribe(data => {
          this.display = false;
          this.toastr.success('You have succesfuly addad a new department');
          [(this.ngOnInit())];
        });

    }
  }

  deleteDepartment() {
    this.menuService.deleteDepartment(this.department.code)
      .subscribe(data => {
        this.displayDelete = false;
        this.toastr.success('You have succesfuly deleted the selected department');
        [(this.ngOnInit())];
      }
      );
  }


  cancle() {
    this.displayDelete = false;
    this.selectedRow = true;
  }

  showDialog(param: string) {
    this.display = true;
    this.option = param;
    if (param === 'Create') {
      this.department = new IDepartment();
    }
  }

  constructor(private menuService: MenuService, private toastr: ToastrService) {
    this.rowSelection = 'single';
    this.departmentT = [
      { name: 'Hrasno', code: 'HRASNO' },
      { name: 'Unitic', code: 'UNITIC' }
    ];
  }

  onGridReady(params) {
    this.gridApi = params.api;
  }

  editDepartment() {
    if (!this.department.name) {
      this.departmentNameValidation = true;
    }
    if (!this.department.code) {
      this.codeValidation = true;
    }
    if (this.department.name && this.department.shortName && this.department.status && this.department.code) {
      const request = { entity: this.department };
      this.department.modifiedBy = localStorage.getItem('userName');
      this.menuService.editDepartment(request)
        .subscribe(data => {
          this.display = false;
          this.toastr.success('You have succesfuly eddited the selected department');
          [(this.ngOnInit())];
        });
    }
  }

  ngOnInit() {
    this.department = new IDepartment();
    this.menuService.getDepartment()
      .subscribe(data => {
        this.rowData = data['payload'];
        this.changeTypeParam(this.rowData);
      });
  }

}
