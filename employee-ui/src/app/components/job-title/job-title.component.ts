import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { MenuService } from 'src/app/menu.service';

import { IjobTitles } from './classes/jobTitles';

@Component({
  selector: 'app-job-title',
  templateUrl: './job-title.component.html',
  styleUrls: ['./job-title.component.css']
})
export class JobTitleComponent implements OnInit {
  public jobTitle: IjobTitles;
  private gridApi;
  private rowSelection;
  public rowData = Array<IjobTitles>();
  selectedRow = true;
  pressed = false;
  option: string;
  display = false;
  displayDelete = false;

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
    { headerName: 'Code', field: 'code', sortable: true, filter: true, resizable: true },
    { headerName: 'Name', field: 'name', sortable: true, filter: true, resizable: true },
    { headerName: 'Short name', field: 'shortName', sortable: true, filter: true, resizable: true },
    { headerName: 'Status', field: 'status', sortable: true, filter: true, resizable: true },
    { headerName: 'Description', field: 'description', sortable: true, filter: true, resizable: true },
    { headerName: 'Modified', field: 'modified', sortable: true, filter: true, resizable: true },
    { headerName: 'Modified by', field: 'modifiedBy', sortable: true, filter: true, resizable: true },
    { headerName: 'Created by', field: 'createdBy', sortable: true, filter: true, resizable: true },
    { headerName: 'Created', field: 'created', sortable: true, filter: true, resizable: true },

  ];

  constructor(private menuService: MenuService, private toastr: ToastrService) {
    this.rowSelection = 'single';
  }

  convertor(param: IjobTitles[]) {
    for (let i = 0; i <= param.length; i++) {
      param[i].created = param[i].created.substr(0, 10);
      if (param[i].modified !== undefined) {
        param[i].modified = param[i].modified.substr(0, 10);
      }
    }
  }

  onGridReady(params) {
    this.gridApi = params.api;
  }

  onSelectionChanged() {
    const selectedRows = this.gridApi.getSelectedRows();
    this.selectedRow = false;
    selectedRows.forEach((selectedRow, index) => {
      this.jobTitle.description = selectedRow.description;
      this.jobTitle.name = selectedRow.name;
      this.jobTitle.shortName = selectedRow.shortName;
      this.jobTitle.modifiedBy = selectedRow.modifiedBy;
      this.jobTitle.status = selectedRow.status;
      this.jobTitle.modified = selectedRow.modified;
      this.jobTitle.createdBy = selectedRow.createdBy;
      this.jobTitle.code = selectedRow.code;
      this.jobTitle.created = selectedRow.created;
    });
  }


  showDialog(param: string) {
    this.display = true;
    this.option = param;
    if (param === 'Create') {
      this.jobTitle = new IjobTitles();
    }
  }

  showDialogDelete() {
    this.displayDelete = true;
  }

  newJobTitle() {
    this.pressed = true;
    if (this.jobTitle.name && this.jobTitle.shortName && this.jobTitle.code) {
      this.jobTitle.createdBy = localStorage.getItem('userName');
      const request = { entity: this.jobTitle };
      this.menuService.createJobTitle(request)
        .subscribe(data => {
          this.menuService.getAllTitels()
            .subscribe(data => {
              this.rowData = data['payload'];
              this.convertor(this.rowData);
            });
          this.display = false;
          this.toastr.success('You have succesfuly addad a new job title');
        });
    }
  }

  editJobTile() {
    this.pressed = true;
    if (this.jobTitle.name && this.jobTitle.shortName && this.jobTitle.code) {
      this.jobTitle.modifiedBy = localStorage.getItem('userName');
      this.jobTitle.created = moment(this.jobTitle.created).format('YYYY-MM-DDTHH:mm:ss.SSS');
      this.jobTitle.modified = moment(this.jobTitle.modified).format('YYYY-MM-DDTHH:mm:ss.SSS');
      const request = { entity: this.jobTitle };
      this.menuService.editJobTitle(request)
        .subscribe(data => {
          this.toastr.success('You have succesfuly eddited selected job title');
          this.display = false;
          this.menuService.getAllTitels()
            .subscribe(data => {
              this.rowData = data['payload'];
              this.convertor(this.rowData);
              this.jobTitle = new IjobTitles();
            });
        });
    }
  }

  deleteJobTitle() {
    this.menuService.deleteJobTitle(this.jobTitle.code)
      .subscribe(data => {
        this.toastr.success('You have succesfuly deleted selected job title');
        this.displayDelete = false;
        this.menuService.getAllTitels()
          .subscribe(data => {
            this.rowData = data['payload'];
            this.convertor(this.rowData);
          });
      }
      );
  }

  cancle() {
    this.displayDelete = false;
    this.selectedRow = true;
  }

  ngOnInit() {
    this.jobTitle = new IjobTitles();
    this.menuService.getAllTitels()
      .subscribe(data => {
        this.rowData = data['payload'];
        this.convertor(this.rowData);
      });
  }

}
