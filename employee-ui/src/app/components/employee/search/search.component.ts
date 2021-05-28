import { DatePipe } from '@angular/common';
import { Component, Inject, LOCALE_ID, OnInit } from '@angular/core';
import { MenuService } from 'src/app/menu.service';

import { IjobTitles } from '../../job-title/classes/jobTitles';
import { JobTitle } from '../classes/jobTitle';
import { SearchFields } from '../classes/searchFields';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  private jobT = Array<JobTitle>();
  private firstName: string;
  private lastName: string;
  private phoneNumber: string;
  private dateValue: Date;
  private jobTitle: JobTitle;
  private message: SearchFields;
  public mapa: Map<string, string>;
  public titleResposne = Array<IjobTitles>();
  show = true;
  constructor(private menuService: MenuService, public datepipe: DatePipe, @Inject(LOCALE_ID) private locale: string) {
    this.firstName = '';
    this.lastName = '';
    this.phoneNumber = '';
    this.dateValue = null;
    this.jobTitle = new JobTitle();
  }
  collaps() {
    this.show = !this.show;
  }
  clear() {
    this.firstName = '';
    this.lastName = '';
    this.phoneNumber = '';
    this.dateValue = null;
    this.jobTitle = new JobTitle();

    this.message.firstName = '';
    this.message.lastName = '';
    this.message.phoneNumber = '';
    this.message.hireDate = null;
    this.message.jobTitle = null;

    this.menuService.changeMessage(this.message);
  }
  submit() {
    this.message.firstName = this.firstName;
    this.message.lastName = this.lastName;
    this.message.phoneNumber = this.phoneNumber;
    this.message.hireDate = this.dateValue;
    this.message.jobTitle = this.jobTitle;
    this.menuService.changeMessage(this.message);

  }
  ngOnInit() {


    this.menuService.getAllTitels().subscribe(data => {
      this.titleResposne = data['payload'];
      for (let i = 0; i < this.titleResposne.length; i++) {
        const oneJobTitle = new JobTitle();
        oneJobTitle.code = this.titleResposne[i].code;
        oneJobTitle.name = this.titleResposne[i].name;
        this.jobT.push(oneJobTitle);

      }

    });
    this.menuService.currmess.subscribe(mess => this.message = mess);
  }

}
