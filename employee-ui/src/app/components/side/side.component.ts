import { Component, OnInit } from '@angular/core';
import { MenuService } from 'src/app/menu.service';

@Component({
  selector: 'app-side',
  templateUrl: './side.component.html',
  styleUrls: ['./side.component.css']
})
export class SideComponent implements OnInit {
  public menu = Array<any>();
  public selected = '';
  constructor(private menuService: MenuService) {
  }
  setClass(param: string) {
    this.selected = param;
    console.log(param);
  }
  ngOnInit() {
    this.menuService.getSide()
      .subscribe(data => this.menu = data);
  }

}
