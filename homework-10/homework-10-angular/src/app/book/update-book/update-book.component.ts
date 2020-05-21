import {Component, OnInit} from '@angular/core';
import {Book} from "../book";
import {HttpErrorResponse} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {BookService} from "../book.service";
import {NgForm} from "@angular/forms";
import {Observable} from "rxjs";
import {Author} from "../../author/author";
import {Genre} from "../../genre/genre";
import {AuthorService} from "../../author/author.service";
import {GenreService} from "../../genre/genre.service";

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  id: number;
  book: Book;
  error: HttpErrorResponse;
  authors: Observable<Author[]>;
  genres: Observable<Genre[]>;

  constructor(private route: ActivatedRoute, private router: Router,
              private bookService: BookService, private authorService: AuthorService,
              private genreService: GenreService,) {
  }

  ngOnInit() {
    this.book = new Book();
    this.id = this.route.snapshot.params['id'];
    this.authors = this.authorService.getAuthorsList();
    this.genres = this.genreService.getGenresList();

    this.bookService.getBook(this.id)
      .subscribe(data => {
        console.log(data);
        this.book = data;
      }, error => console.log(error))
  }

  updateBook() {
    this.bookService.updateBook(this.id, this.book)
      .subscribe(data => {
        console.log(data);
        this.book = new Book();
        this.gotoList();
      }, error => this.error = error);
  }

  onSubmit(form: NgForm) {
    if (form.invalid) {
      form.control.markAllAsTouched();
      return;
    }
    this.updateBook();
  }

  gotoList() {
    this.router.navigate(['/books']);
  }

}
