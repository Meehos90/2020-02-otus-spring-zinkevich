import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) {
  }

  getBook(id: number): Observable<any> {
    return this.http.get(`/api/books/${id}`);
  }

  createBook(book: Object): Observable<Object> {
    return this.http.post(`/api/books`, book);
  }

  updateBook(id: number, value: any): Observable<Object> {
    return this.http.put(`/api/books/${id}`, value);
  }

  deleteBook(id: number): Observable<any> {
    return this.http.delete(`/api/books/${id}`, {responseType: 'text'});
  }

  getBooksList(): Observable<any> {
    return this.http.get(`/api/books`);
  }
}
