import {Component, OnInit} from '@angular/core';
import {Comment} from "../comment";
import {HttpErrorResponse} from "@angular/common/http";
import {CommentService} from "../comment.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {Book} from "../../book/book";
import {BookService} from "../../book/book.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrls: ['./create-comment.component.css']
})
export class CreateCommentComponent implements OnInit {

  comment: Comment = new Comment();
  submitted = false;
  error: HttpErrorResponse;
  books: Observable<Book[]>;

  constructor(private commentService: CommentService, private bookService: BookService,
              private router: Router) {
  }

  ngOnInit() {
    this.books = this.bookService.getBooksList();
  }

  save() {
    this.commentService.createComment(this.comment)
      .subscribe(data => {
        console.log(data);
        this.comment = new Comment();
        this.gotoList();
      }, error => this.error = error);
  }

  onSubmit(form: NgForm) {
    if (form.invalid) {
      form.control.markAllAsTouched();
      return;
    }
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/comments']);
  }

}
