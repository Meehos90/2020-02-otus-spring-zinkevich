import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {Comment} from '../comment';
import {ActivatedRoute, Router} from "@angular/router";
import {CommentService} from "../comment.service";
import {BookService} from "../../book/book.service";
import {Observable} from "rxjs";
import {Book} from "../../book/book";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-update-comment',
  templateUrl: './update-comment.component.html',
  styleUrls: ['./update-comment.component.css']
})
export class UpdateCommentComponent implements OnInit {

  id: number;
  comment: Comment;
  error: HttpErrorResponse;
  books: Observable<Book[]>;

  constructor(private route: ActivatedRoute, private router: Router,
              private bookService: BookService, private commentService: CommentService) {
  }

  ngOnInit() {
    this.comment = new Comment();
    this.books = this.bookService.getBooksList();
    this.id = this.route.snapshot.params['id'];

    this.commentService.getComment(this.id)
      .subscribe(data => {
        console.log(data);
        this.comment = data;
      }, error => console.log(error));
  }

  updateComment() {
    this.commentService.updateComment(this.id, this.comment)
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
    this.updateComment();
  }

  gotoList() {
    this.router.navigate(['/comments']);
  }

}
