import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { MenuService } from 'src/app/menu.service';

import { User } from '../job-title/classes/User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Output() logginEvent: EventEmitter<boolean> = new EventEmitter();
  public userName = '';
  public password = '';
  public correct = false;
  public logInBollean = false;
  public alowedUsers: User[];
  public invalidLogInMassage = false;
  constructor(private menuService: MenuService, private router: Router) {
    this.alowedUsers = [
      { userName: 'Žarko', pasword: 'Šarenac' },
      { userName: 'Tarik', pasword: 'Avdić' },
      { userName: 'Irma', pasword: 'Osmanković' },
      { userName: 'Kaid', pasword: 'Lokmić' },
      { userName: 'Džejla', pasword: 'Šuman' },
      { userName: 'Amina', pasword: 'Mehić' }
    ];
  }
 shake() {
    document.getElementById("login").className="login";
  }
  login() {
    for (let i = 0; i < this.alowedUsers.length; i++) {
      if (this.alowedUsers[i].userName === this.userName && this.alowedUsers[i].pasword === this.password) {
        this.correct = true;
        this.logginEvent.emit(true);
        localStorage.setItem('userName', this.userName);
        this.router.navigateByUrl('/');
        this.menuService.loginCorrect(this.correct);
      }
      this.invalidLogInMassage = true;
      document.getElementById("login").classList.add("shake-horizontal");
      
      setTimeout(this.shake, 2000);
     
    }
  }
  loginTry() {
    this.invalidLogInMassage = false;
    if (this.userName.length > 0 && this.password.length > 0) {
      this.logInBollean = true;
    } else {
      this.logInBollean = false;
    }
  }
  ngOnInit() {
    if (localStorage.getItem('userName') !== null) {
    this.router.navigateByUrl('');
    }
    this.menuService.loginC.subscribe(mess => this.correct = mess);
    }

}