import {AuthenticateService} from './authenticate.service';
import {User} from './user';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'authenticate',
  templateUrl: './authenticate.component.html',
  styleUrls: ['./authenticate.component.css']
})
export class AuthenticateComponent implements OnInit {

  user: User = new User();
  submitted = false;
  error: HttpErrorResponse;

  constructor(private authenticateService: AuthenticateService, private router: Router) {
  }

  ngOnInit(): void {
  }

  save() {
    this.authenticateService.authenticate()
      .subscribe(data => {
        console.log(data);
      }, error => this.error = error);
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

}
