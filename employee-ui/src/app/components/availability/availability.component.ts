import { Component, OnInit } from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interaction from '@fullcalendar/interaction';
import * as moment from 'moment';
import { CalndarResponse } from 'src/app/menu';
import { MenuService } from 'src/app/menu.service';

import { IActivity } from '../activity/classes/activity';


@Component({
  selector: 'app-availability',
  templateUrl: './availability.component.html',
  styleUrls: ['./availability.component.css']
})
export class AvailabilityComponent implements OnInit {
  constructor(private menuService: MenuService) { }
  calendarPlugins = [dayGridPlugin, interaction];
  datasForCalendar: CalndarResponse[];

  dateForCard: string;
  public namesToShow = ' ';

  paramsForAPI: string;
  activityCode: string;
  date: any;

  public rowData = Array<any>();
  activity: IActivity;

  values = '';
  showSearch = false;


  clickCountHoliday = 0;
  clickCountWork = 0;
  clickCountBusy = 0;
  clickCountSick = 0;

  handleDateClick(arg) {
    if (this.activityCode !== undefined) {
      this.datasForCalendar = new Array<CalndarResponse>();
    }
    this.date = arg.dateStr;
    this.date = moment(this.date).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.dateForCard = arg.dateStr;
  }

  listEmp(date, activityCode) {
    if (this.date !== undefined) {
      this.date = date.dateStr;
      this.activityCode = this.activityCode;
    }
  }

  onKey(event: any) {
    this.values = event.target.value;
    for (let i = 0; i < this.datasForCalendar.length; i++) {
      if (this.datasForCalendar[i].firstName.toLowerCase().indexOf(event.target.value.toLowerCase()) > -1
        || this.datasForCalendar[i].lastName.toLowerCase().indexOf(event.target.value.toLowerCase()) > -1) {
        this.datasForCalendar[i]['selected'] = true;
      }
      if (!this.values) {
        this.datasForCalendar[i]['selected'] = false;
      }
    }
  }

  odabranAC(param: string) {
    this.namesToShow = ' ';
    this.activityCode = param;
    if (this.activityCode === 'Holiday absence') {
      this.activityCode = 'HOLIDAY_ABS';
    } else if (this.activityCode === 'At work') {
      this.activityCode = 'WORK';
    } else if (this.activityCode === 'Business trip') {
      this.activityCode = 'BUS_TRIP';
    } else if (this.activityCode === 'Sickness absence') {
      this.activityCode = 'SICK_ABS';
    }
    this.menuService.getAvailibility({ activityCode: this.activityCode, date: this.date }).subscribe(data => {
      this.datasForCalendar = data.payload;
    });
  }

  search(param) {
    if (param === 'a0') {
      this.clickCountHoliday++;
      document.getElementById('b0').style.display = 'inline-block';
      document.getElementById('b1').style.display = 'none';
      document.getElementById('b2').style.display = 'none';
      document.getElementById('b3').style.display = 'none';
      this.showSearch = true;
      if (this.clickCountHoliday % 2 === 0) {
        document.getElementById('b0').style.display = 'none';
        this.showSearch = false;
      }
    }
    if (param === 'a1') {
      this.clickCountWork++;
      document.getElementById('b1').style.display = 'inline-block';
      document.getElementById('b0').style.display = 'none';
      document.getElementById('b2').style.display = 'none';
      document.getElementById('b3').style.display = 'none';
      this.showSearch = true;
      if (this.clickCountWork % 2 === 0) {
        document.getElementById('b1').style.display = 'none';
        this.showSearch = false;
      }
    }
    if (param === 'a2') {
      this.clickCountBusy++;
      document.getElementById('b2').style.display = 'inline-block';
      document.getElementById('b0').style.display = 'none';
      document.getElementById('b1').style.display = 'none';
      document.getElementById('b3').style.display = 'none';
      this.showSearch = true;
      if (this.clickCountBusy % 2 === 0) {
        document.getElementById('b2').style.display = 'none';
        this.showSearch = false;
      }
    }
    if (param === 'a3') {
      this.clickCountSick++;
      document.getElementById('b3').style.display = 'inline-block';
      document.getElementById('b0').style.display = 'none';
      document.getElementById('b1').style.display = 'none';
      document.getElementById('b2').style.display = 'none';
      this.showSearch = true;
      if (this.clickCountSick % 2 === 0) {
        document.getElementById('b3').style.display = 'none';
        this.showSearch = false;
      }
    }
  }

  ngOnInit() {
    this.activity = new IActivity();
    this.menuService.getWorkStatus()
      .subscribe(data => {
        this.rowData = data['payload'];
      });
  }
}