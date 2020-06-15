import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getComment(id: number): Observable<any> {
    return this.http.get(`/api/comments/${id}`);
  }

  createComment(comment: Object): Observable<Object> {
    return this.http.post(`/api/comments`, comment);
  }

  updateComment(id: number, value: any): Observable<Object> {
    return this.http.put(`/api/comments/${id}`, value);
  }

  deleteComment(id: number): Observable<any> {
    return this.http.delete(`/api/comments/${id}`, {responseType: 'text'});
  }

  getCommentsList(): Observable<any> {
    return this.http.get(`/api/comments`);
  }
}
