import {Component, OnInit} from '@angular/core';
import {Book} from "../book";
import {Author} from "../../author/author";
import {Genre} from "../../genre/genre";
import {HttpErrorResponse} from "@angular/common/http";
import {BookService} from "../book.service";
import {AuthorService} from "../../author/author.service";
import {GenreService} from "../../genre/genre.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {
  book: Book = new Book;
  authors: Observable<Author[]>;
  genres: Observable<Genre[]>;
  submitted = false;
  error: HttpErrorResponse;

  constructor(private bookService: BookService, private  authorService: AuthorService,
              private genreService: GenreService, private router: Router) {
  }

  ngOnInit() {
    this.authors = this.authorService.getAuthorsList();
    this.genres = this.genreService.getGenresList();
  }

  save() {
    this.bookService.createBook(this.book)
      .subscribe(data => {
        console.log(data);
        this.book = new Book();
        this.gotoList();
      }, error => this.error = error)
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
    this.router.navigate(['/books']);
  }

}
