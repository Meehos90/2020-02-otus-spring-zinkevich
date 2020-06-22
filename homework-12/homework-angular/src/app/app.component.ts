import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './auth/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[];
  public authority: string;

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      if (this.tokenStorage.getToken()) {
        this.roles = this.tokenStorage.getAuthorities();
        if(this.roles.some(role => role === 'ROLE_ADMIN')) {
          this.authority = 'admin';
        } else {
          this.authority = 'user';
        }
      }
    }
  }

}
