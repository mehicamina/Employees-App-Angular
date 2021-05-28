import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { MenuService } from 'src/app/menu.service';

import { IActivity } from './classes/activity';
import { TypeOfActivity } from './classes/type';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  option: string;
  private gridApi;
  public rowData = Array<any>();
  pressed = false;
  typeT = Array<TypeOfActivity>();
  activity: IActivity;
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
    { headerName: 'Code', field: 'code', sortable: true, filter: true, resizable: true },
    { headerName: 'Name', field: 'name', sortable: true, filter: true, resizable: true },
    { headerName: 'Short Name', field: 'shortName', sortable: true, filter: true, resizable: true },
    { headerName: 'Type', field: 'type', sortable: true, filter: true, resizable: true },
    { headerName: 'Status', field: 'status', sortable: true, filter: true, resizable: true, width: 100 },
    { headerName: 'Description', field: 'description', sortable: true, filter: true, resizable: true, width: 300 },
    { headerName: 'Modified', field: 'modified', sortable: true, filter: true, resizable: true },
    { headerName: 'Modified by', field: 'modifiedBy', sortable: true, filter: true, resizable: true },
    { headerName: 'Created', field: 'created', sortable: true, filter: true, resizable: true },
    { headerName: 'Created by', field: 'createdBy', sortable: true, filter: true, resizable: true },

  ];
  display = false;
  selectedRow = true;
  displayDelete = false;

  private rowSelection;
  private rowDeselection;

  showDialogDelete() {
    this.displayDelete = true;
  }

  changeToCodes(type: any) {
    if (type === 'Project') {
      this.activity.type = 'PROJECT';
    }
    if (type === 'Work Status') {
      this.activity.type = 'WORK_STATUS';
    }
  }

  changeTypeParam(param: any) {
    for (let i = 0; i < param.length; i++) {
      if (param[i].type === 'PROJECT') {
        param[i].type = 'Project';
      } else {
        param[i].type = 'Work Status';
      }
      param[i].created = moment(param[i].created).format('YYYY-MM-DD');
      param[i].modified = moment(param[i].modified).format('YYYY-MM-DD');
    }
    return param;
  }

  onSelectionChanged() {
    const selectedRows = this.gridApi.getSelectedRows();
    this.selectedRow = false;
    selectedRows.forEach((selectedRow, index) => {
      this.activity.description = selectedRow.description;
      this.activity.name = selectedRow.name;
      this.activity.shortName = selectedRow.shortName;
      this.activity.status = selectedRow.status;
      this.activity.code = selectedRow.code;
      this.activity.createdBy = selectedRow.createdBy;
      this.activity.created = selectedRow.created;
      this.activity.modified = selectedRow.modified;
      this.changeToCodes(selectedRow.type);
    });
  }

  createActivity() {
    this.pressed = true;
    if (this.activity.name && this.activity.shortName && this.activity.code && this.activity.type) {
      this.activity.createdBy = localStorage.getItem('userName');
      const request = { entity: this.activity };
      this.menuService.createActivity(request)
        .subscribe(data => {
          this.toastr.success('You have succesfuly addad a new activity code');
          this.display = false;
          [(this.ngOnInit())];
        });
    }
  }

  deleteActivity() {
    this.menuService.deleteActivity(this.activity.code)
      .subscribe(data => {
        this.toastr.success('You have succesfuly deleted selected activity code');
        this.displayDelete = false;
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
      this.activity = new IActivity();
    }
  }

  constructor(private menuService: MenuService, private toastr: ToastrService) {
    this.rowSelection = 'single';
    this.typeT = [
      { name: 'Project', code: 'PROJECT' },
      { name: 'Work Status', code: 'WORK_STATUS' }
    ];
  }

  onGridReady(params) {
    this.gridApi = params.api;
  }

  editActivity() {
    this.pressed = true;
    if (this.activity.name && this.activity.shortName && this.activity.code && this.activity.type) {
      this.activity.modifiedBy = localStorage.getItem('userName');
      this.activity.createdBy = localStorage.getItem('userName');
      this.activity.created = moment(this.activity.created).format('YYYY-MM-DDTHH:mm:ss.SSS');
      this.activity.modified = moment(this.activity.modified).format('YYYY-MM-DDTHH:mm:ss.SSS');
      const request = { entity: this.activity };
      this.menuService.editActivity(request)
        .subscribe(data => {
          this.toastr.success('You have succesfuly eddited selected activity');
          this.display = false;
          [(this.ngOnInit())];
        });
    }
  }
  ngOnInit() {
    this.activity = new IActivity();
    this.menuService.getActivity()
      .subscribe(data => {
        this.rowData = data['payload'];
        this.changeTypeParam(this.rowData);
      });
  }

}
