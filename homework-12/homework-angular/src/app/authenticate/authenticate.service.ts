import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  constructor(private http: HttpClient) {
  }

  authenticate(user: Object): Observable<Object> {
    return this.http.get(`/api/auth`, user);
  }

}
