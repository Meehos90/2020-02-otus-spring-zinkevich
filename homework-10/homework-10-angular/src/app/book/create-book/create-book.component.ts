import {Component, OnInit} from '@angular/core';
import {Book} from "../book";
import {HttpErrorResponse} from "@angular/common/http";
import {BookService} from "../book.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {
  book: Book = new Book;
  submitted = false;
  error: HttpErrorResponse;

  constructor(private bookService: BookService, private router: Router) {
  }

  ngOnInit(): void {
  }

  save() {
    this.bookService.createBook(this.book)
      .subscribe(data => {
        console.log(data);
        this.book = new Book();
        this.gotoList();
      }, error => this.error = error)
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/books']);
  }

}
