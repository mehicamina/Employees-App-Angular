import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuService } from 'src/app/menu.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  public logged;
  public menu = Array<any>();
  toggle = true;
  public user: string;
  public correct = false;
  refresh() {
    this.ngOnInit();
  }

  constructor(private menuService: MenuService, private router: Router) {
  }
  logginEventHandler(param: boolean) {
    this.logged = param;
  }

  logOut(param: string) {
    if (param === 'Log out') {
      localStorage.removeItem('userName');
      this.logged = false;
      this.router.navigateByUrl('/login');
    }
  }

  ngOnInit() {
    this.menuService.loginC.subscribe(mess => {
      this.user = localStorage.getItem('userName');
      this.logged = mess;
      this.menuService.getMeni()
        .subscribe(data => this.menu = data);
    }
    );
    if (localStorage.getItem('userName') === null) {
      this.router.navigateByUrl('/login');
    } else {
      this.user = localStorage.getItem('userName');
      this.logged = true;

      this.menuService.getMeni()
        .subscribe(data => this.menu = data);
    }
  }
}
