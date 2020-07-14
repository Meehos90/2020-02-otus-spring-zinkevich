import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private http: HttpClient) {
  }

  getAuthor(id: number): Observable<any> {
    return this.http.get(`/api/authors/${id}`);
  }

  createAuthor(author: Object): Observable<Object> {
    return this.http.post(`/api/authors`, author);
  }

  updateAuthor(id: number, value: any): Observable<Object> {
    return this.http.put(`/api/authors/${id}`, value);
  }

  deleteAuthor(id: number): Observable<any> {
    return this.http.delete(`/api/authors/${id}`, {responseType: 'text'});
  }

  getAuthorsList(): Observable<any> {
    return this.http.get(`/api/authors`);
  }
}
