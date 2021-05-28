import { Component, OnInit, ViewChild } from '@angular/core';
import { IActivity } from 'src/app/components/activity/classes/activity';
import { Iemployees } from 'src/app/employees';
import { MenuService } from 'src/app/menu.service';

import { EmployeeInfoComponent } from './employee-info/employee-info.component';
import { TreeComponent } from './tree/tree.component';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
  @ViewChild(TreeComponent, { static: false }) child: TreeComponent;
  @ViewChild(EmployeeInfoComponent, { static: false }) child2: EmployeeInfoComponent;
  public employee: Iemployees;
  public odBoj: string;
  activity: IActivity;
  
  constructor(private menuService: MenuService) { }
  show = true;
  collaps(param: any) {
    if (param === 'selected') {
      this.show = true;
    } else {
      this.show = !this.show;
      this.child.showGraph(this.child.projectShear);

    }

  }
  ngOnInit() {
  }

}
