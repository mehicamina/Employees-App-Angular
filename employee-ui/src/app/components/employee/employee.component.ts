import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  employeeAdmin = false;
  employeeProject = false;
  openRegistries(registrie: string) {

    if (registrie === 'employeeAdmin') {
      this.employeeAdmin = true;
      this.employeeProject = false;
      document.getElementById('employeeAdmin').style.backgroundColor = '#035CA0';
      document.getElementById('usercod-icon').style.color = 'white';
      document.getElementById('briefcase-icon').style.color = 'black';
      document.getElementById('employeeProject').style.backgroundColor = 'transparent';
    }
    if (registrie === 'employeeProject') {
      this.employeeAdmin = false;
      this.employeeProject = true;
      document.getElementById('employeeAdmin').style.backgroundColor = 'transparent';
      document.getElementById('usercod-icon').style.color = 'black';
      document.getElementById('briefcase-icon').style.color = 'white';
      document.getElementById('employeeProject').style.backgroundColor = '#035CA0';
    }
  }
  constructor() { }

  ngOnInit() {
    this.openRegistries('employeeAdmin');
  }

}
