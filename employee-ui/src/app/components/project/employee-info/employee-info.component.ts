import { Component, OnInit } from '@angular/core';
import { Iemployees } from 'src/app/employees';
import { MenuService } from 'src/app/menu.service';

import { IjobTitles } from '../../job-title/classes/jobTitles';
import { EmployeInfo } from '../classes/employeInfo';

@Component({
  selector: 'app-employee-info',
  templateUrl: './employee-info.component.html',
  styleUrls: ['./employee-info.component.css']
})
export class EmployeeInfoComponent implements OnInit {
  public employee: Iemployees;
  private message: EmployeInfo;
  public test: string;
  public mapa: Map<string, string>;
  public mapaInvert: Map<string, string>;
  public titleResposne = Array<IjobTitles>();
  public clicked = false;
  constructor(private menuService: MenuService) { }

  convertor(param: Iemployees) {
    if (param.gender === 'M') {
      param.gender = 'Male';
    } else {
      param.gender = 'Female';
    }
    param.jobTitleCode = this.mapa.get(param.jobTitleCode);
    param.birtDate = param.birtDate.substr(0, 10);
    param.hireDate = param.hireDate.substr(0, 10);


  }
  ngOnInit() {
    this.mapaInvert = new Map<string, string>();
    this.mapa = new Map<string, string>();
    this.menuService.getAllTitels().subscribe(data => {
      this.titleResposne = data['payload'];
      for (let i = 0; i < this.titleResposne.length; i++) {
        this.mapa.set(this.titleResposne[i].code, this.titleResposne[i].name);
        this.mapaInvert.set(this.titleResposne[i].name, this.titleResposne[i].code);
      }
    });
    this.test = localStorage.getItem('odabranaBoja');
    this.changeColor();
    this.employee = new Iemployees();
    this.menuService.empInf.subscribe(emess => {
      this.message = emess;
      this.menuService.infoEmployee(+this.message.id).subscribe(data => {
        this.clicked = true;
        this.employee = data['payload'];
        this.convertor(this.employee);
      });
    });

  }


  changeColor() {
    localStorage.setItem('odabranaBoja', this.test);
    document.getElementById('picker').style.backgroundColor = this.test;

    document.getElementById('pozadina').style.backgroundColor = this.test;
  }
}
