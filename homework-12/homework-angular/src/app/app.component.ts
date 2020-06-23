import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from './auth/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[];
  public authority: string;

  constructor(private tokenStorage: TokenStorageService) {
  }

  ngOnInit() {
    this.tokenStorage.authorityChange.subscribe(roles => {
      if (this.tokenStorage.getToken()) {
        this.roles = roles;
        if (this.roles.some(role => role === 'ROLE_ADMIN')) {
          this.authority = 'admin';
        } else {
          this.authority = 'user';
        }
      }
    });
  }
}
