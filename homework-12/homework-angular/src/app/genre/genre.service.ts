import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor(private http: HttpClient) {
  }

  getGenre(id: number): Observable<any> {
    return this.http.get(`/api/genres/${id}`);
  }

  createGenre(genre: Object): Observable<Object> {
    return this.http.post(`/api/genres`, genre);
  }

  updateGenre(id: number, value: any): Observable<Object> {
    return this.http.put(`/api/genres/${id}`, value);
  }

  deleteGenre(id: number): Observable<any> {
    return this.http.delete(`/api/genres/${id}`, {responseType: 'text'});
  }

  getGenresList(): Observable<any> {
    return this.http.get(`/api/genres`);
  }

}
