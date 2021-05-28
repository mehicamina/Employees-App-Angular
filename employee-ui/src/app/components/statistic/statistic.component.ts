import { Component, OnInit } from '@angular/core';
import { Iemployees } from 'src/app/employees';
import { MenuService } from 'src/app/menu.service';

import { IjobTitles } from '../job-title/classes/jobTitles';

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})
export class StatisticComponent implements OnInit {
  public bar = Array<any>();
  public pie = Array<any>();
  public donut = Array<any>();
  public response: Iemployees[];
  public mapLocation: Map<string, number>;
  public mapJobTitle: Map<string, number>;
  public mapJobTitlesCon: Map<string, string>;
  public titleResposne = Array<IjobTitles>();
  public crvena = 3;
  public plava = 160;
  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
  };

  public barChartLabels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  public barChartType = 'bar';
  public barChartLegend = true;
  public barChartData = [
    { data: Array<any>() },
    { data: Array<any>() }
  ];


  public doughnutChartLabels = [];
  public doughnutChartData: any = [
    {
      data: [],
      backgroundColor: []
    }
  ];
  public doughnutChartType = 'doughnut';

  public pieChartLabels = [];
  public pieChartData: any = [
    {
      data: [],
      backgroundColor: []
    }
  ];
  public pieChartType = 'pie';

  constructor(private menuService: MenuService) { }

  ngOnInit() {
    this.mapJobTitlesCon = new Map<string, string>();
    this.menuService.getAllTitels().subscribe(data => {
      this.titleResposne = data['payload'];
      for (let i = 0; i < this.titleResposne.length; i++) {
        this.mapJobTitlesCon.set(this.titleResposne[i].code, this.titleResposne[i].name);
      }
      this.menuService.getEmployees().subscribe(data => {
        this.response = data['payload'];
        this.mapJobTitle = new Map<string, number>();
        this.mapLocation = new Map<string, number>();
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < this.response.length; i++) {
          if (this.mapJobTitle.has(this.response[i].jobTitleCode)) {
            this.mapJobTitle.set(this.response[i].jobTitleCode, this.mapJobTitle.get(this.response[i].jobTitleCode) + 1);
          } else {
            this.mapJobTitle.set(this.response[i].jobTitleCode, 1);
          }

          if (!this.mapLocation.has(this.response[i].departmentCode)) {
            this.mapLocation.set(this.response[i].departmentCode, 1);
          } else {
            this.mapLocation.set(this.response[i].departmentCode, this.mapLocation.get(this.response[i].departmentCode) + 1);
          }
        }
        this.mapLocation.forEach((value: number, key: string) => {
          this.pieChartLabels.push(key);
          this.pieChartData[0].data.push(value);
          const a = 'rgb(' + this.crvena + ',92,' + this.plava + ')';
          this.pieChartData[0].backgroundColor.push(a);
          this.crvena += 100;
          this.plava -= 40;
        }
        );
        this.crvena = 0;
        this.plava = 250;
        this.mapJobTitle.forEach((value: number, key: string) => {
          this.doughnutChartLabels.push(this.mapJobTitlesCon.get(key));
          this.doughnutChartData[0].data.push(value);
          const a = 'rgb(' + this.crvena + ',92,' + this.plava + ')';
          this.crvena += 20;
          this.plava -= 20;
          this.doughnutChartData[0].backgroundColor.push(a);
        }
        );
      });
    });


    this.menuService.getBar()
      .subscribe(data => { this.barChartData = data; });

  }

}
