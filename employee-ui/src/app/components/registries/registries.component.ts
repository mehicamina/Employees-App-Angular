import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-registries',
  templateUrl: './registries.component.html',
  styleUrls: ['./registries.component.css']
})
export class RegistriesComponent implements OnInit {
  jobtitle = false;
  activityC = false;
  department = false;
  constructor() { }
  openRegistries(registrie: string) {

    if (registrie === 'jobTite') {
      this.jobtitle = true;
      this.activityC = false;
      this.department = false;
      document.getElementById('jobT').style.color = 'white';
      document.getElementById('act').style.color = 'black';
      document.getElementById('dep').style.color = 'black';
      document.getElementById('jobTite').style.backgroundColor = '#035CA0';
      document.getElementById('activity').style.backgroundColor = 'transparent';
      document.getElementById('department').style.backgroundColor = 'transparent';

    }
    if (registrie === 'activity') {
      this.jobtitle = false;
      this.activityC = true;
      this.department = false;
      document.getElementById('jobT').style.color = 'black';
      document.getElementById('act').style.color = 'white';
      document.getElementById('dep').style.color = 'black';
      document.getElementById('jobTite').style.backgroundColor = 'transparent';
      document.getElementById('activity').style.backgroundColor = '#035CA0';
      document.getElementById('department').style.backgroundColor = 'transparent';

    }
    if (registrie === 'department') {
      this.department = true;
      this.jobtitle = false;
      this.activityC = false;
      document.getElementById('jobT').style.color = 'black';
      document.getElementById('act').style.color = 'black';
      document.getElementById('dep').style.color = 'white';
      document.getElementById('jobTite').style.backgroundColor = 'transparent';
      document.getElementById('activity').style.backgroundColor = 'transparent';
      document.getElementById('department').style.backgroundColor = '#035CA0';

    }
  }
  ngOnInit() {
    this.openRegistries('jobTite');
  }

}
